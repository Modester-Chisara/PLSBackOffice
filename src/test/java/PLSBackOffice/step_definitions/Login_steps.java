package PLSBackOffice.step_definitions;


import PLSBackOffice.Utilities.DriverUtilites.ThreadSafeDriver;
import PLSBackOffice.pages.LoginPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import static PLSBackOffice.pages.BasePage.timeOut;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;

public class Login_steps {

    Page page = ThreadSafeDriver.getPage();
    LoginPage loginPage = new LoginPage(page);

    @Given("User enter to {string}")
    public void user_enter_to(String appURL) {
        assertTrue(page.url().contains(appURL));
    }

    @Given("User types {string} as COMPID")
    public void user_types_as_compid(String COMPID) {
        timeOut(2);
        loginPage.COMPID.type(COMPID);
    }

    @Given("User types {string} as username")
    public void user_types_as_username(String username) {
        loginPage.username.type(username);
    }

    @Given("User types {string} as password")
    public void user_types_as_password(String password) {
        loginPage.password.type(password);
    }

    @When("User clicks on {string} button")
    public void user_clicks_on_button(String buttonType) {
        loginPage.userClickOn(buttonType);
    }

    @Then("User navigates to the home page with title {string}")
    public void user_navigates_to_the_home_page_with_title(String expectedTitle) {
        assertThat(page).hasTitle(expectedTitle);
    }

    @Then("User get error message saying that {string}")
    public void user_get_error_message_saying_that(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.errorMessage.textContent();
        assertTrue(actualErrorMessage.contains(expectedErrorMessage));
    }

    @Given("User navigates to {string}")
    public void user_navigates_to(String expectedURL) {
        String url = "https://neptx.genesislab.global/" + expectedURL.toLowerCase();
        assertThat(page).hasURL(url);
        if (expectedURL.equalsIgnoreCase("dashboard"))
            loginPage.waitForAxeGridLoaded();
    }

    @Given("{string} fills out the required input boxes with a new password {string}")
    public void user_fills_out_the_required_input_boxes(String user, String newPassword) {
        loginPage.updatePassword(user, newPassword);
    }

    @Then("User succesfully login and navigate to {string}")
    public void u_ser_navigates_back_to(String expectedURL) {
        assertThat(page).hasURL(expectedURL);
    }


    @Then("User successfully logged out from the app")
    public void user_successfully_logged_out_from_the_app() {
        String url = "https://neptx.genesislab.global/login";
        assertThat(page).hasURL(url);
    }

    @When("User enters new password on Login page")
    public void userEntersNewPasswordOnLoginPage() {
        loginPage.login("Client2");
    }
}
