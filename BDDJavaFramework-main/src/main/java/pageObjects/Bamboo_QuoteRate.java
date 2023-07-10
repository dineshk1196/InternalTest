package pageObjects;

import helper.AlertHelper;
import helper.JavaScriptHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bamboo_QuoteRate extends Base_PO {


    private @FindBy(xpath="//button[@id='btn93a68f78fd0d5a4132c0']")
    WebElement Quoterate;

    private @FindBy(xpath="//*[@id='label2']/b")
    WebElement premium;

    private static Logger log = LoggerFactory.getLogger(AlertHelper.class);
    public Bamboo_QuoteRate() {

        super();
    }

    public void click_on_quoterate() throws InterruptedException {
        waitFor(Quoterate);
        JavaScriptHelper helper=new JavaScriptHelper();
        helper.scrollToElemet(Quoterate);
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        js.executeScript("arguments[0].click();",Quoterate);
        Thread.sleep(9000);
    }

    public void getpremium() {
        waitFor(premium);
        String Finalpremium= GetObjData(premium);
        //log.info(Finalpremium);
        System.out.println("Final premium is :"+Finalpremium);


    }



}
