package com.selenium.support;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;
class DriverSmokeTest {
    @Test void submitsFormAndWaitsForResult() {
        WebDriver driver = DriverFactory.create();
        try {
            String html = """
                <!doctype html><title>Automation smoke</title>
                <form onsubmit="event.preventDefault(); setTimeout(() => document.getElementById('result').textContent = 'Hello ' + document.getElementById('name').value, 100)">
                <label>Name <input id="name" required></label><button id="submit">Submit</button></form>
                <p id="result"></p>
                """;
            driver.get("data:text/html;charset=utf-8," + URLEncoder.encode(html, StandardCharsets.UTF_8).replace("+", "%20"));
            assertEquals("Automation smoke", driver.getTitle());
            driver.findElement(By.id("name")).sendKeys("Selenium");
            driver.findElement(By.id("submit")).click();
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.textToBe(By.id("result"), "Hello Selenium"));
            assertEquals("Hello Selenium", driver.findElement(By.id("result")).getText());
        } finally { driver.quit(); }
    }
    @Test void rejectsUnsupportedBrowser() {
        assertThrows(IllegalArgumentException.class, () -> DriverFactory.create("unknown"));
    }
}
