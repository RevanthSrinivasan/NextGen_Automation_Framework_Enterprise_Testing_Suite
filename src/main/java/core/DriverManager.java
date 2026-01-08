package core;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;

public final class DriverManager {
    private static WebDriver driver;

    public static void init() {
        String browser = ConfigReader.get("browser").toLowerCase();
        driver = switch (browser) {
            case "chrome" -> {
                System.out.println("Initializing Chrome driver...");
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
            case "firefox" -> {
                System.out.println("Initializing Firefox driver...");
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            default -> throw new RuntimeException("Unsupported browser: " + browser);
        };
        System.out.println(browser + " driver initialized");
    }

    public static WebDriver getDriver() { return driver; }

    public static void quitDriver() {
        if (driver != null) driver.quit();
    }

}
