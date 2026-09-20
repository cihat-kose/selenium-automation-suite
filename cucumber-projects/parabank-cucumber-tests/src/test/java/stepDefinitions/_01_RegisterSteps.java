package stepDefinitions;

import io.cucumber.java.en.*;
import pages.Locaters;
import utilities.GWD;

public class _01_RegisterSteps {

    Locaters loc = new Locaters();
    @Given("Navigate to the ParaBank")
    public void navigateToTheParaBank() {
        GWD.getDriver().get(System.getProperty("parabank.url", "https://parabank.parasoft.com/parabank/index.htm"));
    }

    @When("Create an account")
    public void createAnAccount() { registerAccount(); }

    public String registerAccount() {
        String username = "suite" + java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        loc.clickFunction(loc.registerLink);
        loc.sendKeysFunction(loc.firstName, "Kerem");
        loc.sendKeysFunction(loc.lastName, "Yigit");
        loc.sendKeysFunction(loc.address, "Dream street ");
        loc.sendKeysFunction(loc.city, "Oslo");
        loc.sendKeysFunction(loc.state, "Oslo");
        loc.sendKeysFunction(loc.zipCode, "12345");
        loc.sendKeysFunction(loc.phone, "98765432100");
        loc.sendKeysFunction(loc.SSN, "5634");
        loc.sendKeysFunction(loc.username, username);
        loc.sendKeysFunction(loc.password, "password");
        loc.sendKeysFunction(loc.confirm, "password");

        loc.clickFunction(loc.registerButton);
        loc.verifyContainsTextFunction(loc.successMessage, "success");
        return username;
    }

    @Then("Success message should be displayed")
    public void successMessageShouldBeDisplayed() {
        loc.verifyContainsTextFunction(loc.successMessage,"success");
    }
}
