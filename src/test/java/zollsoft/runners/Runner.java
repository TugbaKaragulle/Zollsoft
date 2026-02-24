package zollsoft.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "zollsoft.stepDefinitions",
        tags = "@Bewerbung",
        plugin = {
                "html:target/reports/html_report.html",
                "rerun:target/failed_tests/failed_scenarios.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "pretty"},
        publish = true,
        dryRun = false
)
public class Runner extends AbstractTestNGCucumberTests {


}