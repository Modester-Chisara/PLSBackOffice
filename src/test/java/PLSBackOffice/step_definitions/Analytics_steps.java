package PLSBackOffice.step_definitions;


import PLSBackOffice.Utilities.DriverUtilites.ThreadSafeDriver;
import PLSBackOffice.pages.AnalyticsPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Then;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Analytics_steps {
    Page page = ThreadSafeDriver.getPage();
    AnalyticsPage analyticsPage = new AnalyticsPage(page);

    @Then("User lands on Analytics Page")
    public void user_lands_on_analytics_page() {
        assertThat(page).hasURL("https://neptx.genesislab.global/analytics");
    }


}
