import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CaptchaValidationTest extends BaseDriver {

    @Test
    public void rejectPaymentWithoutCaptcha() {
        driver.get(System.getProperty("shopdemo.url", "https://shopdemo.e-junkie.com/"));

        WebElement eBook = driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement addToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        addToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement payUsingDebit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Payment-Button CC']")));
        payUsingDebit.click();

        String mail = getFaker().internet().emailAddress();

        WebElement email = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        email.sendKeys(mail);

        WebElement confirmMail = driver.findElement(By.xpath("//input[@placeholder='Confirm Email']"));
        confirmMail.sendKeys(mail);

        WebElement name = driver.findElement(By.xpath("//input[@placeholder='Name On Card']"));
        name.sendKeys(getFaker().name().fullName());

        WebElement phone = driver.findElement(By.xpath("(//input[@placeholder='Optional'])[1]"));
        phone.sendKeys(getFaker().phoneNumber().cellPhone());

        WebElement company = driver.findElement(By.xpath("(//input[@placeholder='Optional'])[2]"));
        company.sendKeys(getFaker().company().name());

        WebElement iframe2 = driver.findElement(By.cssSelector("iframe[name^='__privateStripeFrame']"));
        driver.switchTo().frame(iframe2);

        WebElement cardNumberBox = driver.findElement(By.name("cardnumber"));
        cardNumberBox.sendKeys("4242 4242 4242 4242");

        WebElement cardMonthYear = driver.findElement(By.name("exp-date"));
        cardMonthYear.sendKeys(java.time.LocalDate.now().plusYears(2).format(java.time.format.DateTimeFormatter.ofPattern("MMyy")));

        WebElement cardCVC = driver.findElement(By.name("cvc"));
        cardCVC.sendKeys("000");

        driver.switchTo().parentFrame();

        WebElement payButton = driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        payButton.click();

        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"SnackBar\"]/span")));

        assertTrue(confirmationMessage.getText().contains("Captcha verification failed. Please try again."));


    }
}
