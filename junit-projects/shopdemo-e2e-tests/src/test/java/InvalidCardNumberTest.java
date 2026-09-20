import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidCardNumberTest extends BaseDriver {

    @Test
    public void verifyInvalidCardNumber() {
        driver.get(System.getProperty("shopdemo.url", "https://shopdemo.e-junkie.com/"));

        WebElement eBook = driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement addToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        addToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement creditCardOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='Payment-Button CC']")));
        creditCardOption.click();

        WebElement iframeCard = driver.findElement(By.cssSelector("iframe[name^='__privateStripeFrame']"));
        driver.switchTo().frame(iframeCard);

        WebElement cardNumberInput = driver.findElement(By.cssSelector("[name='cardnumber']"));
        cardNumberInput.sendKeys("1111 1111 1111 1111");

        driver.switchTo().parentFrame();
        WebElement errorMsg = driver.findElement(By.xpath("//*[@id='SnackBar']"));

        assertEquals("Your card number is invalid.", errorMsg.getText());


    }
}
