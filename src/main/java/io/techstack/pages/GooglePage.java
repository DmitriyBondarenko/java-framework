package io.techstack.pages;

import org.openqa.selenium.WebDriver;

import io.techstack.pages.base.AbstractPage;

public class GooglePage extends AbstractPage {

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    public void openGoogle() {
        driver.get("www.google.com");
    }
}
