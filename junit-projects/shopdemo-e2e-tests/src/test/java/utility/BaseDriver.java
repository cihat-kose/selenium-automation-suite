package utility;

import com.selenium.support.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseDriver {
    private static final net.datafaker.Faker FAKER = new net.datafaker.Faker();

    public static net.datafaker.Faker getFaker() {
        return FAKER;
    }

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void startDriver() {
        driver = DriverFactory.create();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
}
