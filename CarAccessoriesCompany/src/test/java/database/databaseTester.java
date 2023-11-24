package database;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/databaseFeatures", monochrome = true,snippets = CucumberOptions.SnippetType.CAMELCASE, glue = {"database"})
public class databaseTester {
}
