package PLSBackOffice.step_definitions;


import PLSBackOffice.Utilities.DriverUtilites.ThreadSafeDriver;
import PLSBackOffice.pages.WatchlistPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


import static PLSBackOffice.pages.BasePage.timeOut;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;

public class Watchlist_steps {
    Page page = ThreadSafeDriver.getPage();
    WatchlistPage watchlistPage = new WatchlistPage(page);


    @Given("User enters Security {string} to the Add securities pop-up")
    public void user_enters_security_info_isin_cusip_name_to_the_add_securities_pop_up(String securityINFO) {
        timeOut(3);
        watchlistPage.addSecuritiesModal_searchInput.type(securityINFO);
        watchlistPage.addSecuritiesModal_securityList.nth(0).click();
    }

    @And("User clicks on the {string} button on Watchlists")
    public void userClicksOnAddSecuritiesButtonOnWatchlists(String buttonType) {
        watchlistPage.clicksOn(buttonType);
    }

    @Then("User views the single security in the Watchlists {string}")
    public void userViewsTheSingleSecurityInTheWatchlists(String security) {
        watchlistPage.verifyQuickISINSearch(security);
    }

    @Given("User selects securities to remove from the Watchlist {string}")
    public void user_selects_securities_to_remove_from_the_watchlist(String ISIN) {
        timeOut(1);
        watchlistPage.quickSearchBar.nth(0).type(ISIN);
        timeOut(2);
        watchlistPage.quickSearchDropdown_Security.click();
        timeOut(3);
        watchlistPage.selectSingleSecurities(ISIN);
    }


    @Then("User will no longer see the security on the WatchList")
    public void user_will_no_longer_see_the_security_on_the_watch_list() {
        assertThat(watchlistPage.axeGrid_NoRowsToShow).isVisible();
    }

    @Given("User clicks on the override button from the opening File Upload pop-up")
    public void user_clicks_on_either_override_or_add_button_from_the_opening_pop_up() {
        watchlistPage.fileUpload();
    }

    @Given("User navigates to {string} Page")
    public void user_navigates_to_watchlist_settings_page(String title) {
        assertThat(page).hasTitle(title);
    }

    @Given("User toggle on {string} for all securities")
    public void user_makes_some_changes_on_the_settings_of_watch_list(String toggleType) {
        watchlistPage.toggleOn(toggleType);
    }


    @Then("User navigates back to Watchlist with saving changes")
    public void user_navigates_back_to_watchlist_with_saving_changes() {
        watchlistPage.clicksOn("Watchlists Settings");
        watchlistPage.verifySwitchClassAttribute();
    }


    @Then("User views the uploaded securities in the Watchlists")
    public void userViewsTheUploadedSecuritiesInTheWatchlists() {
        page.reload();
        assertTrue(watchlistPage.axeGrid_NoRowsToShow.isHidden());
    }
}
