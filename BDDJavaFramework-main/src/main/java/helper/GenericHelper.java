package helper;

import driver.DriverFactory;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class GenericHelper {

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    private static Logger log = LoggerFactory.getLogger(GenericHelper.class);


    public WebElement getElement(By locator) {
        log.info(String.valueOf(locator));
        if (IsElementPresentQuick(locator))
            return getDriver().findElement(locator);

        try {
            throw new NoSuchElementException("Element Not Found : " + locator);
        } catch (RuntimeException re) {
            log.error(String.valueOf(re));
            throw re;
        }
    }

    /**
     * Check for element is present based on locator
     * If the element is present return the web element otherwise null
     *
     * @param locator
     * @return WebElement or null
     */

//    public WebElement getElementWithNull(By locator) {
//        log.info(String.valueOf(locator));
//        try {
//            return driver.findElement(locator);
//        } catch (NoSuchElementException e) {
//            // Ignore
//        }
//        return null;
//    }
//
    public boolean IsElementPresentQuick(By locator) {
        boolean flag = getDriver().findElements(locator).size() >= 1;
        log.info(String.valueOf(flag));
        return flag;
    }
//
//    public String takeScreenShot(String name) throws IOException {
//
//        if (driver instanceof HtmlUnitDriver) {
//            log.fatal("HtmlUnitDriver Cannot take the ScreenShot");
//            return "";
//        }
//
//        File destDir = new File(ResourceHelper.getResourcePath("screenshots/")
//                + DateTimeHelper.getCurrentDate());
//        if (!destDir.exists())
//            destDir.mkdir();
//
//        File destPath = new File(destDir.getAbsolutePath()
//                + System.getProperty("file.separator") + name + ".jpg");
//        try {
//            FileUtils
//                    .copyFile(((TakesScreenshot) driver)
//                            .getScreenshotAs(OutputType.FILE), destPath);
//        } catch (IOException e) {
//            log.error(String.valueOf(e));
//            throw e;
//        }
//        log.info(destPath.getAbsolutePath());
//        return destPath.getAbsolutePath();// }

//    public String takeScreenShot() {
//        log.info("");
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//    }

}
