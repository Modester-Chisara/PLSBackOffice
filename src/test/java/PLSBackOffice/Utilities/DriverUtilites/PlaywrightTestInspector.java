package PLSBackOffice.Utilities.DriverUtilites;


import PLSBackOffice.Utilities.ConfigReader;
import PLSBackOffice.pages.DashboardPage;
import PLSBackOffice.pages.LoginPage;
import com.microsoft.playwright.Page;

public class PlaywrightTestInspector {
    private static Page page;
    static LoginPage loginPage;
    static DashboardPage dashboardPage;

    static {
        page = ThreadSafeDriver.getPage();
        loginPage = new LoginPage(page);
        dashboardPage = new DashboardPage(page);
    }

    public static void main(String[] args) {
        codeGen();
    }

    public static void codeGen() {
        page.navigate(ConfigReader.readProperty("uat2-axes-server"));
        loginPage.login("Client2");
        page.pause();
    }


}
