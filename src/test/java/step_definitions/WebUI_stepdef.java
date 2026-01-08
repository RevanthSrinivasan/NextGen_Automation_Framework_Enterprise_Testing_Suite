package step_definitions;

import core.DriverManager;
import core.FrameworkInitializer;
import core.ScenarioVariables;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.ConfigReader;
import web.pages.HomePage;

import java.io.IOException;

public class WebUI_stepdef {

    private final ScenarioVariables scenarioContext;

    public WebUI_stepdef(ScenarioVariables scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Before
    public void setUp() throws IOException {
        // Initialize framework (loads assets dynamically from setuplist.json)
        FrameworkInitializer.initFramework();
    }

    @Given("I open the home page")
    public void i_open_home_page() {
        WebDriver driver = DriverManager.getDriver();
        String baseUrl = ConfigReader.get("ui.baseUrl." + ConfigReader.get("env"));
        System.out.println("Opening URL: " + baseUrl);
        driver.get(baseUrl);
    }

    @When("I get the heading text")
    public void i_get_the_heading_text() {
        WebDriver driver = DriverManager.getDriver();
        HomePage homePage = new HomePage(driver);
        String heading = homePage.getHeadingText();
        scenarioContext.set("heading", heading);
    }

    @Then("I validate the heading text")
    public void i_validate_the_heading_text() {
        String heading = scenarioContext.get("heading", String.class);
        System.out.println("Heading is: " + heading);
        Assert.assertNotNull(heading, "Heading should not be null");
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
        System.out.println("Session closed");
    }
}
