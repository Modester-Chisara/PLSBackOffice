package PLSBackOffice.step_definitions;


import PLSBackOffice.pages.LoginPage;
import PLSBackOffice.utilities.DriveUtilities.PlaywrightDriver;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Login_steps {
    Page page = PlaywrightDriver.getPage();
    LoginPage loginPage = new LoginPage(page);


    @Given("User enter to {string}")
    public void user_enter_to(String appURL) {
       // assertTrue(page.url().contains(appURL));

    }
    @And("User types {string} as username")
    public void userTypesAsUsername(String userName) {
        loginPage.username.type(userName);
    }

    @And("User types {string} as password")
    public void userTypesAsPassword(String password) {
        loginPage.password.type(password);

    }

    @When("User clicks on {string} button")
    public void userClicksOnButton() {



    }

    @Then("User navigates to the home page with title {string}")
    public void userNavigatesToTheHomePageWithTitle(String arg0) {
    }

    @When("User clicks on login button")
    public void userClicksOnLoginButton() {
        loginPage.loginBtn.click();
    }
}
