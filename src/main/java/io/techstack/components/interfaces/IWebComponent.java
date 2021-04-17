package io.techstack.components.interfaces;

import org.openqa.selenium.By;

import io.techstack.providers.driver.DriverWrapper;

public interface IWebComponent extends IContextContainer {
    DriverWrapper driver = null;
    String identifier = null;
    By frame = null;
}
