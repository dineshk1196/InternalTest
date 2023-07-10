package helper;

import driver.DriverFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;

public class ButtonHelper {

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    private static Logger log = LoggerFactory.getLogger(ButtonHelper.class);

    public void click(By locator) {
        click(getDriver().findElement(locator));
        log.info("");
    }

    public void click(WebElement element) {
        try {
            element.click();
            log.info(String.valueOf(element));
        }catch(NoSuchElementException e){
            e.printStackTrace();
        }
    }

}
