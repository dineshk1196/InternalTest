package helper;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Set;

public class BrowserActionHelper {
    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    private static Logger log = LoggerFactory.getLogger(BrowserActionHelper.class);

    public void goBack() {
        getDriver().navigate().back();
        log.info("");
    }

    public void goForward() {
        getDriver().navigate().forward();
        log.info("");
    }

    public void refresh() {
        getDriver().navigate().refresh();
        log.info("");
    }

    public Set<String> getWindowHandlens() {
        log.info("");
        return getDriver().getWindowHandles();
    }

    public void SwitchToWindow(int index) {

        LinkedList<String> windowsId = new LinkedList<String>(
                getWindowHandlens());

        if (index < 0 || index > windowsId.size())
            throw new IllegalArgumentException("Invalid Index : " + index);

        getDriver().switchTo().window(windowsId.get(index));
    }

    public void switchToParentWindow() {
        LinkedList<String> windowsId = new LinkedList<String>(
                getWindowHandlens());
        getDriver().switchTo().window(windowsId.get(0));
        log.info("");
    }

    public void switchToParentWithChildClose() {
        switchToParentWindow();

        LinkedList<String> windowsId = new LinkedList<String>(
                getWindowHandlens());

        for (int i = 1; i < windowsId.size(); i++) {
            log.info(windowsId.get(i));
            getDriver().switchTo().window(windowsId.get(i));
            getDriver().close();
        }

        switchToParentWindow();
    }

//    public void switchToFrame(By locator) {
//        driver.switchTo().frame(getElement(locator));
//        log.info(locator);
//    }

    public void switchToFrame(String nameOrId) {
        getDriver().switchTo().frame(nameOrId);
        log.info(nameOrId);
    }
}
