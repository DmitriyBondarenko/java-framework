package io.techstack.steps.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.techstack.providers.driver.DriverProvider;
import io.techstack.providers.driver.DriverWrapper;
import io.techstack.utils.BrowserList;
import io.techstack.utils.PropertyReader;

public class BaseStep {
    private final BrowserList _browserList;
    private final DriverProvider _driverProvider;

    public BaseStep(BrowserList browserList, DriverProvider driverProvider) {
        _browserList = browserList;
        _driverProvider = driverProvider;
    }

    @Before
    public void setUp() {
      _browserList.addDriver(_driverProvider.getInstance());
    }

    @After(order = 0)
    public void tearDown() {
        _browserList.getAllBrowsers().forEach(DriverWrapper::quiteDriver);
    }

    @After(order = 1)
    public void takeScreenshotIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            for (DriverWrapper driver : _browserList.getAllBrowsers()) {
                File screenshot = driver.getScreenshotAs(OutputType.FILE);
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
}
