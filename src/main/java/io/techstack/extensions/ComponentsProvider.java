package io.techstack.extensions;

import org.openqa.selenium.WebElement;

import io.techstack.components.base.BaseWebComponent;

public interface ComponentsProvider {

    default <T extends BaseWebComponent> WebElement getComponent() {
        return null;
    }

    default <T extends BaseWebComponent> WebElement getComponent(String identifier) {
        return null;
    }
}
