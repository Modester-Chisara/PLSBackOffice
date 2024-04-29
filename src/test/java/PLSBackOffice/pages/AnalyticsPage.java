package PLSBackOffice.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AnalyticsPage {
    private final Page page;
    public final Locator filters_Security;
    public final Locator filters_Issuer;
    public final Locator filters_AnySide;
    public final Locator filters_BID;
    public final Locator filters_ASK;
    public final Locator clearFiltersBtn;
    public final Locator filterBtn;
    public final Locator hideAdvancedBtn;
    public final Locator showAdvancedBtn;
    public final Locator calendar;


    public AnalyticsPage(Page page) {
        this.page = page;
        this.filters_Security = page.locator("input[name=secId]");
        this.filters_Issuer = page.locator("input[name=Entity Names]");
        this.filters_AnySide = page.locator("zero-option#option-18");
        this.filters_BID = page.locator("zero-option#option-19");
        this.filters_ASK = page.locator("zero-option#option-20");
        this.clearFiltersBtn = page.locator("text=Clear Filter >> button");
        this.filterBtn = page.locator("text=Filter >> button");
        this.hideAdvancedBtn = page.locator("text=Hide Advanced >> button");
        this.showAdvancedBtn = page.locator("text=Show Advanced >> button");
        this.calendar = page.locator("zero-neptune-text-field >> input >> nth=2");
    }
}
