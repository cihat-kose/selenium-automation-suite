import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPageElements;
import utility.BaseDriverParameter;

public class AdminTests extends BaseDriverParameter {

    String randomMail;

    @Test(priority = 1)
    public void loginTest() {
        AdminPageElements page = new AdminPageElements(driver);
        driver.get(System.getProperty("nopcommerce.url", "https://admin-demo.nopcommerce.com/login?"));

        Assert.assertFalse(driver.getTitle().contains("Just a moment"),
                "Public demo is behind an anti-bot challenge. Set -Dnopcommerce.url to an authorized test environment.");
        page.emailInput.clear();
        page.emailInput.sendKeys("admin@yourstore.com");

        page.passwordInput.clear();
        page.passwordInput.sendKeys("admin");

        page.loginButton.click();

        Assert.assertTrue(page.logoutLink.isDisplayed(), "Login failed!");
    }

    @Test(priority = 2, dependsOnMethods = "loginTest")
    public void checkLeftNavMenu() {
        AdminPageElements page = new AdminPageElements(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        java.util.List<org.openqa.selenium.WebElement> menus = driver.findElements(
                org.openqa.selenium.By.cssSelector(".nav-sidebar > li > a[href='#']"));
        Assert.assertFalse(menus.isEmpty(), "No expandable navigation menus found");
        for (org.openqa.selenium.WebElement menu : menus) {
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", menu);
            menu.click();
            org.openqa.selenium.WebElement submenu = menu.findElement(org.openqa.selenium.By.xpath("following-sibling::ul"));
            wait.until(ExpectedConditions.visibilityOf(submenu));
            Assert.assertTrue(submenu.isDisplayed(), "Sub-menu not visible!");
        }
    }

    @Test(priority = 3, dependsOnMethods = "loginTest")
    public void createCustomer() {
        AdminPageElements page = new AdminPageElements(driver);
        randomMail = "testuser" + java.util.UUID.randomUUID() + "@email.com";

        driver.get(java.net.URI.create(driver.getCurrentUrl()).resolve("/Admin/Customer/List").toString());
        page.addNewButton.click();

        driver.findElement(org.openqa.selenium.By.id("Email")).sendKeys(randomMail);
        driver.findElement(org.openqa.selenium.By.id("Password")).sendKeys("DemoPassword123!");
        driver.findElement(org.openqa.selenium.By.id("FirstName")).sendKeys("Automation");
        driver.findElement(org.openqa.selenium.By.id("LastName")).sendKeys("Suite");

        page.saveButton.click();
        Assert.assertTrue(page.successMessage.isDisplayed(), "Customer creation failed!");
    }
}
