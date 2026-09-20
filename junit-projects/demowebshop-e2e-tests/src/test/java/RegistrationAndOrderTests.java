import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BaseDriver;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationAndOrderTests extends BaseDriver {

    private Actions actions;
    private final String email = "suite-" + java.util.UUID.randomUUID() + "@example.com";

    @BeforeEach
    public void setup() {
        driver.get(System.getProperty("demowebshop.url", "https://demowebshop.tricentis.com/"));
        actions = new Actions(driver);
    }

    @Test
    @Order(1)
    public void registerSuccessfully() {

        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();

        WebElement gender = driver.findElement(By.id("gender-male"));
        gender.click();

        WebElement firstName = driver.findElement(By.id("FirstName"));
        actions.moveToElement(firstName).click().sendKeys("Name").perform();

        WebElement lastName = driver.findElement(By.id("LastName"));
        actions.moveToElement(lastName).click().sendKeys("Surname").perform();

        WebElement eMail = driver.findElement(By.id("Email"));
        actions.moveToElement(eMail).click().sendKeys(email).perform();

        WebElement password = driver.findElement(By.id("Password"));
        actions.moveToElement(password).click().sendKeys("password").perform();

        WebElement confirmPassword = driver.findElement(By.id("ConfirmPassword"));
        actions.moveToElement(confirmPassword).click().sendKeys("password").perform();

        WebElement registerButton = driver.findElement(By.id("register-button"));
        registerButton.click();

        WebElement confirmation = driver.findElement(By.className("result"));
        Assertions.assertEquals("Your registration completed", confirmation.getText());

        WebElement logout = driver.findElement(By.linkText("Log out"));
        logout.click();
    }

    @Test
    @Order(2)
    public void registerWithExistingEmail() {
        registerSuccessfully();

        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();

        WebElement gender = driver.findElement(By.id("gender-male"));
        gender.click();

        WebElement firstName = driver.findElement(By.id("FirstName"));
        actions.moveToElement(firstName).click().sendKeys("Name").perform();

        WebElement lastName = driver.findElement(By.id("LastName"));
        actions.moveToElement(lastName).click().sendKeys("Surname").perform();

        WebElement eMail = driver.findElement(By.id("Email"));
        actions.moveToElement(eMail).click().sendKeys(email).perform();

        WebElement password = driver.findElement(By.id("Password"));
        actions.moveToElement(password).click().sendKeys("password").perform();

        WebElement confirmPassword = driver.findElement(By.id("ConfirmPassword"));
        actions.moveToElement(confirmPassword).click().sendKeys("password").perform();

        WebElement registerButton = driver.findElement(By.id("register-button"));
        registerButton.click();

        WebElement confirmation = driver.findElement(By.xpath("//li[text()='The specified email already exists']"));
        Assertions.assertEquals("The specified email already exists", confirmation.getText());
    }

    @Test
    @Order(3)
    public void loginSuccessfully() {
        registerSuccessfully();

        WebElement login = driver.findElement(By.linkText("Log in"));
        login.click();

        WebElement eMail = driver.findElement(By.id("Email"));
        actions.moveToElement(eMail).click().sendKeys(email).perform();

        WebElement password = driver.findElement(By.id("Password"));
        actions.moveToElement(password).click().sendKeys("password").perform();

        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();

        WebElement logout = driver.findElement(By.linkText("Log out"));
        Assertions.assertTrue(logout.isDisplayed());

        logout.click();
    }

    @Test
    @Order(4)
    public void loginWithInvalidCredentials() {

        WebElement login = driver.findElement(By.linkText("Log in"));
        login.click();

        WebElement eMail = driver.findElement(By.id("Email"));
        actions.moveToElement(eMail).click().sendKeys("wrong" + ((int) (Math.random() * 10000)) + "@testing.com").perform();

        WebElement password = driver.findElement(By.id("Password"));
        actions.moveToElement(password).click().sendKeys("wrongpass").perform();

        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();

        WebElement confirmation = driver.findElement(By.xpath("//span[text()='Login was unsuccessful. Please correct the errors and try again.']"));
        Assertions.assertEquals("Login was unsuccessful. Please correct the errors and try again.", confirmation.getText());
    }

    @Test
    @Order(5)
    public void addLaptopToCart() {
        registerSuccessfully();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement login = driver.findElement(By.linkText("Log in"));
        login.click();

        WebElement eMail = driver.findElement(By.id("Email"));
        actions.moveToElement(eMail).click().sendKeys(email).perform();

        WebElement password = driver.findElement(By.id("Password"));
        actions.moveToElement(password).click().sendKeys("password").perform();

        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();

        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.linkText("Log out")));

        WebElement computers = driver.findElement(By.linkText("COMPUTERS"));
        actions.moveToElement(computers).perform();

        WebElement notebooks = driver.findElement(By.linkText("Notebooks"));
        notebooks.click();

        WebElement product = driver.findElement(By.xpath("(//div[@class='details']//a)[1]"));
        product.click();

        WebElement addToCart = driver.findElement(By.id("add-to-cart-button-31"));
        addToCart.click();

        WebElement confirmation = driver.findElement(By.xpath("//p[contains(text(),'The product has been added to your')]"));
        Assertions.assertTrue(confirmation.getText().contains("The product has been added to your"));
    }
}
