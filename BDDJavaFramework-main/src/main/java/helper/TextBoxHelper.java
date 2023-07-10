package helper;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextBoxHelper{
    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    private static Logger log = LoggerFactory.getLogger(TextBoxHelper.class);

    public WebElement getElement(By locator) {
        if (IsElementPresentQuick(locator))
            return getDriver().findElement(locator);

        try {
            throw new NoSuchElementException("Element Not Found : " + locator);
        } catch (RuntimeException re) {
            log.error(String.valueOf(re));
            throw re;
        }
    }


    public boolean IsElementPresentQuick(By locator) {
        boolean flag = getDriver().findElements(locator).size() >= 1;
        return flag;
    }


}