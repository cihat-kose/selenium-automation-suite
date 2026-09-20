package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import pages.Locaters;

import java.util.List;

public class _03_BillPaySteps {
    Locaters loc = new Locaters();

    @And("Click bill pay link")
    public void clickBillPayLink() {
        loc.clickFunction(loc.billPayLink);
    }

    @And("User sending the keys in Locaters")
    public void userSendingTheKeysInLocaters(DataTable dt) {
        List<List<String>> items = dt.asLists(String.class);

        for (int i = 0; i < items.size(); i++) {
            WebElement element = loc.getWebElement(items.get(i).get(0));
            loc.sendKeysFunction(element, items.get(i).get(1));
        }
    }
    @And("Click send payment button")
    public void clicksendpaymentButton() {
        loc.clickFunction(loc.billPaymentButton);
    }

    @Then("Bill Payment Complete message should be displayed")
    public void billPaymentCompleteMessageShouldBeDisplayed() {
        loc.verifyContainsTextFunction(loc.billConfirmPayMessage,"Complete");
    }
}
