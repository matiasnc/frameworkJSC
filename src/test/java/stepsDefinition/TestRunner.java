package stepsDefinition;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:Features",
    glue = "classpath:stepsDefinition",
    tags = "@testReport"
)

public class TestRunner {
}
