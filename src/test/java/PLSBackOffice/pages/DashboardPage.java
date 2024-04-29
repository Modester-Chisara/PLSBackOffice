package PLSBackOffice.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static PLSBackOffice.Utilities.CommonFunctions.dateFormatter;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;

public class DashboardPage extends BasePage {
    private final Page page;
    private Download download;
    public final Locator saveQueryBtn;
    public final Locator saveQueryModal_nameQueryInputBox;
    public final Locator saveQueryModal_saveQueryBtn;
    public final Locator saveQueryModal_cancelBtn;
    public final Locator saveQueryModal_alert;
    public final Locator HideAdvanced;
    public final Locator ShowAdvanced;
    public final Locator SearchFacetModal;
    public final Locator shareQueryBtn;
    public final Locator rawDataBtn;
    public final Locator axeReportBtn;
    public final Locator csvExportBtn;
    public final Locator alertModal;
    public final Locator addWatchListBtn;
    public final Locator addWatchListAlertOKBtn;
    public final Locator savedQueriesFirstItemShareBtn;


    public DashboardPage(Page page) {
        super(page);
        this.page = page;
        this.saveQueryBtn = page.locator("zero-button.save-query");
        this.saveQueryModal_nameQueryInputBox = page.locator("text=Save Query Name your query to save it. Name Query 0/26 Cancel Save Query >> input[type=text]");
        this.saveQueryModal_saveQueryBtn = page.locator("text=Cancel Save Query >> button").nth(1);
        this.saveQueryModal_cancelBtn = page.locator("text=Cancel >> button");
        this.saveQueryModal_alert = page.locator("zero-toast > span");
        this.HideAdvanced = page.locator("text=Hide Advanced");
        this.ShowAdvanced = page.locator("text=Show Advanced");
        this.SearchFacetModal = page.locator(".modal-root-element").nth(1);
        this.shareQueryBtn = page.locator("text=Share Query >> button");
        this.rawDataBtn = page.locator("text='Download Raw Data'");
        this.axeReportBtn = page.locator("text='Download Axe Report'");
        this.csvExportBtn = page.locator("text=CSV Export");
        this.alertModal = page.locator("zero-toast");
        this.addWatchListBtn = page.locator("div.search-container > zero-button >> nth=2");
        this.addWatchListAlertOKBtn = page.locator("zero-button.dialog-button");
        this.savedQueriesFirstItemShareBtn = page.locator("div.accordion-item > div > button >> nth=1");
    }


    public List<String> selectRandomMultipleSecurityCheckBox() {
        Faker faker = new Faker();
        List<String> list = new ArrayList<>();
        int x = 0;
        while (x < 5) {
            int index = faker.number().numberBetween(0, 30);
            axeGrid_SecurityCheckBoxes.nth(index).click();
            list.add(axeGrid_ListOfISIN.nth(index).textContent());
            x++;
        }
        return list;
    }

    public void getUserToShareQuery(String userName) {
        page.locator("text=" + userName + " >> div").first().click();
    }


    public void selectDropdownWSearchBar(Page page, Locator locator, Locator locatorList, String option) {
        locator.click();
        locatorList.nth(0).click();

        List<String> list = locatorList.allTextContents();
        int x = 0;
        while (x < list.size()) {
            if (list.get(x).equalsIgnoreCase(option)) {
                locatorList.nth(x).click();
                searchFacetTitle.click();
                break;
            }
            x++;
        }
    }

    public void selectDropdown(Locator locator, Locator locatorList, String option) {
        locator.click(); //zero-select
        List<String> list = locatorList.allTextContents();
        int x = 0;
        while (x < list.size()) {
            if (list.get(x).equalsIgnoreCase(option)) {
                locatorList.nth(x).click();
                break;
            }
            x++;
        }
    }

    public void verifyQuerySearch(Locator locator1, Locator locator2, String option1, String option2) {
        List<String> stringList1 = locator1.allTextContents();
        List<String> stringList2 = locator2.allTextContents();

        stringList1.forEach(x -> assertTrue(x.contains(option1)));
        stringList2.forEach(y -> assertTrue(y.contains(option2)));
    }

    public void userClicksOn(String buttonType) {
        switch (buttonType) {
            case "Search Query":
//                timeOut(3);
//
                searchFacet_SearchQueryBtn.click();
                page.waitForTimeout(4000);
                break;
            case "Share Query":
                shareQueryBtn.click();
                break;
            case "Clear":
                searchFacet_ClearBtn.click();
                break;
            case "Hide Advanced":
                HideAdvanced.click();
                break;
            case "Show Advanced":
                HideAdvanced.click();
                ShowAdvanced.click();
                break;
            case "Export":
                exportBtn.click();
                break;
            case "Add to Watchlist":
                addWatchListBtn.click();
                addWatchListAlertOKBtn.click();
                break;
        }
    }

    public void userClickOnToExport(String buttonType) {
        int time = 60 * 1000;
        switch (buttonType) {
            case "Download Raw Data":
                download = page.waitForDownload(new Page.WaitForDownloadOptions().setTimeout(time), rawDataBtn::click);
                break;
            case "Download Axe Report":
                download = page.waitForDownload(new Page.WaitForDownloadOptions().setTimeout(time), axeReportBtn::click);
                break;
            case "Export Cell Data":
                timeOut(2);
                download = page.waitForDownload(new Page.WaitForDownloadOptions().setTimeout(time), csvExportBtn::click);
                break;
        }
    }

    public void verifyDownloadAndSave(String fileType) {
        if ((fileType.equalsIgnoreCase("Axe Report") && alertModal.isVisible() && alertModal.innerText().contains("Download completed"))) {
            System.out.println(alertModal.innerText());
            // Save downloaded file somewhere
            download.saveAs(Paths.get("src/test/resources/downloads/" + dateFormatter(LocalDate.now()) + "/" + fileType + "-" + LocalDate.now() + ".xlsx"));
        } else if (fileType.equalsIgnoreCase("CSV Export") ||
                (fileType.equalsIgnoreCase("Raw Data") && alertModal.isVisible() && alertModal.innerText().contains("Download completed"))
        ) {
            download.saveAs(Paths.get("src/test/resources/downloads/" + dateFormatter(LocalDate.now()) + "/" + fileType + "-" + LocalDate.now() + ".csv"));
        }
    }

    public void enterValueToMinMaxInput(String inputBox, String min, String max) {
        switch (inputBox) {
            case "Price":
                searchFacet_ListOfMinInputs.nth(0).fill(min);
                searchFacet_ListOfMaxInputs.nth(0).fill(max);
                break;
            case "Spread":
                searchFacet_ListOfMinInputs.nth(1).fill(min);
                searchFacet_ListOfMaxInputs.nth(1).fill(max);
                break;
            case "Yield":
                searchFacet_ListOfMinInputs.nth(2).fill(min);
                searchFacet_ListOfMaxInputs.nth(2).fill(max);
                break;
            case "Amount":
                searchFacet_ListOfMinInputs.nth(3).fill(min);
                searchFacet_ListOfMaxInputs.nth(3).fill(max);
                break;
        }
    }

    public void verifyTheAmount(String strMin, String strMax, String side) {
        int min = Integer.parseInt(strMin);
        int max = Integer.parseInt(strMax);
        axeGrid_ListOfASKPrice.nth(0).hover(new Locator.HoverOptions()
                .setForce(true)
        );
        List<String> bidPrice = new ArrayList<>();
        List<String> askPrice = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            page.mouse().wheel(0, 1250);
            timeOut(1);
            bidPrice.addAll(axeGrid_ListOfBIDPrice.allTextContents());
            askPrice.addAll(axeGrid_ListOfASKPrice.allTextContents());
        }

        if (side.equalsIgnoreCase("bid")) {
            for (String s : bidPrice) {
                double eachBidPrice = Double.parseDouble(s);
                assertTrue(eachBidPrice >= min && eachBidPrice <= max);
            }
        }
        if (side.equalsIgnoreCase("ask")) {
            for (String s : askPrice) {
                double eachAskPrice = Double.parseDouble(s);
                assertTrue(eachAskPrice >= min && eachAskPrice <= max);
            }
        }
    }

    public String saveQuery() {
        String queryName = "Test" + new Faker().number().numberBetween(0, 10000);
        saveQueryBtn.click();
        saveQueryModal_nameQueryInputBox.type(queryName);
        saveQueryModal_saveQueryBtn.click();
        timeOut(3);
        assertThat(saveQueryModal_alert).isVisible();
        return queryName;
    }

    public void filterOutBySavedQuery(String queryName) {
        searchFacet_SavedQueries.click();
        List<String> queryList = searchFacet_ListMyQueries.allTextContents();
        int count = 0;
        while (true) {
            if (queryList.get(count).trim().equalsIgnoreCase(queryName)) {
                searchFacet_ListMyQueries.nth(count).click();
                break;
            }
            count++;
        }
    }

    public void verifySavedQuerySearchMoveMouseHorizontal(Locator locator1, Locator locator2, String value1, String value2) {
        axeGrid_ListOfASKPrice.nth(0).hover(new Locator.HoverOptions()
                .setForce(true)
        );
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            page.mouse().wheel(1250, 0);
        }

        for (int i = 0; i < 5; i++) {
            page.mouse().wheel(0, 1250);
            timeOut(1);
            list1.addAll(locator1.allTextContents());
            list2.addAll(locator2.allTextContents());
        }

        list1.forEach(x -> assertTrue(x.contains(value1)));
        list2.forEach(y -> assertTrue(y.contains(value2)));
    }


}


