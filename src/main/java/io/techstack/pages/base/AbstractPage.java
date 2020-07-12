package io.techstack.pages.base;

import org.openqa.selenium.support.PageFactory;

import io.techstack.providers.driver.DriverWrapper;

public abstract class AbstractPage {
    protected DriverWrapper driver;

    public AbstractPage(DriverWrapper driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
