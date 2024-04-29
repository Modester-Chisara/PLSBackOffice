package PLSBackOffice.step_definitions;


import PLSBackOffice.Utilities.DriverUtilites.ThreadSafeDriver;
import PLSBackOffice.pages.LoginPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;

public class MainModules_steps {
    Page page = ThreadSafeDriver.getPage();
    LoginPage loginPage = new LoginPage(page);

    @Given("{string} logged into the app {string}")
    public void logged_into_the_app(String user, String appUrl) {
        assertTrue(page.url().contains(appUrl));
        loginPage.login(user);
    }

    @When("clicks to {string} module")
    public void clicks_to_module(String module) {
        loginPage.navigateToMainModules(module);
    }

    @Then("views the url changes to {string}")
    public void views_the_url_changes_to(String url) {
        assertThat(page).hasURL(url);
    }

}
