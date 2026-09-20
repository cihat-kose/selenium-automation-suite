package stepDefinitions;
import io.cucumber.java.en.*;
import pages.Locaters;
public class _02_LoginSteps {
    private final Locaters loc = new Locaters();
    private String registerAndLogout() {
        String username = new _01_RegisterSteps().registerAccount();
        loc.clickFunction(loc.logoutLink);
        return username;
    }
    @When("Enter credentials for a new account")
    public void enterNewAccountCredentials() {
        String username = registerAndLogout();
        loc.sendKeysFunction(loc.loginUsername, username);
        loc.sendKeysFunction(loc.loginPassword, "password");
    }
    @When("Enter an existing username and an incorrect password")
    public void enterWrongPassword() {
        String username = registerAndLogout();
        loc.sendKeysFunction(loc.loginUsername, username);
        loc.sendKeysFunction(loc.loginPassword, "incorrect-password");
    }
    @When("Enter a nonexistent username")
    public void enterUnknownUsername() {
        loc.sendKeysFunction(loc.loginUsername, "missing-" + java.util.UUID.randomUUID());
        loc.sendKeysFunction(loc.loginPassword, "password");
    }
    @And("Click login button")
    public void clickLoginButton() { loc.clickFunction(loc.loginButton); }
    @Then("User should successfully login to the system")
    public void userShouldSuccessfullyLoginToTheSystem() { loc.verifyContainsTextFunction(loc.loginSuccessMessage, "Welcome"); }
    @And("User should logout from the system")
    public void userShouldLogoutFromTheSystem() { loc.clickFunction(loc.logoutLink); }
    @Then("An unsuccessful login message should be displayed")
    public void unsuccessfulAttemptShouldBeDisplayed() { loc.verifyContainsTextFunction(loc.loginUnsuccessMessage, "could not be verified"); }
}
