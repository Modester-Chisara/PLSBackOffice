package PLSBackOffice.step_definitions;


import PLSBackOffice.Utilities.DriverUtilites.ThreadSafeDriver;
import PLSBackOffice.pages.DashboardPage;
import PLSBackOffice.pages.WatchlistPage;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.MouseButton;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;

public class Dashboard_steps {

    Page page = ThreadSafeDriver.getPage();
    DashboardPage dashboardPage = new DashboardPage(page);
    WatchlistPage watchlistPage = new WatchlistPage(page);
    private String queryName;
    private List<String> listISIN;

    @When("User enters {string} as search value on quick search input box")
    public void user_enters_nums_of_security_isin_on_quick_search_input_box(String searchValue) {
        dashboardPage.waitForAxeGridLoaded();
        dashboardPage.quickSearchBar.type(searchValue);
    }

    @When("User selects from dropdown as {string}")
    public void user_selects_from_dropdown_as(String value) {
        dashboardPage.selectQuickSearchDropdown(value);
    }

    @Then("User views searched {string} of {string} with matching results")
    public void user_views_searched_nums_of_isin_with_matching_results(String searchValue, String searchType) {
        dashboardPage.matchQuickSearchWithResult(searchValue, searchType);
    }

    @When("User clicks on the Clear button on Axe Grid")
    public void user_clicks_on_the_clear_button_on_axe_grid() {
        dashboardPage.quickSearch_ClearBtn.click();
    }

    @Then("Quick search input box cleared from Search filter")
    public void quick_search_input_box_cleared_from_search_filter() {
        assertTrue(dashboardPage.combobox.getAttribute("current-value").equalsIgnoreCase(""));
    }

    @Given("User enters ISIN on the {string} input box on the Search Facet container")
    public void user_enters_isin_on_the_input_box_on_the_search_facet_container(String ISIN) {
        dashboardPage.waitForAxeGridLoaded();
        dashboardPage.searchFacet_Security.type(ISIN);
    }

    @Given("User selects Currency {string} from the dropdown")
    public void user_selects_currency_from_the_dropdown(String currency) {
        dashboardPage.selectDropdownWSearchBar(page, dashboardPage.searchFacet_Currency, dashboardPage.searchFacet_ListOfCurrencies, currency);
    }

    @When("User clicks on the {string} button")
    public void user_clicks_on_the_button(String buttonType) {
        dashboardPage.userClicksOn(buttonType);
    }

    @Then("User views responded data to Search Query on Axe Grid expected as ISIN {string} and Currency {string}")
    public void user_views_responded_data_to_search_query_on_axe_grid(String ISIN, String Currency) {
        dashboardPage.verifyQuerySearch(dashboardPage.axeGrid_ListOfISIN, dashboardPage.axeGrid_ListOfCurrency, ISIN, Currency);
    }

    @Given("User selects Sector {string} from the dropdown box")
    public void user_selects_sector_from_the_dropdown_box(String sector) {
        dashboardPage.selectDropdownWSearchBar(page, dashboardPage.searchFacet_Sector, dashboardPage.searchFacet_ListOfSector, sector);
    }

    @Then("User views responded data to Search Query on Axe Grid expected as Sector {string} and Currency {string}")
    public void user_views_responded_data_to_search_query_on_axe_grid_as_Sector_and_Currency(String Sector, String Currency) {
        dashboardPage.verifyQuerySearch(dashboardPage.axeGrid_ListOfSector, dashboardPage.axeGrid_ListOfCurrency, Sector, Currency);
    }

    @Given("User selects Side {string} from the dropdown")
    public void user_selects_side_from_the_dropdown(String side) {
        dashboardPage.selectDropdown(dashboardPage.searchFacet_Side, dashboardPage.searchFacet_ListOfSide, side);
    }

    @Given("User enters min Price {string} and max Price {string} on to {string} input box")
    public void user_enters_min_price_on_to_input_box(String minPrice, String maxPrice, String inputBox) {
        dashboardPage.enterValueToMinMaxInput(inputBox, minPrice, maxPrice);
    }

    @Then("User views responded data to Search Query on Axe Grid expected as Min Price {string}, Max Price {string} and Side {string}")
    public void userViewsRespondedDataToSearchQueryOnAxeGridExpectedAsMinPriceMaxPriceAndSide(String minPx, String maxPx, String side) {
        dashboardPage.verifyTheAmount(minPx, maxPx, side);
    }

    @Given("User saves the query")
    public void user_saves_the_query() {
        queryName = dashboardPage.saveQuery();
    }

    @When("User filters out with saved query from my queries list")
    public void userFiltersOutWithSavedQueryFromMyQueriesList() {
        dashboardPage.filterOutBySavedQuery(queryName);
    }

    @Then("User views responded data to saved query on Axe Grid expected as Currency {string} and Sector {string}")
    public void userViewsRespondedDataToSavedQueryOnAxeGridExpectedAsSideCurrencyAndSector(String currency, String sector) {
        dashboardPage.verifySavedQuerySearchMoveMouseHorizontal(dashboardPage.axeGrid_ListOfCurrency, dashboardPage.axeGrid_ListOfSector, currency, sector);
    }

    @Then("User views responded data to saved query on Axe Grid expected as Security {string} and Sector {string}")
    public void userViewsRespondedDataToSavedQueryOnAxeGridExpectedAsSecurityAndCurrency(String security, String sector) {
        dashboardPage.verifySavedQuerySearchMoveMouseHorizontal(dashboardPage.axeGrid_ListOfIssuer, dashboardPage.axeGrid_ListOfSector, security, sector);
    }

    @Then("User views the Search Facets container will be disappear")
    public void user_views_the_search_facets_container_will_be_disappear() {
        assertThat(dashboardPage.SearchFacetModal).isHidden();
    }

    @Then("User views the Search Facets container will be enabled to create query search")
    public void user_views_the_search_facets_container_will_be_enabled_to_create_query_search() {
        assertThat(dashboardPage.SearchFacetModal).isVisible();
    }

    @Given("User enters Security Name on the {string} input box on the Search Facet container")
    public void user_enters_security_name_on_the_input_box_on_the_search_facet_container(String issuer) {
        dashboardPage.searchFacet_Issuer.type(issuer);
    }

    @Given("User navigates to Saved Queries on Search Facets container")
    public void user_navigates_to_on_search_facets_container() {
        dashboardPage.searchFacet_SavedQueries.click();
    }

    @Given("User clicks on the Shared button on the dedicated Query")
    public void user_clicks_on_the_button_on_the_dedicated_query() {
        dashboardPage.savedQueriesFirstItemShareBtn.click();
    }

    @Given("User selects users from the list")
    public void user_selects_users_from_the_list() {
        dashboardPage.getUserToShareQuery("Client One");
    }

    @Then("User receives a pop-up message saying Saved Query Shared with query information")
    public void user_receives_a_pop_up_message_saying_with_query_information() {
        assertThat(dashboardPage.alertModal).isVisible();
    }

    @When("User clicks on {string} from the dropdown")
    public void user_clicks_on_from_the_dropdown(String buttonType) {
        dashboardPage.userClickOnToExport(buttonType);
    }

    @Then("User gets a confirmation message and {string} downloaded with matching data")
    public void user_gets_a_confirmation_message_and_raw_data_downloaded_with_matching_data(String fileType) {
        dashboardPage.verifyDownloadAndSave(fileType);
    }

    @Given("User right-clicks anywhere inside of the Axe Grid")
    public void user_right_clicks_anywhere_inside_of_the_axe_grid() {
        dashboardPage.axeGrid_ListOfBIDPrice.nth(0).elementHandle().click(new ElementHandle.ClickOptions().setButton(MouseButton.RIGHT));
    }


    @Given("User selects a multiple security checkbox")
    public void user_selects_a_multiple_security_checkbox() {
        listISIN = dashboardPage.selectRandomMultipleSecurityCheckBox();
    }

    @Then("User views the security in the Watchlists")
    public void user_views_the_security_in_the_watchlists() {
        int count = 0;
        while (count < listISIN.size()) {
            watchlistPage.verifyQuickISINSearch(listISIN.get(count));
            count++;
        }
    }

    @Then("The system display No Rows To Show modal")
    public void theSystemDisplayNoRowsToShowModal() {
        assertThat(dashboardPage.axeGrid_NoRowsToShow).isVisible();
    }

    @And("User enters {string} reference to security input box")
    public void userEntersSecurityReferenceToSecurityInputBox(String securityReference) {
        dashboardPage.searchFacet_Security.type(securityReference);
    }

    @Then("The system display No Axes Available Modal")
    public void theSystemDisplayNoAxesAvailableModal() {
        assertThat(page.locator("text='No Axes Available'")).isVisible();
    }

    @And("User hit enter")
    public void userHitEnter() {
        dashboardPage.waitForAxeGridLoaded();
        page.keyboard().press("Enter");
    }
}
