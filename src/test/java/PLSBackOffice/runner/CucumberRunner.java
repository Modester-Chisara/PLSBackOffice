package PLSBackOffice.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


/**
 * @tag1 or @tag2 --> With or keyword, It will run the scenario if it has either one of tags.
 * @tag1 and @tag1 --> With and keyword, it will run the scenario if it has BOTH OF THE TAGS at the same time
 * @tag1 and not @tag1 --> With and not keyword, it will run the tags has first side, it will
 * EXCLUDE the scenarios that has the tag that comes after "and not"
 * <p>
 * This framework can execute by click play button here or,
 * executing mvn commands like;
 * mvn clean test,
 * mvn clean test -Dbrowser=<browser>,
 * mvn clean test -Dbrower=<browser> -Dset-viewport-width=<width> -Denv=<ENV>
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty", "json:target/cucumber-report/cucumber.json",
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        features = "src/test/resources/Features",
        glue = "PLSBackOffice/step_definitions",
        dryRun = false,
        tags = "@TEST_NEPX-1671"
)

public class CucumberRunner {
}
