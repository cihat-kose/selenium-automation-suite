import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvalidPaymentTest extends BaseDriver {

    @Test
    public void verifyInvalidPaymentDetails() {
        driver.get(System.getProperty("shopdemo.url", "https://shopdemo.e-junkie.com/"));

        WebElement eBook = driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement addToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        addToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement payWithDebit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='Payment-Options']/button)[3]")));
        new Actions(driver).moveToElement(payWithDebit).click().perform();

        WebElement payButton = driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        payButton.click();

        WebElement errorMsg = driver.findElement(By.xpath("//span[text()='Invalid Email']"));
        assertTrue(errorMsg.getText().contains("Invalid Email"));


    }
}
