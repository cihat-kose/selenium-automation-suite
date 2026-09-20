package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;

public class Locaters extends Parent {
    public Locaters() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

    @FindBy(linkText = "Register")
    public WebElement registerLink;
    @FindBy(id = "customer.firstName")
    public WebElement firstName;
    @FindBy(id = "customer.lastName")
    public WebElement lastName;
    @FindBy(id = "customer.address.street")
    public WebElement address;
    @FindBy(id = "customer.address.city")
    public WebElement city;
    @FindBy(id = "customer.address.state")
    public WebElement state;
    @FindBy(id = "customer.address.zipCode")
    public WebElement zipCode;
    @FindBy(id = "customer.phoneNumber")
    public WebElement phone;
    @FindBy(id = "customer.ssn")
    public WebElement SSN;
    @FindBy(id = "customer.username")
    public WebElement username;
    @FindBy(id = "customer.password")
    public WebElement password;
    @FindBy(id = "repeatedPassword")
    public WebElement confirm;
    @FindBy(xpath = "(//input[@class='button'])[2]")
    public WebElement registerButton;
    @FindBy(xpath = "//p[text()='Your account was created successfully. You are now logged in.']")
    public WebElement successMessage;
    @FindBy(xpath = "//input[@name='username']")
    public WebElement loginUsername;
    @FindBy(xpath = "//input[@name='password']")
    public WebElement loginPassword;
    @FindBy(xpath = "(//input[@type='submit'])[1]")
    public WebElement loginButton;
    @FindBy(xpath = "//a[text()='Log Out']")
    public WebElement logoutLink;
    @FindBy(xpath = "//b[text()='Welcome']")
    public WebElement loginSuccessMessage;
    @FindBy(xpath = "//p[@class='error']")
    public WebElement loginUnsuccessMessage;
    @FindBy(xpath = "//a[text()='Bill Pay']")
    public WebElement billPayLink;
    @FindBy(name = "payee.name")
    public WebElement payeeName;
    @FindBy(name = "payee.address.street")
    public WebElement billAddress;
    @FindBy(name = "payee.address.city")
    public WebElement billCity;
    @FindBy(name = "payee.address.state")
    public WebElement billState;
    @FindBy(name = "payee.address.zipCode")
    public WebElement billZipCode;
    @FindBy(name = "payee.phoneNumber")
    public WebElement billPhone;
    @FindBy(name = "payee.accountNumber")
    public WebElement billAccount;
    @FindBy(name = "verifyAccount")
    public WebElement billVerifyAccount;
    @FindBy(name = "amount")
    public WebElement billAmount;
    @FindBy(xpath = "//input[@class='button']")
    public WebElement billPaymentButton;
    @FindBy(xpath = "//h1[text()='Bill Payment Complete']")
    public WebElement billConfirmPayMessage;

    public WebElement getWebElement(String strButton) {

        switch (strButton) {
            case "payeeName":
                return payeeName;
            case "billAddress":
                return billAddress;
            case "billCity":
                return billCity;
            case "billState":
                return billState;
            case "billZipCode":
                return billZipCode;
            case "billPhone":
                return billPhone;
            case "billAccount":
                return billAccount;
            case "billVerifyAccount":
                return billVerifyAccount;
            case "billAmount":
                return billAmount;
            case "billPaymentButton":
                return billPaymentButton;
            case "billConfirmPayMessage":
                return billConfirmPayMessage;
        }
        throw new IllegalArgumentException("Unknown field: " + strButton);
    }
}
