package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdminPageElements {
    public AdminPageElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Login Page Elements
    @FindBy(id = "Email")
    public WebElement emailInput;

    @FindBy(id = "Password")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Log in']")
    public WebElement loginButton;

    @FindBy(linkText = "Logout")
    public WebElement logoutLink;

    // Dashboard Navigation
    @FindBy(xpath = "//ul[@class='nav nav-pills nav-sidebar flex-column nav-legacy']/li")
    public List<WebElement> navMenu;

    @FindBy(xpath = "//li[@class='nav-item has-treeview menu-is-opening menu-open']//li")
    public List<WebElement> navAltMenu;

    // Customer Management
    @FindBy(xpath = "//a[@href='/Admin/Customer/List']")
    public WebElement customersMenu;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    public WebElement addNewButton;

    @FindBy(id = "SearchEmail")
    public WebElement searchEmail;

    @FindBy(id = "search-customers")
    public WebElement searchButton;

    @FindBy(xpath = "(//td[@class='button-column'])[1]//a")
    public WebElement editButton;

    @FindBy(xpath = "//button[@name='save']")
    public WebElement saveButton;

    @FindBy(css = ".alert-success")
    public WebElement successMessage;

    @FindBy(xpath = "//button[@class='btn btn-danger float-right']")
    public WebElement deleteButton;

    @FindBy(xpath = "//button[@class='btn btn-danger']")
    public WebElement deleteConfirm;

    // Search Shipments
    @FindBy(xpath = "//input[@placeholder='Search']")
    public WebElement searchBox;

    @FindBy(xpath = "//strong[text()='Shipments']")
    public WebElement searchShipments;

    @FindBy(className = "float-left")
    public WebElement shipmentsTextConfirm;
}
