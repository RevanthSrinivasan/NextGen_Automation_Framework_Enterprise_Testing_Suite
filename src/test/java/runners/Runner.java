package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        plugin = {"pretty", "html:target/cucumber-html-report"},
        tags = "@api"
)
public class Runner extends AbstractTestNGCucumberTests {
}
