package io.techstack.steps.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.techstack.providers.driver.DriverProvider;

public class BaseStep {
    private final DriverProvider driverProvider;

    public BaseStep(DriverProvider driverProvider) {
        this.driverProvider = driverProvider;
    }

    @Before
    public void setUp() {
       driverProvider.getInstance();
    }

    @After
    public void tearDown() {
        driverProvider.destroy();
    }
}
