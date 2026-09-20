import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;

public class IteraTestSuite extends BaseDriver {
    private final String usernameRandom = "suite" + java.util.UUID.randomUUID().toString().replace("-", "");

    @Test
    public void shouldRegisterSuccessfully() {

        driver.get(System.getProperty("itera.url", "https://itera-qa.azurewebsites.net/"));

        String emailRandom = "testing" + (int)(Math.random() * 10000) + "@testing.com";

        WebElement signUp = driver.findElement(By.xpath("//a[text()='Sign Up']"));
        signUp.click();

        WebElement firstName = driver.findElement(By.xpath("//input[@data-val-required='Please enter first name']"));
        firstName.sendKeys("Kerem");

        WebElement surname = driver.findElement(By.xpath("//input[@data-val-required='Please enter surname']"));
        surname.sendKeys("Yigit");

        WebElement eMail = driver.findElement(By.xpath("//input[@id='E_post']"));
        eMail.sendKeys(emailRandom);

        WebElement phoneNumber = driver.findElement(By.xpath("//input[@id='Mobile']"));
        phoneNumber.sendKeys("123456");

        WebElement userName = driver.findElement(By.xpath("//input[@id='Username']"));
        userName.sendKeys(usernameRandom);

        WebElement password = driver.findElement(By.xpath("//input[@id='Password']"));
        password.sendKeys("123456");

        WebElement confirmPassword = driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
        confirmPassword.sendKeys("123456");

        WebElement submit = driver.findElement(By.xpath("//input[@id='submit']"));
        submit.click();

        WebElement confirmationMessage = driver.findElement(By.xpath("//label[text()='Registration Successful']"));

        Assertions.assertEquals("Registration Successful", confirmationMessage.getText(),
                "Registration was not successful.");
    }

    @Test
    public void shouldLoginSuccessfully() {
        shouldRegisterSuccessfully();

        driver.get(System.getProperty("itera.url", "https://itera-qa.azurewebsites.net/"));

        WebElement login = driver.findElement(By.xpath("//a[text()='Login']"));
        login.click();

        WebElement username = driver.findElement(By.xpath("//input[@data-val-required='Please enter username']"));
        username.sendKeys(usernameRandom);

        WebElement password = driver.findElement(By.xpath("//input[@data-val-required='Please enter password']"));
        password.sendKeys("123456");

        WebElement loginButton = driver.findElement(By.xpath("//input[@name='login']"));
        loginButton.click();

        WebElement confirmation = driver.findElement(By.xpath("//h3[starts-with(normalize-space(), 'Welcome')]"));

        Assertions.assertEquals("Welcome " + usernameRandom, confirmation.getText(), "Login was not successful.");

        WebElement logoutButton = driver.findElement(By.xpath("//a[text()='Log out']"));
        logoutButton.click();
    }

    @Test
    public void shouldCreateNewCustomer() {
        shouldRegisterSuccessfully();

        driver.get(System.getProperty("itera.url", "https://itera-qa.azurewebsites.net/"));

        WebElement login = driver.findElement(By.xpath("//a[text()='Login']"));
        login.click();

        WebElement username = driver.findElement(By.xpath("//input[@data-val-required='Please enter username']"));
        username.sendKeys(usernameRandom);

        WebElement password = driver.findElement(By.xpath("//input[@data-val-required='Please enter password']"));
        password.sendKeys("123456");

        WebElement loginButton = driver.findElement(By.xpath("//input[@name='login']"));
        loginButton.click();

        WebElement createButton = driver.findElement(By.xpath("//a[text()='Create New']"));
        createButton.click();

        WebElement name = driver.findElement(By.xpath("//input[@id='Name']"));
        name.sendKeys("Said");

        WebElement company = driver.findElement(By.xpath("//input[@id='Company']"));
        company.sendKeys("Company");

        WebElement address = driver.findElement(By.xpath("//input[@id='Address']"));
        address.sendKeys("Norway");

        WebElement city = driver.findElement(By.xpath("//input[@id='City']"));
        city.sendKeys("Oslo");

        WebElement phone = driver.findElement(By.xpath("//input[@id='Phone']"));
        phone.sendKeys("123456");

        WebElement eMail = driver.findElement(By.xpath("//input[@id='Email']"));
        eMail.sendKeys("123456@kmail.com");

        WebElement createButton2 = driver.findElement(By.xpath("//input[@value='Create']"));
        createButton2.click();
        Assertions.assertFalse(driver.findElements(By.xpath("//td[normalize-space()='Said']")).isEmpty(), "Created customer should appear in the list");


    }
}
