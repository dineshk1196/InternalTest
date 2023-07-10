package helper;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckBoxOrRadioButtonHelper {

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    private static Logger log = LoggerFactory.getLogger(CheckBoxOrRadioButtonHelper.class);


    public void selectCheckBox(By locator) {
        log.info(String.valueOf(locator));
        selectCheckBox(getDriver().findElement(locator));
    }

    public void unSelectCheckBox(By locator) {
        log.info(String.valueOf(locator));
        unSelectCheckBox(getDriver().findElement(locator));
    }

    public boolean isIselected(By locator) {
        log.info(String.valueOf(locator));
        return isIselected(getDriver().findElement(locator));
    }

    public boolean isIselected(WebElement element) {
        boolean flag = element.isSelected();
        log.info(String.valueOf(flag));
        return flag;
    }

    public void selectCheckBox(WebElement element) {
        if (!isIselected(element))
            element.click();
        log.info(String.valueOf(element));
    }

    public void unSelectCheckBox(WebElement element) {
        if (isIselected(element))
            element.click();
        log.info(String.valueOf(element));
    }

}
