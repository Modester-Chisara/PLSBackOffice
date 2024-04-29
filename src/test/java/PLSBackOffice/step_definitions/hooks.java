package PLSBackOffice.step_definitions;


import PLSBackOffice.Utilities.ConfigReader;
import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import static PLSBackOffice.Utilities.AllureReportUtilities.AllureEnvironmentWriter.allureEnvironmentWriter;
import static PLSBackOffice.Utilities.Credentials.environment;
import static PLSBackOffice.Utilities.DriverUtilites.ThreadSafeDriver.closePage;
import static PLSBackOffice.Utilities.DriverUtilites.ThreadSafeDriver.getPage;


public class hooks {
    Page page;

    static String env;

    static {
        env = "";
        if (System.getProperty("env") == null) {
            env = ConfigReader.readProperty("uat2-axes-server");
        } else {
            env = environment(System.getProperty("env"));
        }
    }


    /**
     * @BeforeAll method creates environment.xml file for Allure report
     * Can find it in target/allure-results
     */
    @BeforeAll
    public static void beforeAll() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Environment", "UAT2");
        map.put("Browser", "Chromium-Headless");
        map.put("URL", "https://neptx.genesislab.global/");
        allureEnvironmentWriter(map);
    }

    /**
     * @Before method runs before every scneario execution
     * Build Playwright page and navigate to main page
     * With help of If statement, can pass url from cli as default framework works on UAT2
     * To do that,
     *          mvn clean test -Denv=<Env URL>
     */
    @Before
    public void setup() {
//        DatabaseCon.createDataBaseConnection();
        System.out.println("ENVIRONMENT = " + env.toUpperCase());
        page = getPage();
        page.navigate(env);
        page.waitForTimeout(2000);
    }

    /**
     * @After method runs after every scneario execution
     * Closes the playwrigth drivers
     * Add screenshot attachment to the allure-report
     * if the scneario FAILED.
     * @param scenario
     */
    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println(scenario.getName() + " IS FAILED");
            System.out.println("SCREENSHOT CAPTURED FOR :" + scenario.getName());
            Allure.addAttachment(scenario.getName(), new ByteArrayInputStream(page.screenshot()));
        }
//        closeDataBaseConnection();
        closePage();
    }
}
