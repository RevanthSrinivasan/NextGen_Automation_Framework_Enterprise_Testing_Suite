package step_definitions;

import core.ContextWrapper;
import core.DriverManager;
import core.FrameworkInitializer;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import web.pages.HomePage;

import java.io.IOException;
import java.util.Scanner;

public class Sample_stepdef {
    ContextWrapper contextWrapper;

    public Sample_stepdef(ContextWrapper context) {
        this.contextWrapper = context;
    }
    @Before
    public void setUp() throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        String browserName = scanner.next();
        FrameworkInitializer.initFramework();

//        // Initialize WebDriver
//        WebDriver driver = new ChromeDriver();
//        DriverManager.setWebDriver(driver);
    }

//    @Given("framework is initialized")
//    public void framework_is_initialized() {
//        System.out.println("Framework initialized successfully");
//    }
//
//    @Then("test should pass")
//    public void test_should_pass() {
//        Assert.assertTrue(true);
//    }

    @Given("I open the home page")
    public void i_open_home_page() {
        WebDriver driver = DriverManager.getWebDriver();
        System.out.println("URL:"+FrameworkInitializer.properties.getProperty("baseUrl"));
        driver.get(FrameworkInitializer.properties.getProperty("baseUrl"));
    }

    @When("I get the heading text")
    public void i_get_the_heading_text() {
        HomePage homePage = new HomePage(DriverManager.getWebDriver());
        contextWrapper.getScenarioVariables().set("heading", homePage.getHeadingText());
    }

    @Then("I validate the heading text")
    public void i_validate_the_heading_text() {
        String heading = (String) contextWrapper.getScenarioVariables().get("heading");
        System.out.println("Heading is: " + heading);
        Assert.assertNotNull(heading);
    }

    @After
    public void tearDown() {
        DriverManager.closeDriver();
        System.out.println("Session closed");
    }
}
