package io.techstack.components;

import org.openqa.selenium.By;

import io.techstack.components.base.BaseWebComponent;

public class Button extends BaseWebComponent {

    @Override
    protected By construct() {
        var selector = String.format(".//input[@value = '%s']", identifier);
        return By.xpath(selector);
    }
}
