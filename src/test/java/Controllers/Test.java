package Controllers;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/cases", monochrome = true,snippets = SnippetType.CAMELCASE, glue = {"Controllers"})
public class Test {
}