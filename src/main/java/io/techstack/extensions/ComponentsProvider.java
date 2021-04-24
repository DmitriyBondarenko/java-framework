package io.techstack.extensions;

import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;

import io.techstack.components.base.BaseWebComponent;
import io.techstack.providers.driver.DriverWrapper;

public interface ComponentsProvider {

    default <T extends BaseWebComponent> WebElement getComponent(Class<T> componentClass) {
        var component = initObject(componentClass);
        component.driver = (DriverWrapper) this;
        component.build();
        return component.instance;
    }

    default <T extends BaseWebComponent> WebElement getComponent(Class<T> componentClass, String identifier) {
        var component = initObject(componentClass);
        component.driver = (DriverWrapper) this;
        component.identifier = identifier;
        component.build();
        return component.instance;
    }

    private <T> T initObject(Class<T> objectClass) {
        T obj;
        try {
            obj = objectClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(String.format("Unable to create the new instance of %s", objectClass));
        }
        return obj;
    }
}
