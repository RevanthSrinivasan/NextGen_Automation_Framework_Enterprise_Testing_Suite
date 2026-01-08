package core;

import com.fasterxml.jackson.databind.JsonNode;
import utils.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class FrameworkInitializer {

    public static void initFramework() throws IOException {
        System.out.println("Initializing Framework...");

        // Load Config
        ConfigReader.loadProperties();
        System.out.println("Config loaded: browser=" + ConfigReader.get("browser"));

        // Load setuplist.json
        SetupListReader.init("src/test/resources/setuplist.json");
        System.out.println("SetupList loaded");

        // Load runnermap.json
        RunnerMapReader.init("src/test/resources/runnermap.json");
        System.out.println("Runnermap loaded");

        DatalistReader.init("src/test/resources/datalist.json");
        System.out.println("Datalist loaded");
        // List of all possible assets
        String[] assets = {"ui", "api", "mobile", "etl", "reporting", "logging"};

        for (String assetName : assets) {
            try {
                // Only initialize if module enabled in runnermap.json
                if (!RunnerMapReader.isModuleEnabled(assetName)) {
                    System.out.println("Skipping asset: " + assetName + " (disabled in runnermap.json)");
                    continue;
                }

                JsonNode assetNode = SetupListReader.getAsset(assetName);
                if (assetNode.isMissingNode()) {
                    System.out.println("Asset not defined in setuplist.json: " + assetName);
                    continue;
                }

                String className = assetNode.get("class").asText();
                String methodName = assetNode.get("initMethod").asText();

                Class<?> clazz = Class.forName(className);
                Method method = clazz.getMethod(methodName);
                method.invoke(null); // static init

                System.out.println("Initialized asset: " + assetName);

            } catch (Exception e) {
                System.out.println("Failed to initialize asset: " + assetName);
                e.printStackTrace();
            }
        }

        // Validate critical config keys
        validateConfig();

        System.out.println("=== Framework Initialization Complete ===");
    }

    private static void validateConfig() {
        ConfigPropertiesValidator.validateRequiredKeys(
                List.of(
                        "env",
                        "browser",
                        "implicitWait",
                        "pageLoadTimeout",
                        "api.baseUrl." + RunnerMapReader.getEnv(),
                        "ui.baseUrl." + RunnerMapReader.getEnv()
                )
        );
        System.out.println("Configuration validation successful");
    }
}
