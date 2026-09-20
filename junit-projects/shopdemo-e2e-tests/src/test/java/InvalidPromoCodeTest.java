import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidPromoCodeTest extends BaseDriver {

    @Test
    public void verifyInvalidPromoCode() {
        driver.get(System.getProperty("shopdemo.url", "https://shopdemo.e-junkie.com/"));

        WebElement eBook = driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement addToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        addToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement promoButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Apply-Button Show-Promo-Code-Button']")));
        promoButton.click();

        WebElement promoCodeInput = driver.findElement(By.className("Promo-Code-Value"));
        promoCodeInput.sendKeys("INVALIDCODE");

        WebElement applyButton = driver.findElement(By.xpath("//button[@class='Promo-Apply']"));
        applyButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//span[text()='Invalid promo code']"));
        assertEquals("Invalid promo code", errorMessage.getText());


    }
}
