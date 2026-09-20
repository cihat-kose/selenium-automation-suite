package com.selenium.support;

import java.time.Duration;
import java.util.Locale;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/** Creates isolated browser sessions; Selenium Manager resolves driver binaries. */
public final class DriverFactory {
    private DriverFactory() { }
    public static WebDriver create() { return create(System.getProperty("browser", "chrome")); }
    public static WebDriver create(String browser) {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        WebDriver driver = switch (browser.toLowerCase(Locale.ROOT)) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=en-US", "--disable-dev-shm-usage");
                if (headless) options.addArguments("--headless=new");
                yield new ChromeDriver(options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--lang=en-US");
                if (headless) options.addArguments("--headless=new");
                yield new EdgeDriver(options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.addPreference("intl.accept_languages", "en-US");
                if (headless) options.addArguments("-headless");
                yield new FirefoxDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
        try {
            driver.manage().window().setSize(new Dimension(1440, 1000));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            return driver;
        } catch (RuntimeException failure) {
            try { driver.quit(); } catch (RuntimeException cleanup) { failure.addSuppressed(cleanup); }
            throw failure;
        }
    }
}
