//package stepDefinitions;
//
//import helper.JavaScriptHelper;
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import model.User;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebElement;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import pageObjects.*;
//import utils.Excel.ExcelUtil;
//import utils.TestRunConfig;
//import utils.dbOperations.QueryTestDataExcel;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
//public class Bamboo_Login_Steps extends Base_PO {
//    private static final Logger log = LoggerFactory.getLogger(Bamboo_Login_Steps.class);
//
//    //private Bamboo_Login Blogin_po;
//
//    //private Bamboo_BasicDetails Basicpg;
//
//    private Bamboo_Home home;
//
//    private Bamboo_QuoteRate QR;
//
//    private ExcelUtil operation;
//
//    private Base_PO base;
//
//    private JavaScriptHelper js;
//
//    String Excelpath= "C:\\Users\\sadiqs\\Downloads\\BDDJavaFramework-main - Copy\\BDDJavaFramework-main\\src\\main\\resources\\WorkBook\\BDDSourceFile.xlsx";
//
//    QueryTestDataExcel queryTestDataExcel;
//
//
//    public Bamboo_Login_Steps(Base_PO Base, QueryTestDataExcel queryTestDataExcel) {
//
//        this.base = Base;
//        this.queryTestDataExcel = queryTestDataExcel;
//    }
//
//    @Given("I access the Bamboo login page")
//    public void i_access_the_webdriver_university_login_page() throws Exception {
//       String[] values= base.get_testdate(1);
//       String url= values[0];
//       base.navigateTo_URL(url);
//       Thread.sleep(10000);
//    }
//
//    @When("I enter a username")
//    public void i_enter_a_username() throws Exception {
//        String[] values= base.get_testdate(2); //Retrive the testdata and action from excel sheet
//        String name= values[0]; //storing retrived values
//        String ele=base.make_webelement(2); //Webelement from excelsheet
//
//        base.actions(values[1],ele,name); //Performing actions, which are mentioned on excel
//    }
//
//    @And("I click on next button")
//    public void i_enter_a_next() throws Exception {
//        String[] values= base.get_testdate(3);
//        String ele=base.make_webelement(3);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//        //Blogin_po.clickOn_Login_Button();
//    }
//
//    @And("I enter a password")
//    public void i_enter_a_password() throws Exception {
//        String[] values= base.get_testdate(4);
//        String ele=base.make_webelement(4);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//    }
//
//    @And("I click on the login button")
//    public void i_click_on_the_login_button() throws Exception {
//        String[] values= base.get_testdate(5);
//        String ele=base.make_webelement(5);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//    }
//
//    @And("I click on New Quote button")
//    public void i_click_on_new_quote() throws Exception {
//        Thread.sleep(15000);
//        String[] values= base.get_testdate(6);
//        String ele=base.make_webelement(6);
//        //waitFor(ele);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//        //Blogin_po.clickOn_start_new_quote();
//         }
//
//
//    @And("I changed the tab")
//    public void switch_tab() throws InterruptedException {
//        Thread.sleep(8000);
//        ArrayList<String> tabs=new ArrayList<String>(getDriver().getWindowHandles());
//        getDriver().switchTo().window(tabs.get(1));
//        Thread.sleep(8000);
//    }
//
//    @And("I Enter the Frist name")
//    public void i_enter_fristname() throws Exception {
//
//        String[] values= base.get_testdate(7);
//        String ele=base.make_webelement(7);
//        //waitFor(ele);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//    }
//
//    @And("I Enter the Last name")
//    public void i_enter_lastname() throws Exception {
//        String[] values= base.get_testdate(8);
//        String ele=base.make_webelement(8);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//    }
//
//    @And("I enter the Email Address")
//    public void i_enter_address() throws Exception {
//        String[] values= base.get_testdate(9);
//        String ele=base.make_webelement(9);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//
//
//    }
//
//    @And("I entered the address")
//    public void i_enter_maddress() throws Exception {
//        String[] values= base.get_testdate(10);
//        String ele=base.make_webelement(10);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//        Thread.sleep(5000);
//        //ele.sendKeys(Keys.TAB);
//        Thread.sleep(5000);
//        //popup
//        String[] values1= base.get_testdate(11);
//        String ele1=base.make_webelement(11);
//        String name1=values[0];
////        if(ele1.isDisplayed()) {
////            base.actions(values1[1], ele1, name1);
////        }
//    }
//
//    @And("I clicked on Radio button 1")
//    public void i_enter_radiobtn1() throws Exception {
//        Thread.sleep(5000);
//        js=new JavaScriptHelper();
//        String[] values= base.get_testdate(12);
//        String ele=base.make_webelement(12);
//        String name=values[0];
//        //js.scrollToElemet(ele);
//        base.actions(values[1],ele,name);
//    }
//
//    @And("I clicked on Radio button 2")
//    public void i_enter_radiobtn2() throws Exception {
//        String[] values= base.get_testdate(13);
//        String ele=base.make_webelement(13);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//    }
//
//    @And("I clicked on Radio button 3")
//    public void i_enter_radiobtn3() throws Exception {
//        String[] values= base.get_testdate(14);
//        String ele=base.make_webelement(14);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//    }
//
//    @And("I clicked on Radio button 4")
//    public void i_enter_radiobtn4() throws Exception {
//        String[] values= base.get_testdate(15);
//        String ele=base.make_webelement(15);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//    }
//
//    @Then("I clicked on Proceed button")
//    public void i_enter_proceedbtn() throws Exception {
//        String[] values= base.get_testdate(16);
//        String ele=base.make_webelement(16);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//    }
//
//    @And("I clicked on create quote button")
//    public void i_enter_createquotebtn() throws Exception {
//        Thread.sleep(15000);
//        String[] values= base.get_testdate(17);
//        String ele=base.make_webelement(17);
//        //waitFor(ele);
//        js=new JavaScriptHelper();
//        //js.scrollToElemet(ele);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//        Thread.sleep(5000);
//    }
//
//    @And("I clicked on proceed button1")
//    public void i_enter_proceedbtn1() throws Exception {
//        String[] values= base.get_testdate(18);
//        String ele=base.make_webelement(18);
//        js=new JavaScriptHelper();
//       // js.scrollToElemet(ele);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//        Thread.sleep(5000);
//    }
//
//    @And("I clicked on Start quote")
//    public void i_enter_startquote() throws Exception {
//        String[] values= base.get_testdate(19);
//        String ele=base.make_webelement(19);
//        //waitFor(ele);
//        js=new JavaScriptHelper();
//        //js.scrollToElemet(ele);
//        String name=values[0];
//        base.actions(values[1],ele,name);
//        Thread.sleep(15000);
//
//    }
//
//    @Then("I checked Final premium")
//    public void i_checked_FinalPremium() throws Exception {
//        String[] values= base.get_testdate(20);
//        String ele=base.make_webelement(20);
//        //waitFor(ele);
//        //String Finalpremium= GetObjData(ele);
//        //System.out.println("Final Premium is"+Finalpremium);
//    }
//
//}