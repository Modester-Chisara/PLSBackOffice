package PLSBackOffice.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BasePage {
    private final Page page;
    private final Locator watchlistsPage;
    private final Locator analyticsPage;
    private final Locator cdsPage;
    private final Locator adminPage;
    private final Locator dashboardPage;
    public final Locator quickSearchBar;
    public final Locator quickSearch_ClearBtn;
    public final Locator combobox;

    public BasePage(Page page) {
        this.page = page;
        this.watchlistsPage = page.locator("fast-option#option-13");
        this.analyticsPage = page.locator("fast-option#option-14");
        this.cdsPage = page.locator("fast-option#option-15");
        this.adminPage = page.locator("fast-option#option-16");
        this.dashboardPage = page.locator("fast-option#option-12");
        this.quickSearchBar = page.locator("input[aria-haspopup='listbox']");
        this.quickSearch_ClearBtn = page.locator("zero-button.search-clear-button");
        this.combobox = page.locator("zero-search-bar-combobox");



    }

    public void navigateToMainModules(String module) {
        switch (module) {
            case "Watchlists":
                watchlistsPage.click();
                break;
            case "Analytics":
                analyticsPage.click();
                break;
            case "Admin":
                adminPage.click();
                break;
            case "Dashboard":
                dashboardPage.click();
                break;
            case "CDS":
                cdsPage.click();
                break;
        }
    }



}
