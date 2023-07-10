package helper;

import driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertHelper {
    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    private static Logger log = LoggerFactory.getLogger(AlertHelper.class);

    public Alert getAlert() {
        log.debug("");
        return getDriver().switchTo().alert();
    }

    public void AcceptAlert() {
        try {
            log.info("");
            getAlert().accept();
        }catch(NoAlertPresentException e){
            e.printStackTrace();
        }
    }

    public void DismissAlert() {
        try {
            log.info("");
            getAlert().dismiss();
        }catch(NoAlertPresentException e){
            e.printStackTrace();
        }
    }

    public String getAlertText() {
        String text = null;
        try {
            text = getAlert().getText();
            log.info(text);
        }catch(NoAlertPresentException e){
            e.printStackTrace();
        }
        return text;
    }

    public boolean isAlertPresent() {
        try {
            getDriver().switchTo().alert();
            log.info("Alert present");
            return true;
        } catch (NoAlertPresentException e) {
            log.info("Alert not present");
            return false;
        }
    }

    public void AcceptAlertIfPresent() {
        try {
            if (!isAlertPresent())
                return;
            AcceptAlert();
            log.info("");
        }catch(NoAlertPresentException e){
            e.printStackTrace();
        }
    }

    public void DismissAlertIfPresent() {
        try{
            if (!isAlertPresent())
                return;
            DismissAlert();
            log.info("");
        }catch(NoAlertPresentException e){
            e.printStackTrace();
        }
    }

    public void AcceptPrompt(String text) {
        try{
            if (!isAlertPresent())
                return;

            Alert alert = getAlert();
            alert.sendKeys(text);
            alert.accept();
            log.info(text);
        }catch(NoAlertPresentException e){
            e.printStackTrace();
        }
    }
}
