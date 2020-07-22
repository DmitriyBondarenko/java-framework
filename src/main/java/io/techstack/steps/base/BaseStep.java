package io.techstack.steps.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.techstack.providers.driver.DriverProvider;
import io.techstack.utils.PropertyReader;

public class BaseStep {
    private final DriverProvider _driverProvider;

    public BaseStep(DriverProvider driverProvider) {
        _driverProvider = driverProvider;
    }

    @Before
    public void setUp() {
      DriverProvider.browserList.addDriver(_driverProvider.getInstance());
    }

    @After(order = 0)
    public void tearDown() {
        _driverProvider.destroy();
    }

    @After(order = 1)
    public void takeScreenshotIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            File screenshot = _driverProvider.getInstance().getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File(String.format("%s/%s.png",
                        PropertyReader.getProperty("screenshots.folder"),
                        scenario.getName())));
            } catch (IOException e) {
                throw new RuntimeException(String.format("Unable to create a screenshot. %s", e.getMessage()));
            }
        }
    }
}
