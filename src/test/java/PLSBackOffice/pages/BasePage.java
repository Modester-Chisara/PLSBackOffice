package PLSBackOffice.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

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
    public final Locator searchFacetTitle;
    public final Locator searchFacet_Security;
    public final Locator searchFacet_Issuer;
    public final Locator searchFacet_Side;
    public final Locator searchFacet_ListOfSide;
    public final Locator searchFacet_Currency;
    public final Locator searchFacet_ListOfCurrencies;
    public final Locator searchFacet_Sector;
    public final Locator searchFacet_ListOfSector;
    public final Locator searchFacet_ClearBtn;
    public final Locator searchFacet_SearchQueryBtn;
    public final Locator searchFacet_ListOfMinInputs;
    public final Locator searchFacet_ListOfMaxInputs;
    public final Locator searchFacet_ListOfCreditRatings;
    public final Locator searchFacet_ListOfMarketOptions;
    public final Locator searchFacet_ListOfDealersOptions;
    public final Locator searchFacet_ListOfTypeOptions;
    public final Locator searchFacet_ListOfBondTypesOptions;
    public final Locator searchFacet_ListOfCountriesOfIssuerOptions;
    public final Locator searchFacet_ListOfCountriesOfRiskOptions;
    public final Locator searchFacet_ListOfRegionsOptions;
    public final Locator searchFacet_ListOfSeniorityOptions;
    public final Locator searchFacet_ListOfCouponTypesOptions;
    public final Locator searchFacet_LEI;
    public final Locator searchFacet_FRN;
    public final Locator searchFacet_Valuation;
    public final Locator searchFacet_Regulation;
    public final Locator searchFacet_Rule;
    public final Locator searchFacet_SavedQueries;
    public final Locator searchFacet_ListMyQueries;
    public final Locator searchFacet_PerpFlag;
    public final Locator searchFacet_AssetClass;
    public final Locator searchFacet_ListOfAssetClass;
    public final Locator axeGrid_ListOfISIN;
    public final Locator axeGrid_ListOfCUSIP;
    public final Locator axeGrid_ListOfSECNAME;
    public final Locator axeGrid_ListOfCurrency;
    public final Locator axeGrid_ListOfSector;
    public final Locator axeGrid_ListOfIssuer;
    public final Locator axeGrid_ListOfBIDPrice;
    public final Locator axeGrid_ListOfASKPrice;
    public final Locator axeGrid_SecurityCheckBoxes;
    public final Locator axeGrid_NoRowsToShow;
    public final Locator axeGrid_Header;
    public final Locator axeGrid_ListOfRows;
    public final Locator axeGrid_ListOfMaturity;
    public final Locator axeGrid_ListOfCountryOfIssuer;
    public final Locator axeGrid_ListOfBIDSpread;
    public final Locator axeGrid_ListOfBIDYield;
    public final Locator axeGrid_ListOfBIDSize;
    public final Locator axeGrid_ListOfBIDDealrs;
    public final Locator axeGrid_ListOfASKSpread;
    public final Locator axeGrid_ListOfASKYield;
    public final Locator axeGrid_ListOfASKSize;
    public final Locator axeGrid_ListOfASKDealrs;
    public final Locator axeGridTab;
    public final Locator exportBtn;
    public final Locator quickSearchDropdown_Security;
    public final Locator quickSearchDropdown_SecurityName;
    public final Locator quickSearchDropdown_Ticker;

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
        this.searchFacet_Security = page.locator("input[name='secId']");
        //TODO this locator for v1.5.x
//        this.searchFacet_Issuer = page.locator("input[name='Entity Names']");
//        //TODO this locator for v1.6.x+
        this.searchFacet_Issuer = page.locator("input[name='Entity Names'] >> nth=1");
        this.searchFacet_Side = page.locator("zero-select[name=searchSide]");
        this.searchFacet_ListOfSide = page.locator("zero-select.field-input > zero-option");
        this.searchFacet_Currency = page.locator("zero-multiselect-combobox[name=Currencies]");
        this.searchFacet_ListOfCurrencies = page.locator("zero-multiselect-combobox[name=Currencies] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_Sector = page.locator("zero-multiselect-combobox[name='Sector']");
        this.searchFacet_ListOfSector = page.locator("zero-multiselect-combobox[name='Sector'] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_ClearBtn = page.locator("zero-button.back-button");
        this.searchFacet_SearchQueryBtn = page.locator("zero-button.confirm-button");
        this.searchFacet_ListOfMinInputs = page.locator("input[placeholder='Min']");
        this.searchFacet_ListOfMaxInputs = page.locator("input[placeholder='Max']");
        this.searchFacet_PerpFlag = page.locator("text=Perpetual");
        this.searchFacet_AssetClass = page.locator("zero-multiselect-combobox[name='Asset Classes']");
        this.searchFacet_ListOfAssetClass = page.locator("zero-multiselect-combobox[name='Asset Classes'] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_ListOfCreditRatings = page.locator("zero-multiselect-combobox[name='Credit Ratings'] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_ListOfMarketOptions = page.locator("zero-multiselect-combobox[name='Markets'] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_ListOfDealersOptions = page.locator("zero-multiselect-combobox[name='Dealers'] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_ListOfTypeOptions = page.locator("zero-multiselect-combobox[name='Type'] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_ListOfBondTypesOptions = page.locator("zero-multiselect-combobox[name='Bond Types'] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_ListOfCountriesOfIssuerOptions = page.locator("zero-multiselect-combobox[name='Countries of Issuer'] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_ListOfCountriesOfRiskOptions = page.locator("zero-multiselect-combobox[name='Countries of Risk'] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_ListOfRegionsOptions = page.locator("zero-multiselect-combobox[name='Regions'] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_ListOfSeniorityOptions = page.locator("zero-multiselect-combobox[name='Seniorities'] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_ListOfCouponTypesOptions = page.locator("zero-multiselect-combobox[name='Coupon Types'] > div > div:nth-child(2) > div > zero-checkbox");
        this.searchFacet_LEI = page.locator("input[name='LEIs']");
        this.searchFacet_FRN = page.locator("text=FRN");
        this.searchFacet_Valuation = page.locator("text=Valuation");
        this.searchFacet_Regulation = page.locator("text=Regulation S");
        this.searchFacet_Rule = page.locator("text=Rule 144A");
        this.searchFacet_SavedQueries = page.locator("zero-tab#queries");
        this.searchFacet_ListMyQueries = page.locator("div.accordion-item > .my-query-name");
        this.searchFacetTitle = page.locator("h2.title >> nth=1");
        this.axeGrid_ListOfISIN = page.locator("div.ag-center-cols-container > div > div[col-id=ISIN_CODE]");
        this.axeGrid_ListOfCUSIP = page.locator("div.ag-center-cols-container > div > div[col-id=CUSIP]");
        this.axeGrid_ListOfSECNAME = page.locator("div.ag-center-cols-container > div > div[col-id=SEC_NAME]");
        this.axeGrid_ListOfCurrency = page.locator("div.ag-center-cols-container > div > div[col-id=ISO_CURRENCY_ID]");
        this.axeGrid_ListOfSector = page.locator("div.ag-center-cols-container > div > div[col-id=MARKET_SECTOR]");
        this.axeGrid_ListOfIssuer = page.locator("div.ag-center-cols-container > div > div[col-id=ENTITY_NAME]");
        this.axeGrid_ListOfMaturity = page.locator("div.ag-center-cols-container > div > div[col-id=MATURITY_DATE]");
        this.axeGrid_ListOfCountryOfIssuer = page.locator("div.ag-center-cols-container > div > div[col-id=ISSUE_COUNTRY]");
        this.axeGrid_ListOfRows = page.locator("div.ag-center-cols-viewport > .ag-center-cols-container > div");
        this.axeGrid_ListOfBIDPrice = page.locator("div.ag-center-cols-container > div > div[col-id='BID_PX']");
        this.axeGrid_ListOfBIDSpread = page.locator("div.ag-center-cols-container > div > div[col-id='BID_SPD']");
        this.axeGrid_ListOfBIDYield = page.locator("div.ag-center-cols-container > div > div[col-id='BID_YLD']");
        this.axeGrid_ListOfBIDSize = page.locator("div.ag-center-cols-container > div > div[col-id='BID_SIZE']");
        this.axeGrid_ListOfBIDDealrs = page.locator("div.ag-center-cols-container > div > div[col-id='BID_DLRS']");
        this.axeGrid_ListOfASKPrice = page.locator("div.ag-center-cols-container > div > div[col-id='ASK_PX']");
        this.axeGrid_ListOfASKSpread = page.locator("div.ag-center-cols-container > div > div[col-id='ASK_SPD']");
        this.axeGrid_ListOfASKYield = page.locator("div.ag-center-cols-container > div > div[col-id='ASK_YLD']");
        this.axeGrid_ListOfASKSize = page.locator("div.ag-center-cols-container > div > div[col-id='ASK_SIZE']");
        this.axeGrid_ListOfASKDealrs = page.locator("div.ag-center-cols-container > div > div[col-id='ASK_DLRS']");
        this.axeGrid_SecurityCheckBoxes = page.locator(".ag-selection-checkbox");
        this.axeGrid_NoRowsToShow = page.locator("i.far.fa-frown");
        this.axeGrid_Header = page.locator("div.ag-header-container > div:nth-child(1) > div");
        this.axeGridTab = page.locator("zero-tab#AXES");
        this.exportBtn = page.locator("text=Export");
        this.quickSearchDropdown_Security = page.locator("fast-option[value=ISIN_OR_CUSIP_AS_SECURITY]");
        this.quickSearchDropdown_SecurityName = page.locator("fast-option[value=SEC_NAME]");
        this.quickSearchDropdown_Ticker = page.locator("fast-option[value=BBG_ENTITY_TICKER]");

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

    public int getSizeOfTheGridColumn() {
        timeOut(2);
        return page.locator("div.ag-center-cols-container > div:nth-child(1) > div").count();
    }

    public int getSizeOfTheGridRow() {
        return Integer.parseInt(axeGrid_ListOfRows.last().getAttribute("row-index"));
    }

    public Locator getListOf_AxeGrid_SECFullData(int index, int secondIndex) {
        return page.locator("div.ag-center-cols-container > div:nth-child(" + index + ") > div >> nth=" + secondIndex);
    }

    /**
     * The new quickSearchDropdown v1.6.x+
     *
     * @param value String argument to decided which
     *              options to click from
     *              quick search dropdown
     *              <p>
     *              This quicksearch method for v1.5.x
     */
//    public void quickSearchDropdown(String value) {
//        page.locator("fast-option[value=" + value + "]").click();
//    }
    public void setQuickSearchBar(String searchType, String searchVal) {
        axeGridTab.click();
        quickSearch_ClearBtn.click();
        quickSearchBar.type(searchVal);

        if (searchType.equalsIgnoreCase("isin") || searchType.equalsIgnoreCase("cusip")) {
            quickSearchDropdown_Security.click();
        }
        if (searchType.equalsIgnoreCase("secname")) {
            quickSearchDropdown_SecurityName.click();
        }
        if (searchType.equalsIgnoreCase("ticker")) {
            quickSearchDropdown_Ticker.click();
        }

        page.waitForTimeout(1000);
        waitForAxeGridLoaded();
    }

    public void selectQuickSearchDropdown(String value) {
        if (value.equalsIgnoreCase("isin") || value.equalsIgnoreCase("cusip")) {
            quickSearchDropdown_Security.click();
        }
        if (value.equalsIgnoreCase("security_name")) {
            quickSearchDropdown_SecurityName.click();
        }
        if (value.equalsIgnoreCase("ticker")) {
            quickSearchDropdown_Ticker.click();
        }
    }

    /**
     * @param searchValue
     * @param searchType
     */
    public void matchQuickSearchWithResult(String searchValue, String searchType) {
        String cellsActualValue = "";
        timeOut(2);

        if (searchType.equalsIgnoreCase("isin")) {
            cellsActualValue = axeGrid_ListOfISIN.nth(0).textContent();
        }
        if (searchType.equalsIgnoreCase("cusip")) {
            cellsActualValue = axeGrid_ListOfCUSIP.nth(0).textContent();

        }
        if (searchType.equalsIgnoreCase("security_name")) {
            cellsActualValue = axeGrid_ListOfSECNAME.nth(0).textContent();

        }
        assertTrue(cellsActualValue.contains(searchValue));
    }

    public static void timeOut(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, String>> moveTheMouseDownInAxeGridGrabSingleData(Page page, Locator locatorToHover, int index) {

        locatorToHover.hover();
        List<Map<String, String>> mapList = new ArrayList<>();

        for (int i = 0; i < getSizeOfTheGridColumn(); i++) {
            timeOut(1);
            mapList.add(getMappedData(index));
            page.mouse().wheel(0, 1250);
            System.out.println("mapList = " + mapList);
        }

        return mapList;
    }

    Map<String, String> getMappedData(int index) {
        Map<String, String> map = new LinkedHashMap<>();

        int count = 0;
        while (count < getSizeOfTheGridColumn()) {
            map.put(axeGrid_Header.nth(count).innerText(), getListOf_AxeGrid_SECFullData(index + 1, count).innerText());

            count++;
        }

        System.out.println(map);
        return map;
    }

    public void deleteSavedQuery(String queryName) {
        searchFacet_SavedQueries.click();
        timeOut(3);
        List<String> queryList = searchFacet_ListMyQueries.allTextContents();
        int y = 0;

        int count = 0;
        while (true) {
            if (queryList.get(count).trim().equalsIgnoreCase(queryName)) {
                searchFacet_ListMyQueries.nth(count).click();
                break;
            }
            count++;
        }
        timeOut(3);
    }

    public void waitForAxeGridLoaded() {
        page.waitForSelector("div.ag-center-cols-container > div > div[col-id=ISIN_CODE] >> nth=0");
    }

}
