package utility;
import com.selenium.support.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;
public class BaseDriverParameter {
    protected WebDriver driver;
    protected WebDriverWait wait;
    @BeforeClass
    @Parameters("browserType")
    public void startingOperations(@Optional String browser) {
        driver = browser == null ? DriverFactory.create() : DriverFactory.create(browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    @AfterMethod(alwaysRun = true)
    public void captureFailure(org.testng.ITestResult result) {
        if (!result.isSuccess() && driver != null) {
            java.nio.file.Path folder = java.nio.file.Path.of("target", "screenshots");
            try {
                java.nio.file.Files.createDirectories(folder);
                java.nio.file.Files.write(folder.resolve(result.getName() + ".png"),
                    ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES));
                java.nio.file.Files.writeString(folder.resolve(result.getName() + ".html"), driver.getPageSource());
            } catch (org.openqa.selenium.WebDriverException | java.io.IOException captureFailure) {
                System.err.println("Failure capture unavailable: " + captureFailure.getClass().getSimpleName());
            }
        }
    }
    @AfterClass(alwaysRun = true)
    public void endingOperations() {
        if (driver != null) { try { driver.quit(); } finally { driver = null; } }
    }
}
