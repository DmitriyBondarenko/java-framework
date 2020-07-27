package io.techstack.providers.extensions;

import org.openqa.selenium.support.PageFactory;

import io.techstack.pages.base.AbstractPage;
import io.techstack.providers.driver.DriverWrapper;

public interface PagesProvider {

    default <P extends AbstractPage> P nowAt(Class<P> pageClass) {
        P page = PageFactory.initElements((DriverWrapper) this, pageClass);
        page.driver = (DriverWrapper) this;
        page.waitForMainPageElement();
        return page;
    }
}
