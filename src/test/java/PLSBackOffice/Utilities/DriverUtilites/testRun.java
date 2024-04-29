package PLSBackOffice.Utilities.DriverUtilites;


import PLSBackOffice.Utilities.ExcelReader;
import PLSBackOffice.pages.DashboardPage;
import PLSBackOffice.pages.LoginPage;
import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;


import static PLSBackOffice.Utilities.APIUtilities.GenerateToken.apiLogin;
import static PLSBackOffice.Utilities.CommonFunctions.dateFormatter;
import static PLSBackOffice.Utilities.ConfigReader.readProperty;
import static PLSBackOffice.Utilities.DriverUtilites.ThreadSafeDriver.getPage;
import static PLSBackOffice.pages.BasePage.timeOut;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class testRun {
    Page page;
    DashboardPage dashboardPage;
    LoginPage loginPage;

    static {
        baseURI = readProperty("api_uat2");
    }

    @Test
    public void test2() {
        page = getPage();
        loginPage = new LoginPage(page);
        dashboardPage = new DashboardPage(page);
        page.navigate(readProperty("uat2-axes-server"));
        loginPage.login("Client2");
        try {
            Reader in = new FileReader("src/test/resources/downloads/csvExport-2022-09-23.csv");
            Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withHeader("Updated", "ISIN", "CUSIP", "Security Name", "Bid Spd", "Bid Yld", "Bid Px", "Bid Size", "Bid Dlrs", "Net Dlr Pos.", "Ask Dlrs", "Ask Size", "Ask Px", "Ask Yld", "Ask Spd", "Ccy", "Maturity", "Security", "Issuer", "Sector", "Country of Issuer", "Floating Rate Note", "2 Sided")
                    .withFirstRecordAsHeader()
                    .parse(in);
            int count = 0;
            for (CSVRecord record : csvRecords) {
                Map<String, String> map = record.toMap();
                dashboardPage.moveTheMouseDownInAxeGridGrabSingleData(page, dashboardPage.axeGrid_ListOfASKPrice.nth(0), count);
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test5() {
        page = getPage();

        loginPage = new LoginPage(page);
        dashboardPage = new DashboardPage(page);
        page.navigate(readProperty("uat2-axes-server"));
        loginPage.login("Client2");
        timeOut(3);
        loginPage.searchFacet_Side.click();
        loginPage.searchFacet_ListOfSide.nth(2).click();
        loginPage.searchFacet_SearchQueryBtn.click();
        timeOut(3);

        String type = "Raw Data";
        dashboardPage.userClicksOn("Export");
        dashboardPage.userClickOnToExport("Download " + type);
        dashboardPage.verifyDownloadAndSave(type);

        page.close();
        timeOut(3);
        if (type.equalsIgnoreCase("Axe Report")) {
            ExcelReader excelReader = new ExcelReader("src/test/resources/downloads/" + dateFormatter(LocalDate.now()) + "/" + type + "-" + LocalDate.now() + ".xlsx");
            System.out.println("excelReader.getRowCount(\"" + type + "\") = " + excelReader.getRowCount(type));
        } else {
            try {
//                Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withHeader("Sec Name", "ISIN", "CUSIP", " Security", " Ticker", " Send Date Time", " Dealer", "Side", "Amount", " More",
//                                " Price", "Yield", " Bench Spd", "Type", "Status", "Comment", "Sender Sub ID",
//                                "Sender Location", " Timestamp", " Target", "Ref ID", "Prev ID", "Sec Type", "Firm", "Benchmark", "ASW Spd=",
//                                "OIS Spd", "Z Spd", "Disc Mgn", "I Spd", "OA Spd", " G Spd", " CDS Basis", " CDS Int Basis", " Other RVM", "ASW Spd Side=",
//                                "OIS Spd Side", "Z Spd Side", "Disc Mgn Side", "I Spd Side", "OA Spd Side",
//                                "G Spd Side", "CDS Basis Side", "CDS Int Basis Side", "Other RVM Side", "Yield Type",
//                                "Ccy", " Entity Name", " Sector", " Country of Issuer", "Country of Risk", "Region",
//                                "Asset Class", "Maturity Date", "Coupon", "Seniority", "Emerging Market", "FRN",
//                                "ABS Classification=", "ABS Type", "ABS Agency", "Credit Rating", "ESG Classification"
//                        ).withFirstRecordAsHeader()
//                        .parse(in);

                Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/downloads/" + dateFormatter(LocalDate.now()) + "/" + type + "-" + LocalDate.now() + ".csv"));

                Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
                Map<String, String> map = new LinkedHashMap<>();
                int count = 0;
                for (CSVRecord record : csvRecords) {
                    map = record.toMap();
                    if (count == 0) {
                        break;
                    }
                    count++;
                }
                System.out.println("map.size() = " + map.size());
                System.out.println("map = " + map);
                System.out.println("count = " + count);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void apiTest() {
        given()
                .header("SESSION_AUTH_TOKEN", apiLogin("Client3"))
                .header("SOURCE_REF", new Faker().number().randomDigitNotZero())
                .queryParam("REQUEST.CURRENCY", "USD")
                .queryParam("REQUEST.SECURITY", "USH42097DJ36")
                .accept("application/json").
                when()
                .get("/req_axes_data").
                then()
                .log().body()
        ;

    }
}
