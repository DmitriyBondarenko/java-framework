package io.techstack.providers.extensions;

import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.InvocationTargetException;

import io.techstack.providers.driver.DriverWrapper;

public interface PagesProvider {

    default <P> P nowAt(Class<P> pageClass) {
        P page =  instantiatePage(pageClass);
        PageFactory.initElements((DriverWrapper) this, page);
        return page;
    }

    private <P> P instantiatePage(Class<P> pageClassToProxy) {
        try {
            return pageClassToProxy.getConstructor(DriverWrapper.class).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException(String.format("Unable to instantiate page: %s", pageClassToProxy.getName()));
    }
}
