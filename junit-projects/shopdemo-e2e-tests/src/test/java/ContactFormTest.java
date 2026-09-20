import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactFormTest extends BaseDriver {

    @Test
    public void verifyContactForm() {
        driver.get(System.getProperty("shopdemo.url", "https://shopdemo.e-junkie.com/"));

        WebElement contactUs = driver.findElement(By.xpath("//a[@class='contact']"));
        contactUs.click();

        WebElement name = driver.findElement(By.xpath("//input[@id='sender_name']"));
        name.sendKeys(getFaker().name().fullName());

        WebElement email = driver.findElement(By.xpath("//input[@id='sender_email']"));
        email.sendKeys(getFaker().internet().emailAddress());

        WebElement subject = driver.findElement(By.xpath("//input[@id='sender_subject']"));
        subject.sendKeys("Inquiry");

        WebElement message = driver.findElement(By.xpath("//textarea[@id='sender_message']"));
        message.sendKeys("I need assistance with my order.");

        WebElement sendButton = driver.findElement(By.xpath("//button[text()='Send Message']"));
        sendButton.click();

        assertEquals("Recaptcha didn't match", wait.until(ExpectedConditions.alertIsPresent()).getText());
        driver.switchTo().alert().accept();


    }
}
