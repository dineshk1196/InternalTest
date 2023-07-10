package helper;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaScriptHelper{

    private static Logger log = LoggerFactory.getLogger(JavaScriptHelper.class);

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public Object executeScript(String script) {
        JavascriptExecutor exe = (JavascriptExecutor) getDriver();
        log.info(script);
        return exe.executeScript(script);
    }

    public Object executeScript(String script, Object... args) {
        JavascriptExecutor exe = (JavascriptExecutor) getDriver();
        log.info(script);
        return exe.executeScript(script, args);
    }

    public void scrollToElemet(WebElement element) {
//        executeScript("window.scrollTo(arguments[0],arguments[1])",
//                element.getLocation().x, element.getLocation().y);
        JavascriptExecutor j = (JavascriptExecutor)getDriver();
        j.executeScript ("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
        log.info(String.valueOf(element));
    }

    public void scrollToElemet(By locator) {
        scrollToElemet(getDriver().findElement(locator));
        log.info(String.valueOf(locator));
    }

    public void scrollToElemetAndClick(By locator) {
        WebElement element = getDriver().findElement(locator);
        scrollToElemet(element);
        element.click();
        log.info(String.valueOf(locator));
    }

    public void scrollToElemetAndClick(WebElement element) {
        scrollToElemet(element);
        element.click();
        log.info(String.valueOf(element));
    }

    public void scrollIntoView(WebElement element) {
        executeScript("arguments[0].scrollIntoView()", element);
        log.info(String.valueOf(element));
    }

    public void scrollIntoView(By locator) {
        scrollIntoView(getDriver().findElement(locator));
        log.info(String.valueOf(locator));
    }

    public void scrollIntoViewAndClick(By locator) {
        WebElement element = getDriver().findElement(locator);
        scrollIntoView(element);
        element.click();
        log.info(String.valueOf(locator));
    }

    public void scrollIntoViewAndClick(WebElement element) {
        scrollIntoView(element);
        element.click();
        log.info(String.valueOf(element));
    }
}