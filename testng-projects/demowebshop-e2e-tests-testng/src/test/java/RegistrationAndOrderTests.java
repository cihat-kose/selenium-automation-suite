import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriver;

import java.time.Duration;
import java.util.List;


public class RegistrationAndOrderTests extends BaseDriver {
    JavascriptExecutor js;
    Faker randomGenerator = new Faker();
    Actions actions;
    @org.testng.annotations.BeforeClass
    public void initializeActions() {
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }
    String password = "password";
    String randomEmail;
    String randomName;
    String randomSurname;
    Action action;

    @Test(priority = 1)
    public void registerTest() {

        driver.get(System.getProperty("demowebshop.url", "https://demowebshop.tricentis.com/"));

        randomName = randomGenerator.name().firstName();
        randomSurname = randomGenerator.name().lastName();
        randomEmail = randomGenerator.internet().emailAddress();
        WebElement registerButton = driver.findElement(By.linkText("Register"));

        Action action = actions.moveToElement(registerButton).click().build();
        action.perform();

        WebElement maleButton = driver.findElement(By.cssSelector("[id='gender-male']"));
        action = actions
                .moveToElement(maleButton)
                .click()
                .sendKeys(Keys.TAB)
                .sendKeys(randomName)
                .sendKeys(Keys.TAB)
                .sendKeys(randomSurname)
                .sendKeys(Keys.TAB)
                .sendKeys(randomEmail)
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build();
        action.perform();

        WebElement emailConfirmation = driver.findElement(By.linkText(randomEmail));
        Assert.assertEquals(emailConfirmation.getText(), randomEmail);

        WebElement logOut = driver.findElement(By.linkText("Log out"));
        action = actions.moveToElement(logOut).click().build();
        action.perform();
    }

    @Test(dependsOnMethods = "registerTest", priority = 2)
    public void registerTestNegative() {

        driver.get(System.getProperty("demowebshop.url", "https://demowebshop.tricentis.com/"));
        WebElement registerButton = driver.findElement(By.linkText("Register"));

        Action action = actions.moveToElement(registerButton).click().build();
        action.perform();

        WebElement maleButton = driver.findElement(By.cssSelector("[id='gender-male']"));
        action = actions
                .moveToElement(maleButton)
                .click()
                .sendKeys(Keys.TAB)
                .sendKeys(randomName)
                .sendKeys(Keys.TAB)
                .sendKeys(randomSurname)
                .sendKeys(Keys.TAB)
                .sendKeys(randomEmail)
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build();
        action.perform();

        WebElement alreadyExists = driver.findElement(By.xpath("//li[text()='The specified email already exists']"));
        Assert.assertEquals(alreadyExists.getText(), "The specified email already exists");
    }

    @Test(dependsOnMethods = "registerTest", priority = 3)
    public void loginTest() {

        driver.get(System.getProperty("demowebshop.url", "https://demowebshop.tricentis.com/"));

        WebElement loginButton = driver.findElement(By.linkText("Log in"));
        action = actions
                .moveToElement(loginButton)
                .click()
                .build();
        action.perform();

        WebElement email = driver.findElement(By.cssSelector("[id='Email']"));

        action = actions
                .moveToElement(email)
                .sendKeys(randomEmail)
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build();

        action.perform();

        WebElement logOut = driver.findElement(By.linkText("Log out"));
        Assert.assertEquals(logOut.getText(), "Log out", "Login unsuccessful");
        logOut.click();
    }

    @Test(dependsOnMethods = "registerTest", priority = 4)
    public void loginTestNegative() {

        driver.get(System.getProperty("demowebshop.url", "https://demowebshop.tricentis.com/"));

        WebElement loginButton = driver.findElement(By.xpath("//a[text()='Log in']"));
        action = actions
                .moveToElement(loginButton)
                .click()
                .build();
        action.perform();

        WebElement email = driver.findElement(By.cssSelector("[id='Email']"));

        action = actions
                .moveToElement(email)
                .sendKeys(randomGenerator.internet().emailAddress())
                .sendKeys(Keys.TAB)
                .sendKeys(randomGenerator.internet().password())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build();

        action.perform();

        WebElement unsuccessfulLogin = driver.findElement(By.xpath("//span[text()='Login was unsuccessful. Please correct the errors and try again.']"));
        Assert.assertTrue(unsuccessfulLogin.getText().contains("Login was unsuccessful"));
    }

    @Test(dependsOnMethods = "registerTest", priority = 5)
    public void orderTest() {

        driver.get(System.getProperty("demowebshop.url", "https://demowebshop.tricentis.com/"));

        WebElement loginButton = driver.findElement(By.linkText("Log in"));
        action = actions
                .moveToElement(loginButton)
                .click()
                .build();
        action.perform();

        WebElement email = driver.findElement(By.cssSelector("[id='Email']"));

        action = actions
                .moveToElement(email)
                .sendKeys(randomEmail)
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build();

        action.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log out")));

        WebElement computersBigPunto = driver.findElement(By.linkText("COMPUTERS"));

        action = actions
                .moveToElement(computersBigPunto)
                .build();

        action.perform();

        WebElement notebooks = driver.findElement(By.linkText("Notebooks"));
        notebooks.click();

        WebElement laptop = driver.findElement(By.linkText("14.1-inch Laptop"));
        action = actions.moveToElement(laptop).click().build();
        action.perform();

        WebElement addToCard = driver.findElement(By.cssSelector("[id='add-to-cart-button-31']"));
        addToCard.click();

        WebElement confirmationMessage = driver.findElement(By.xpath("//p[text()='The product has been added to your ']"));
        Assert.assertTrue(confirmationMessage.getText().contains("The product has been added to your"));

        WebElement shoppingCard = driver.findElement(By.xpath("//span[text()='Shopping cart']"));
        action = actions.moveToElement(shoppingCard).click().build();
        action.perform();

        List<WebElement> products = driver.findElements(By.xpath("//td[@class='product']"));
        String notebook = null;

        //Assert.assertTrue("the process failed", products.get(0).getText().contains("14.1-inch Laptop"));

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getText().equals("14.1-inch Laptop")) {
                notebook = products.get(i).getText();
                break;
            }
        }
        Assert.assertEquals("14.1-inch Laptop", notebook);

        WebElement checkBox = driver.findElement(By.cssSelector("[id='termsofservice']"));
        checkBox.click();

        WebElement checkOut = driver.findElement(By.cssSelector("[id='checkout']"));
        checkOut.click();

        WebElement countrySelectMenu = driver.findElement(By.cssSelector("[id='BillingNewAddress_CountryId']"));
        Select country = new Select(countrySelectMenu);
        country.selectByVisibleText("United States");

        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Oslo");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Test Street 12");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("10001");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("2025550123");
        driver.findElement(By.cssSelector("#billing-buttons-container input")).click();

        WebElement continue2 = driver.findElement(By.xpath("(//input[@title='Continue'])[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(continue2)).click();

        WebElement continue3 = driver.findElement(By.xpath("//input[@onclick='ShippingMethod.save()']"));
        wait.until(ExpectedConditions.elementToBeClickable(continue3)).click();

        WebElement continue4 = driver.findElement(By.xpath("//input[@onclick='PaymentMethod.save()']"));
        wait.until(ExpectedConditions.elementToBeClickable(continue4)).click();

        WebElement continue5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='PaymentInfo.save()']")));
        continue5.click();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);"); // Goes to the end of the page.
        WebElement confirmOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='ConfirmOrder.save()']")));
        js.executeScript("arguments[0].click();", confirmOrder);

        WebElement confirmationText = driver.findElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        Assert.assertEquals(confirmationText.getText(), "Your order has been successfully processed!", "Order successful");


    }
}