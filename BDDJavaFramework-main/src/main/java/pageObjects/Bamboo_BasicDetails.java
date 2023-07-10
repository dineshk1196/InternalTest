//package pageObjects;
//
//import helper.CheckBoxOrRadioButtonHelper;
//import helper.JavaScriptHelper;
//import model.Policy;
//import model.User;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.FindBy;
//import utils.Global_Vars;
//import utils.TestRunConfig;
//
//import java.util.ArrayList;
//
//public class Bamboo_BasicDetails extends Base_PO {
//
//    private @FindBy(xpath="//input[@id='txtdad1053c00c2e7593632']")
//    WebElement Frist_name;
//
//    private @FindBy(xpath="//input[@id='txt2d00b04306e841a08510']")
//    WebElement Last_name;
//
//    private @FindBy(xpath="//input[@id='txt7357c81e56b7e2a2ef26']")
//    WebElement Email;
//
//    private  @FindBy(xpath="//input[@name='txtauto4701a43f33413ad9ad1c']")
//    WebElement address;
//
//    private @FindBy(xpath = "//label[@for='rb9617fe0e72989985833bNo']")
//    WebElement radio1;
//
//    private @FindBy(xpath="//label[@for='rbc1871ade8a0fd2e409ceNo']")
//    WebElement radio2;
//
//    private @FindBy(xpath="//label[@for='rbdab0c0adca64102d7a24No']")
//    WebElement radio3;
//
//    private @FindBy(xpath="//label[@for='rbd79cc95ff6fddd79a77aNo']")
//    WebElement radio4;
//
//    private @FindBy(xpath = "//button[@id='btna24fc34ead8960762fc2']")
//    WebElement Proceed;
//
//    private @FindBy(xpath="//button[@class='ng-tns-c39-1 p-confirm-dialog-accept p-button p-component ng-star-inserted']")
//    WebElement Dialog_yes;
//
//
//
//
//
//    public Bamboo_BasicDetails() {
//
//        super();
//    }
//
//    public void changetab() throws InterruptedException {
//        Thread.sleep(5000);
//        ArrayList<String> tabs=new ArrayList<String>(getDriver().getWindowHandles());
//        getDriver().switchTo().window(tabs.get(1));
//        Thread.sleep(5000);
//
//
//    }
//
//    public void enter_fristname(String name) throws InterruptedException {
//        waitFor(Frist_name);
//        sendKeys(Frist_name,name);
//    }
//
//    public void enter_lastname(String lname){
//        sendKeys(Last_name,lname);
//    }
//
//    public void enter_email(String mail){
//        sendKeys(Email, mail);
//    }
//
//    public void enter_address(String iaddress) throws InterruptedException {
//        sendKeys(address, iaddress);
//        Thread.sleep(5000);
//        address.sendKeys(Keys.TAB);
//        Thread.sleep(5000);
//        if(Dialog_yes.isDisplayed()){
//            Dialog_yes.click();
//        }
//
//
//        //Thread.sleep(10000);
//    }
//
//    public void click_on_radio1() throws InterruptedException {
//
//        JavaScriptHelper helper=new JavaScriptHelper();
//        helper.scrollToElemet(radio1);
//        JavascriptExecutor js = (JavascriptExecutor)getDriver();
//        js.executeScript("arguments[0].click();", radio1);
//        Thread.sleep(9000);
//    }
//
//    public void click_on_radio2() throws InterruptedException {
//        JavascriptExecutor js = (JavascriptExecutor)getDriver();
//        js.executeScript("arguments[0].click();", radio2);
//
//        Thread.sleep(2000);
//    }
//
//    public void click_on_radio3() throws InterruptedException {
//        JavascriptExecutor js = (JavascriptExecutor)getDriver();
//        js.executeScript("arguments[0].click();", radio3);
//
//        Thread.sleep(2000);
//    }
//
//    public void click_on_radio4() throws InterruptedException {
//        JavascriptExecutor js = (JavascriptExecutor)getDriver();
//        js.executeScript("arguments[0].click();", radio4);
//
//        Thread.sleep(2000);
//    }
//
//    public void click_on_procced() throws InterruptedException{
//        JavascriptExecutor js = (JavascriptExecutor)getDriver();
//        js.executeScript("arguments[0].click();", Proceed);
//        Thread.sleep(10000);
//    }
//
//
//
//
//
//
//
//
//}
