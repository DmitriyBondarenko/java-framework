package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"io.techstack.steps", "io.techstack.base"},
        plugin = {"rerun:target/featuresToReRun.txt"})
public class CucumberRunner { }
