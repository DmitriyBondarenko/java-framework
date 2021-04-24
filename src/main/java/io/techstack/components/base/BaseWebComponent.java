package io.techstack.components.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.techstack.components.interfaces.IContextContainer;
import io.techstack.components.interfaces.IWebComponent;
import io.techstack.providers.driver.DriverWrapper;

public abstract class BaseWebComponent implements IWebComponent, IContextContainer {
    protected WebElement component;

    protected BaseWebComponent() {};

    public WebElement instance;
    public DriverWrapper driver;
    public String identifier;
    public By context;
    public By frame;
    protected By parentSelector;
    protected WebElement parent;

    public void build() {
        instance = driver.waitForElementToBeDisplayed(construct());
    }
    protected abstract By construct();
}
