package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"classpath:features/_02_Login.feature"},
        glue = {"stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"}
)

public class TestRunnerLoginSteps extends AbstractTestNGCucumberTests {

}