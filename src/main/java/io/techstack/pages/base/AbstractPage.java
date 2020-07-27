package io.techstack.pages.base;

import io.techstack.providers.driver.DriverWrapper;

public abstract class AbstractPage {
    public DriverWrapper driver;

    public abstract void waitForMainPageElement();
}
