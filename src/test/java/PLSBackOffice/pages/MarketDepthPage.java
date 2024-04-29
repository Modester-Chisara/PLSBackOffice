package PLSBackOffice.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import static PLSBackOffice.Utilities.CommonFunctions.*;
import static org.junit.Assert.assertTrue;

public class MarketDepthPage extends BasePage {
    private final Page page;
    public final Locator marketDepthHeader;
    public final Locator instrumentDetail_SecurityName;
    public final Locator instrumentDetail_ISIN;
    public final Locator instrumentDetail_Issuer;
    public final Locator instrumentDetail_Currency;
    public final Locator instrumentDetail_IssuerCountry;
    public final Locator instrumentDetail_Sector;
    public final Locator instrumentDetail_MaturityDate;
    public final Locator excludeCancels_ON;
    public final Locator excludeCancels_OFF;
    public final Locator securityStatus;
    public final Locator viewDealerInterest_Live;
    public final Locator viewDealerInterest_1W;
    public final Locator viewDealerInterest_1M;
    public final Locator dateTimeHeader;
    public final Locator date_BID;
    public final Locator date_ASK;


    public MarketDepthPage(Page page) {
        super(page);
        this.page = page;
        this.marketDepthHeader = page.locator(".header-title");
        this.instrumentDetail_SecurityName = page.locator(".security-label > .header");
        this.instrumentDetail_ISIN = page.locator(".security-label > .field-label");
        this.instrumentDetail_Issuer = page.locator(".layout > div > .field-content > .field-value >> nth=0");
        this.instrumentDetail_Currency = page.locator(".layout > div > .field-content > .field-value >> nth=2");
        this.instrumentDetail_IssuerCountry = page.locator(".layout > div > .field-content > .field-value >> nth=8");
        this.instrumentDetail_Sector = page.locator(".layout > div > .field-content > .field-value >> nth=5");
        this.instrumentDetail_MaturityDate = page.locator(".layout > div > .field-content > .field-value >> nth=3");
        this.excludeCancels_ON = page.locator("zero-segmented-item[value=on]");
        this.excludeCancels_OFF = page.locator("zero-segmented-item[value=off]");
        this.viewDealerInterest_Live = page.locator("zero-segmented-item[value=AXES_FLATTEN_LIVE]");
        this.viewDealerInterest_1W = page.locator("zero-segmented-item[value=AXES_FLATTEN_ONE_WEEK]");
        this.viewDealerInterest_1M = page.locator("zero-segmented-item[value=AXES_FLATTEN_ONE_MONTH]");
        this.securityStatus = page.locator("div.ag-center-cols-container > div > div[col-id=AXE_STATUS]");
        this.dateTimeHeader = page.locator(".ag-header-row > div[col-id=SEND_DATETIME]");
        this.date_BID = page.locator(".dealer-bid >> .ag-center-cols-container> div > div[col-id=DATE]");
        this.date_ASK = page.locator(".dealer-ask >> .ag-center-cols-container> div > div[col-id=DATE]");

    }

    public Map<String, String> getInstrumentDetails() {
        Map<String, String> instrumentMap = new LinkedHashMap<>();
        instrumentMap.put("instrumentDetail_ISIN", instrumentDetail_ISIN.innerText().trim());
        instrumentMap.put("instrumentDetail_SecurityName", instrumentDetail_SecurityName.innerText().trim());
        instrumentMap.put("instrumentDetail_Currency", instrumentDetail_Currency.innerText().trim());
        instrumentMap.put("instrumentDetail_MaturityDate", dateFormatter(instrumentDetail_MaturityDate.innerText().trim()));
        instrumentMap.put("instrumentDetail_Issuer", instrumentDetail_Issuer.innerText().trim());
        instrumentMap.put("instrumentDetail_Sector", instrumentDetail_Sector.innerText().trim());
        instrumentMap.put("instrumentDetail_IssuerCountry", instrumentDetail_IssuerCountry.innerText().trim());
        return instrumentMap;
    }


    public String clickOnRandomSecurity() {
        int r = new Faker().number().numberBetween(0, getSizeOfTheGridRow());
        String ISIN = axeGrid_ListOfISIN.nth(r).textContent();
        axeGrid_ListOfISIN.nth(r).click();
        return ISIN;
    }

    public Locator getlistOfPrice(String side) {
        return page.locator(".dealer-" + side.toLowerCase() + " >> .ag-center-cols-container >> div[col-id='PRICE']");
    }

    public Locator getlistOfSize(String side) {
        return page.locator(".dealer-" + side.toLowerCase() + " >> .ag-center-cols-container >> div[col-id='AMOUNT']");
    }

    public Locator getlistOfSpread(String side) {
        return page.locator(".dealer-" + side.toLowerCase() + " >> .ag-center-cols-container >> div[col-id='BENCH_SPD']");
    }

    public Locator getlistOfYield(String side) {
        return page.locator(".dealer-" + side.toLowerCase() + " >> .ag-center-cols-container >> div[col-id='AXE_YIELD']");
    }

    public Map<String, Object> getMarketDepthNumbers(String side) {

        Map<String, Object> bidMap = new LinkedHashMap<>();
        Map<String, Object> askMap = new LinkedHashMap<>();
        if (side.equalsIgnoreCase("bid")) {
            bidMap.put("Size", addSizeList(getlistOfSize("bid")));
            bidMap.put("Price", findMaxOfDoubleList(getlistOfPrice("bid")));
            bidMap.put("Spread", findMinOfDoubleList(getlistOfSpread("bid")));
            bidMap.put("Yield", findMinOfDoubleList(getlistOfYield("bid")));
            return bidMap;
        }
        if (side.equalsIgnoreCase("ask")) {
            askMap.put("Size", addSizeList(getlistOfSize("ask")));
            askMap.put("Price", findMinOfDoubleList(getlistOfPrice("ask")));
            askMap.put("Spread", findMaxOfDoubleList(getlistOfSpread("ask")));
            askMap.put("Yield", findMaxOfDoubleList(getlistOfYield("ask")));
            return askMap;
        }
        return new LinkedHashMap<>();
    }

    public boolean verifyStatusListIncludesCancels() {
        List<String> statusList = securityStatus.allTextContents();
        int countCXL = 0;
        int i = 0;
        while (i < statusList.size()) {
            if (statusList.get(i).equalsIgnoreCase("CXL")) {
                countCXL++;
            }
            i++;
        }
        if (countCXL == 0) {
            return false;
        }
        return true;
    }

    public void verifyViewDealerInterestToogle(String toggleType, String ISIN) {
        timeOut(3);
        List<String> bidDateList = date_BID.allInnerTexts();
        List<String> askDateList = date_ASK.allInnerTexts();

        String bidFirstDate = "";
        String bidLastDate = "";
        String askFirstDate = "";
        String askLastDate = "";

        if (bidDateList.size() != 0 && askDateList.size() != 0) {
            bidFirstDate = bidDateList.get(0).substring(0, 2);
            bidLastDate = bidDateList.get(bidDateList.size() - 1).substring(0, 2);
            askFirstDate = askDateList.get(0).substring(0, 2);
            askLastDate = askDateList.get(askDateList.size() - 1).substring(0, 2);

            int bidDateDiff = Integer.parseInt(bidFirstDate) - Integer.parseInt(bidLastDate);
            int askDateDiff = Integer.parseInt(askFirstDate) - Integer.parseInt(askLastDate);

            if (toggleType.equalsIgnoreCase("1w")) {
                assertTrue(bidDateDiff <= 7 && askDateDiff <= 7);
            }

            if (toggleType.equalsIgnoreCase("1m")) {
                assertTrue(bidDateDiff <= 30 && askDateDiff <= 30);
            }
        }

        if (bidDateList.size() == 0) {
            if (askDateList.size() == 1) {
                System.out.println("THERE IS ONLY ONE ASK SIDE DATE FOR " + ISIN + ", DATE= " + askDateList.get(0));
            } else {
                System.out.println("THERE IS NO BID SIDE DATE FOR " + ISIN);
                askFirstDate = askDateList.get(0).substring(0, 2);
                askLastDate = askDateList.get(askDateList.size() - 1).substring(0, 2);
                int askDateDiff = Integer.parseInt(askFirstDate) - Integer.parseInt(askLastDate);

                if (toggleType.equalsIgnoreCase("1w")) {
                    assertTrue(askDateDiff <= 7);
                }

                if (toggleType.equalsIgnoreCase("1m")) {
                    assertTrue(askDateDiff <= 30);
                }
            }

        }

        if (askDateList.size() == 0) {
            if (bidDateList.size() == 1) {
                System.out.println("THERE IS ONLY ONE BID SIDE DATE FOR " + ISIN + ", DATE= " + bidDateList.get(0));
            } else {
                System.out.println("THERE IS NO ASK SIDE DATE FOR " + ISIN);
                bidFirstDate = bidDateList.get(0).substring(0, 2);
                bidLastDate = bidDateList.get(bidDateList.size() - 1).substring(0, 2);
                int bidDateDiff = Integer.parseInt(bidFirstDate) - Integer.parseInt(bidLastDate);

                if (toggleType.equalsIgnoreCase("1w")) {
                    assertTrue(bidDateDiff <= 7);
                }

                if (toggleType.equalsIgnoreCase("1m")) {
                    assertTrue(bidDateDiff <= 30);
                }
            }

        }
    }


}
