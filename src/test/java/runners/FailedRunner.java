package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/featuresToReRun.txt",
        glue = {"io.techstack.steps"},
        plugin = {"rerun:target/featuresToReRun.txt"})
public class FailedRunner { }
