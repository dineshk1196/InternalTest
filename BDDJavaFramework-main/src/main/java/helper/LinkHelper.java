package helper;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkHelper extends GenericHelper {


    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    private static Logger log = LoggerFactory.getLogger(LinkHelper.class);


    public void clickLink(String linkText) {
        log.info(linkText);
        getElement(By.linkText(linkText)).click();
    }

    public void clickPartialLink(String partialLinkText) {
        log.info(partialLinkText);
        getElement(By.partialLinkText(partialLinkText)).click();
    }

    public String getHyperLink(By locator) {
        log.info(String.valueOf(locator));
        return getHyperLink(getElement(locator));
    }

    public String getHyperLink(WebElement element) {
        String link = element.getAttribute("hreg");
        log.info("Element : " + element + " Value : " + link);
        return link;
    }

}
