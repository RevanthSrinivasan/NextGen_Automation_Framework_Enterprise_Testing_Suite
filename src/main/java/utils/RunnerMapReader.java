package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class RunnerMapReader {

    private static JsonNode rootNode;

    public static void init(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(new File(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read runnermap.json: " + e.getMessage(), e);
        }
    }

    public static boolean isModuleEnabled(String moduleName) {
        if (rootNode == null) throw new RuntimeException("RunnermapReader not initialized!");
        return rootNode.path("modules").path(moduleName).asBoolean(false);
    }

    public static String getEnv() {
        if (rootNode == null) throw new RuntimeException("RunnermapReader not initialized!");
        return rootNode.path("env").asText("qa");
    }
}
