package CucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/Features/placeValidations.feature",
        glue={"StepDefinitions"},
plugin = "json:target/jsonReports/cucumber-report.json")
public class TestRunner {
}
