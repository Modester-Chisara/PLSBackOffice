package PLSBackOffice.pages;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;

public class WatchlistPage extends BasePage {
    private final Page page;
    public final Locator addSecuritiesBtn;
    public final Locator addSecuritiesModal_searchInput;
    public final Locator addSecuritiesModal_addAllBtn;
    public final Locator addSecuritiesModal_cancelBtn;
    public final Locator addSecuritiesModal_securityList;
    public final Locator removeSecuritiesModal_OkBtn;
    public final Locator removeSecuritiesModal_removeSecurityBtn;
    public final Locator uploadBtn;
    public final Locator fileUploadModal_OverrideBtn;
    public final Locator watchlistsSettings;
    public final Locator watchlistsSettings_AlertAllToggle;
    public final Locator watchlistsSettings_NewAxeAllToggle;
    public final Locator watchlistsSettings_AxeUpdatedAllToggle;
    public final Locator watchlistsSettings_SaveBtn;
    public final Locator watchlistsSettings_Dialog;
    public final Locator watchlistsSettings_DialogSaveBtn;
    public final Locator watchlistsSettings_listOfSwitch;
    public final Locator confirmUpload_YesBtn;
    public final Locator fileOverrideModal_OkBtn;

    public WatchlistPage(Page page) {
        super(page);
        this.page = page;
        this.addSecuritiesBtn = page.locator("zero-button:has-text('Add Securities')");
        this.addSecuritiesModal_searchInput = page.locator("input[placeholder=Search]");
        this.addSecuritiesModal_addAllBtn = page.locator("zero-button:has-text('Add All')");
        this.addSecuritiesModal_cancelBtn = page.locator("zero-button:has-text('Cancel')");
        this.addSecuritiesModal_securityList = page.locator("fast-combobox > fast-option");
        this.removeSecuritiesModal_OkBtn = page.locator("zero-button:has-text('OK')");
        this.removeSecuritiesModal_removeSecurityBtn = page.locator("zero-button:has-text('Remove Security')");
        this.uploadBtn = page.locator("zero-button:has-text('Upload')");
        this.fileUploadModal_OverrideBtn = page.locator("zero-button:has-text('Override')");
        this.watchlistsSettings = page.locator("span:has-text('Watchlist Settings')");
        this.watchlistsSettings_AlertAllToggle = page.locator("zero-switch >> nth=0");
        this.watchlistsSettings_NewAxeAllToggle = page.locator("zero-switch >> nth=1");
        this.watchlistsSettings_AxeUpdatedAllToggle = page.locator("zero-switch >> nth=2");
        this.watchlistsSettings_SaveBtn = page.locator("fast-button:has-text('Save')");
        this.watchlistsSettings_Dialog = page.locator("watchlist-settings-route > zero-dialog > dialog");
        this.watchlistsSettings_DialogSaveBtn = page.locator("zero-dialog >> zero-button:has-text('Save')>> nth=0");
        this.watchlistsSettings_listOfSwitch = page.locator("zero-switch");
        this.confirmUpload_YesBtn = page.locator("zero-button:has-text('Yes')");
        this.fileOverrideModal_OkBtn = page.locator("zero-button.buttons:has-text('Ok')");
    }

    public void verifyQuickISINSearch(String value) {
        timeOut(1);
        quickSearchBar.nth(0).type(value);
        timeOut(2);
        quickSearchDropdown_Security.click();
        timeOut(3);
        matchQuickSearchWithResult(value, "ISIN");
        quickSearch_ClearBtn.click();
    }


    public void clicksOn(String buttonType) {
        switch (buttonType) {
            case "Add Securities":
                addSecuritiesBtn.click();
                break;
            case "Add All":
                addSecuritiesModal_addAllBtn.click();
                break;
            case "Cancel":
                addSecuritiesModal_cancelBtn.click();
                break;
            case "Remove Security":
                removeSecuritiesModal_removeSecurityBtn.click();
                break;
            case "Ok":
                removeSecuritiesModal_OkBtn.click();
                break;
            case "Upload":
                uploadBtn.click();
                break;
            case "Watchlists Settings":
                watchlistsSettings.click();
                break;
            case "Save":
                timeOut(1);
                watchlistsSettings_SaveBtn.click();
                assertThat(watchlistsSettings_Dialog).isVisible();
                watchlistsSettings_DialogSaveBtn.click();
                break;
        }
    }

    public void selectSingleSecurities(String ISIN) {
        if (axeGrid_ListOfISIN.nth(0).textContent().contains(ISIN)) {
            axeGrid_SecurityCheckBoxes.nth(0).click();
        }
    }

    public void fileUpload() {
        FileChooser fileChooser = page.waitForFileChooser(fileUploadModal_OverrideBtn::click);
        fileChooser.setFiles(Paths.get("src/test/resources/test-data/isins_for_watchlist.csv"));
        confirmUpload_YesBtn.click();
        fileOverrideModal_OkBtn.click();
    }

    public void toggleOn(String toggleType) {
        switch (toggleType) {
            case "Alert":
                watchlistsSettings_AlertAllToggle.click();
                break;
            case "New Axe":
                watchlistsSettings_NewAxeAllToggle.click();
                break;
            case "Axe Updated":
                watchlistsSettings_AxeUpdatedAllToggle.click();
                break;
        }
    }

    public void verifySwitchClassAttribute() {
        for (int i = 3; i < watchlistsSettings_listOfSwitch.allTextContents().size(); i++) {
            assertTrue(watchlistsSettings_listOfSwitch.nth(i).getAttribute("class").equalsIgnoreCase("checked"));
        }
    }
}



