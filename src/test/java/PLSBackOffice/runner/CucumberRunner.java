package PLSBackOffice.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = {
                    "pretty", "json:target/cucumber-report/cucumber.json",
                    "html:target/cucumber-report.html",
                    "rerun:target/rerun.txt",
                    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
            },
            features = "src/test/Resources/Features",
            glue = "PLSBackOffice/step_definitions",
            dryRun = false,
            tags = "@TEST_NEPX-1671"
    )

    public class CucumberRunner {
    }

