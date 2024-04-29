package PLSBackOffice.step_definitions;


import PLSBackOffice.Utilities.DriverUtilites.ThreadSafeDriver;
import PLSBackOffice.pages.AdminPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class Admin_steps {
    Page page = ThreadSafeDriver.getPage();
    AdminPage adminPage = new AdminPage(page);
    private String email;

    @When("User clicks on {string} button on Admin page")
    public void user_clicks_on_login_button(String buttonType) {
        adminPage.userClickOn(buttonType);
    }

    @Given("User fills out the required input boxes")
    public void user_fills_out_the_required_input_boxes() {
        email = adminPage.userGenerate();
    }


    @Then("User views the verification pop-up with the corresponding email")
    public void user_views_the_new_created_user_on_user_management_table_as_status_enabled() {
        assertTrue(adminPage.successMessage.textContent().contains(email));
    }

    @Given("User selects desired accounts checkbox {string}")
    public void user_selects_desired_accounts_checkbox(String account) {
        adminPage.getSpecificCheckBox(account).check();
    }

    @Then("User views the user on User Management Table as Status Disabled")
    public void user_views_the_user_on_user_management_table_as_status_disabled() {
        assertTrue(adminPage.status.textContent().contains("DISABLED"));
    }


}
