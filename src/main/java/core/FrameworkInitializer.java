package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;

import java.util.Properties;
import java.util.Scanner;

public class FrameworkInitializer {

    public static Properties properties;

    // Initialize the framework
    public static void initFramework() {
        System.out.println("Initializing Framework...");

        // Load configuration properties
        try {
            properties = ConfigReader.loadProperties();
            System.out.println("Config loaded: " + properties);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration");
        }
        initChooseDriver();
        initReporting();
        initLogging();
        System.out.println("=== Framework Initialization Complete ===");
        // Initialize logging or reporting if needed
        // ReportManager.initReports();

        // You can initialize other utilities here
        // ExcelReader.init();
        // DBConnection.init();
    }

    public static void initChooseDriver() {
        String browser = properties.getProperty("browser").toLowerCase();
        WebDriver driver = switch (browser) {
            case "chrome" -> {
                System.out.println("Intializing the Chrome");
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            default -> throw new RuntimeException("Unsupported browser: " + browser);
        };
        DriverManager.setWebDriver(driver);
        System.out.println(browser + " driver initialized");
    }

    private static void initReporting() {
        System.out.println("Reporting Initialized (placeholder)");
    }

    private static void initLogging() {
        System.out.println("Logging Initialized (placeholder)");
    }
}
