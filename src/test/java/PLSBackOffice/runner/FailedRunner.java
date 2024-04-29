package PLSBackOffice.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/rerun.txt",
        glue = "NeptuneAxes/Step_Definitions"
)

/**
 * This class will execute only the failed test scenarios
 * While running the framework if the execution fails
 * Cucumber will add that into rerun.txt file in target
 */

public class FailedRunner {

}
