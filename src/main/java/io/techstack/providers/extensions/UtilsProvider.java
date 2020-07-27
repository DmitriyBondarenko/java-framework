package io.techstack.providers.extensions;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public interface UtilsProvider {

    //region Get methods
    default String getAttribute(WebElement element, String attribute) {
        try {
            return element.getAttribute(attribute);
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            return StringUtils.EMPTY;
        }
    }

    default String getElementText(WebElement element) {
        List<String> text = new ArrayList<>();
        text.add(element.getText());
        text.add(element.getAttribute("value"));
        return text.stream()
                   .filter(StringUtils::isNotEmpty)
                   .findFirst()
                   .orElse("");
    }

    default List<String> getElementsText(List<WebElement> elementList) {
        List<String> textList = new ArrayList<>();
        for (WebElement element : elementList) {
            textList.add(getElementText(element).trim());
        }

        return textList;
    }
    //endregion

    //region Is methods
    default boolean isAttributePresent(WebElement element, String attribute) {
        try {
            var value = element.getAttribute(attribute);
            return value != null;
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            return false;
        }
    }
    //endregion

    //region Checkboxes
    default boolean isCheckBoxSelected(WebElement checkbox) {
        return checkbox.isSelected();
    }

    default void checkCheckBox(WebElement checkbox) {
        if (!checkbox.isSelected())
            checkbox.click();
    }

    default void uncheckCheckBox(WebElement checkbox) {
        if (checkbox.isSelected())
            checkbox.click();
    }

    default void setCheckboxState(WebElement checkbox, boolean desiredState) {
        if (desiredState) {
            if (!checkbox.isSelected())
                checkbox.click();
        } else {
            if (checkbox.isSelected())
                checkbox.click();
        }
    }
    //endregion
}
