package stepDefinitions;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.GWD;
public class Hooks {
    @After
    public void after(Scenario scenario) {
        try {
            if (scenario.isFailed() && GWD.currentDriver() instanceof TakesScreenshot screenshot) {
                scenario.attach(GWD.currentDriver().getPageSource(), "text/html", "Failure page");
                scenario.attach(screenshot.getScreenshotAs(OutputType.BYTES), "image/png", "Failure");
            }
        } catch (org.openqa.selenium.WebDriverException screenshotFailure) {
            scenario.log("Screenshot unavailable: " + screenshotFailure.getClass().getSimpleName());
        } finally { GWD.quitDriver(); }
    }
}
