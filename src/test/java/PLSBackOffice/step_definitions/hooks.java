package PLSBackOffice.step_definitions;


import PLSBackOffice.utilities.ConfigReader;
import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import static PLSBackOffice.utilities.AllureReportUtilities.AllureEnvironmentWriter.allureEnvironmentWriter;
import static PLSBackOffice.utilities.Credentials.environment;
import static PLSBackOffice.utilities.DriveUtilities.PlaywrightDriver.closePage;
import static PLSBackOffice.utilities.DriveUtilities.PlaywrightDriver.getPage;


public class hooks {

        Page page;

        static String env;

        static {
            env = "";
            if (System.getProperty("env") == null) {
                env = ConfigReader.readProperty("QA");
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
            map.put("Environment", "QA");
            map.put("Browser", "Chromium-Headless");
            map.put("URL", "https://www.saucedemo.com/");
            allureEnvironmentWriter(map);
        }


        @Before
        public void setup() {
//
            System.out.println("ENVIRONMENT = " + env.toUpperCase());
            page = getPage();
            page.navigate(env);
            page.waitForTimeout(2000);
        }


        @After
        public void teardown(Scenario scenario) {
            if (scenario.isFailed()) {
                System.out.println(scenario.getName() + " IS FAILED");
                System.out.println("SCREENSHOT CAPTURED FOR :" + scenario.getName());
                Allure.addAttachment(scenario.getName(), new ByteArrayInputStream(page.screenshot()));
            }

            closePage();
        }

}
