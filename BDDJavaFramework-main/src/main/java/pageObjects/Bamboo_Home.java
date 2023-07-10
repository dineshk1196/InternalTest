package pageObjects;

import helper.JavaScriptHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class Bamboo_Home extends Base_PO {

    private @FindBy(xpath="(//*[contains(text(), 'Create Quote')])[1]")
    WebElement CreateQuote;

    private @FindBy(xpath="//button[@id='btnf30dea3790f0bf68215e']")
    WebElement Proceed;


    public Bamboo_Home() {

        super();
    }

    public void click_on_createquote() throws InterruptedException {
        Thread.sleep(5000);
        waitFor(CreateQuote);
        JavaScriptHelper helper=new JavaScriptHelper();
        helper.scrollToElemet(CreateQuote);
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        js.executeScript("arguments[0].click();",CreateQuote);
        Thread.sleep(5000);
    }

    public void click_on_proceed() throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        js.executeScript("arguments[0].click();", Proceed);
        Thread.sleep(2000);
    }

}
