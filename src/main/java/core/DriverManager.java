package core;
import org.openqa.selenium.WebDriver;
public final class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private DriverManager(){}
    public static WebDriver getWebDriver()
    {
        return driver.get();
    }
    public static void setWebDriver(WebDriver webDriver)
    {
        driver.set(webDriver);
        System.out.println("Driver is set");
    }
    public static void closeDriver()
    {
        if(driver.get()!=null)
        {
            driver.get().quit();
            driver.remove();
        }
    }

}
