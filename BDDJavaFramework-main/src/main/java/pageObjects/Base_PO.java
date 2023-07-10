package pageObjects;

import driver.DriverFactory;
import helper.CheckBoxOrRadioButtonHelper;
import helper.DropDownHelper;
import helper.JavaScriptHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import utils.Excel.ExcelUtil;
import utils.ExcelReadUtil;
import utils.Global_Vars;
import utils.TestRunConfig;
import java.io.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.X509Certificate;
import java.io.*;



public class Base_PO {
    String excelFileName = null;
    String quoteid=null;
    String premium=null;
    Set<String> cptext=new LinkedHashSet<String>(); ;
    private ExcelUtil operation;
    private JavaScriptHelper js;

    DropDownHelper ddhelper;
    CheckBoxOrRadioButtonHelper chelp;
    String res=null;
    String requestbody=null;
    String pass="";
    String fail="";
    String uwpass="";
    String uwfail="";
    String [] results=new String[100];
    int resultvalue;
    int l,m=0;
    String Policynumber=null;
    String faillist;



    String Excelpath= System.getProperty("user.dir")+"/src/main/resources/WorkBook/BDDSourceFileAPI.xlsx";
    public Base_PO() {
        if(TestRunConfig.LOB.equalsIgnoreCase("Compak")){
            excelFileName = ExcelReadUtil.readExcelFile("CPKMA.xlsx");

        }else if(TestRunConfig.LOB.equalsIgnoreCase("Commercial")){
            excelFileName = ExcelReadUtil.readExcelFile("CPKMA1.xlsx");
        }else if(TestRunConfig.LOB.equalsIgnoreCase("NA")){
            //TODO
        }
        PageFactory.initElements(getDriver(), this);
    }

    /**
     This getDriver() creates driver and returns driver object
     @return WebDriver object
     @throws WebDriverException expection
     */
    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public void navigateTo_URL(String url) {
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
        getDriver().get(url);

    }

    public String generateRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public void sendKeys(By by, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(textToType);
    }

    public String sendres(){
        return res;
    }

    public  String[] send_notmatchedresults()
        {
        return results;
    }

    public int sendresnotmatched(){
        return resultvalue;
    }
    public String premiummatch(){return premium;}
    public String sendreq() { return requestbody;}
    public String controlpanelvalidation(){
        WebElement table = getDriver().findElement(By.xpath("//table"));
        List<WebElement> rows=table.findElements(By.tagName("tr"));
        int rowcount=rows.size();
        for(int i=1;i<rowcount;i++)
        {
            WebElement E=getDriver().findElement(By.xpath("//tr["+i+"]/td["+3+"]"));
            String S= E.findElement(By.tagName("div")).getText();
            String V=S.replaceAll(" ","");

            if((V.equals("Requires Attention\n")==true)) {

                    String T = getDriver().findElement(By.xpath("//tr[" + i + "]/td[" + 1 + "]")).getText();
                    String R = T.replaceAll(" ", "");


                    if (R.contains("INSPECTIONORDERING")) {

                    } else if (R.contains("ESIGN")) {

                    } else {

                        cptext.add("[ " + T + " -> Status: " + S + " ;]\n");


                    }

            }

        }


        faillist=cptext.toString();

        System.out.println(faillist);
        System.out.println(faillist.length());
        return faillist;
    }

    public String[] sectionalpremiumvalidation(){
             String[] result=new String[2];
              result[0]=uwpass;
              result[1]=uwfail;
              return result;
        }

    public String[] uwrulevalidation(){
        String[] result=new String[2];
        result[0]=pass;
        result[1]=fail;
        return result;
    }


    public void sendKeys(WebElement element, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToType);
    }

    public void waitFor(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public void waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForWebElementAndClick(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void waitForWebElementAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitForAlert_And_ValidateText(String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.alertIsPresent());
        String alert_Message_Text = getDriver().switchTo().alert().getText();
        Assert.assertEquals(alert_Message_Text, text);
    }


    public String GetObjData(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element)).getText();
        String text=element.getText();
        return text;
    }

    public void actions(String action, String element, String text,String PN) throws InterruptedException {

        switch (action) {
            case "newtab":
                getDriver().switchTo().newWindow(WindowType.TAB);
                getDriver().navigate().to(text);

                break;

            case "refresh":
                getDriver().navigate().refresh();
                break;
            case "gotowebapp":
                Set<String> windot = getDriver().getWindowHandles();
                Iterator<String> ita = windot.iterator();
                ita.next();
                String webappurl=ita.next();
                getDriver().switchTo().window(webappurl);
                break;

            case "gotosocotra":

                Set<String> windows = getDriver().getWindowHandles();
                Iterator<String> it = windows.iterator();
                String socotraurl = it.next();
                getDriver().switchTo().window(socotraurl);
                break;
            case "closepolicysearchwindow":
                List<WebElement> li = getDriver().findElements(By.xpath(element));
                if(li.size()>0)
                {
                    WebElement W=getDriver().findElement(By.xpath(element));
                    W.click();
                }
                break;
            case "sectionalpremiumvalidation":

                String expected = text;
                Map<String, String> expectedvalues = new HashMap<>();
                String[] keyValuePairs = expected.split("\\|\\|");

                for (String pair : keyValuePairs) {

                    String[] keyValue = pair.split("\\|");
                    String key = keyValue[0];
                    String value = keyValue[1];

                    expectedvalues.put(key, value);
                }
                if(m==0)
                {
                    l=3;
                }
                else{
                    l=2;
                }


                WebElement table=getDriver().findElement(By.xpath(element));
                List<WebElement> rows=table.findElements(By.tagName("tr"));
                Map<String, String> actualvalue = new HashMap<>();
                int rowcount=rows.size();
                for(int i=1;i<rowcount;i++)
                {

                    String value=getDriver().findElement(By.xpath(element+"/tr["+i+"]/td["+l+"]")).getText();

                    String key=getDriver().findElement(By.xpath(element+"/tr["+i+"]/td[1]")).getText();
                    actualvalue.put(key, value);
                }
                pass="Sectional Premium pass in: ";
                fail="Sectional premium mismatch in: ";

                boolean T=expectedvalues.equals(actualvalue);
                for (String key : actualvalue.keySet()) {
                    String value1 = expectedvalues.get(key);
                    String value2 = actualvalue.get(key);

                    if (value2.equals(value1)) {
                        pass=pass+" "+key+"  :"+value2+"|\n";

                    } else {
                        fail=fail+key+": "+" Expected value: "+value1+" Actual value:"+value2+"|\n";
                    }
                }
                m=m+1;
                System.out.println("Pass:"+pass);
                System.out.println("Fail:"+fail);


                break;

            case "logoutfunction":
              List<WebElement> list=getDriver().findElements(By.xpath(element));
              System.out.println(list);
                if(list.size()!=0)
                {
                    WebElement WO=getDriver().findElement(By.xpath(element));
                            WO.click();
                   Thread.sleep(10);
                    WebElement logout1= getDriver().findElement(By.xpath("//span[text()='Logout']"));
                    logout1.click();
                    Thread.sleep(10);
                    WebElement logout2= getDriver().findElement(By.xpath("//button[text()='Logout']\n"));
                    logout2.click();

                }
                else {

                }
                break;


            case "windowhandlerfunction":
                Set<String> windowss = getDriver().getWindowHandles();
                Iterator<String> itt = windowss.iterator();
                String socotraaurl = itt.next();
                String webapppurl = itt.next();
                getDriver().switchTo().window(socotraaurl);
                String strUrl = getDriver().getCurrentUrl();
                String strnum =strUrl.replaceAll("[^\\d+]", "");
                String strpol = strnum.substring(0,9);
                getDriver().switchTo().window(webapppurl);
                System.out.println(getDriver().getCurrentUrl());
                Thread.sleep(800);

                waitFor(By.xpath(element));
                System.out.println("in method"+strpol);

                sendKeys(getDriver().findElement(By.xpath(element)),strpol);

                System.out.println(strpol);
                break;

            case "frames":

                getDriver().switchTo().frame(0);

                break;


            case "switchToDefault":
                getDriver().switchTo().defaultContent();
                break;


            case "sendkey":
                Thread.sleep(200);
                WebElement ele;
                waitFor(By.xpath(element));
                getDriver().findElement(By.xpath(element)).clear();
                Thread.sleep(200);
                sendKeys(getDriver().findElement(By.xpath(element)),text);
                break;

            case "FileUpload":
                waitFor(By.xpath(element));
                WebElement fu=getDriver().findElement(By.xpath(element));
                fu.sendKeys(text);
                break;

            case "Navigate_to_url":
                navigateTo_URL(text);
                break;
            case "cptable":

                WebElement nextbtn=getDriver().findElement(By.xpath(element));

                    controlpanelvalidation();
                    while(nextbtn.isEnabled()){
                        nextbtn.click();
                        controlpanelvalidation();
                    }
                break;

            case "waitandclick_js":
                Thread.sleep(200);
                getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
                waitFor(By.xpath(element));
                 js=new JavaScriptHelper();
                 js.scrollToElemet(getDriver().findElement(By.xpath(element)));
                 Thread.sleep(200);
                 javascriptexe(getDriver().findElement(By.xpath(element)));
                break;

            case "Action_click":
                Thread.sleep(200);
                Actions  builder=new Actions(getDriver());
                Action mouse= builder.moveToElement(getDriver().findElement(By.xpath(element))).click().build();
                mouse.perform();
                break;


            case "click":
                Thread.sleep(200);
                waitForWebElementAndClick(getDriver().findElement(By.xpath(element)));
                break;

            case "change_tab":
                ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
                int latest=tabs.size();
                getDriver().switchTo().window(tabs.get(latest-1));
                break;

            case "close_previous_window":
                String base = getDriver().getWindowHandle();
                Set<String> set = getDriver().getWindowHandles();
                set.remove(base);
                assert set.size()==1;
                getDriver().switchTo().window(Arrays.toString(set.toArray(new String[0])));
                getDriver().close();
                getDriver().switchTo().window(base);
               break;

            case "Wait_until_load_bar":
               WebDriverWait w=new WebDriverWait(getDriver(), Duration.ofSeconds(250));
               w.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
                break;

            case "wait":
                int sec = Integer.parseInt(text);
                sec = sec * 500;
                Thread.sleep(sec);
                break;

            case "is_element_present_thenClick":
                Thread.sleep(4000);
              if (getDriver().findElement(By.xpath(element)).isDisplayed()) {
                  javascriptexe(getDriver().findElement(By.xpath(element)));
               }
                break;

            case "is_element_present_thensendkey":
                Thread.sleep(4000);
                if (getDriver().findElement(By.xpath(element)).isDisplayed()) {
                    sendKeys(getDriver().findElement(By.xpath(element)),text);
                }
                break;


            case "sendkey_dropdown":
                ele=getDriver().findElement(By.xpath(element));
                ele.clear();
                sendKeys(ele,text);
                Thread.sleep(2000);
                ele.sendKeys(Keys.BACK_SPACE);
                Thread.sleep(3000);
                ele.sendKeys(Keys.ARROW_DOWN);
                Thread.sleep(2000);
                ele.sendKeys(Keys.ENTER);
                break;

            case "sendkey_dropdownM":
                ele=getDriver().findElement(By.xpath(element));
                sendKeys(ele,text);
                Thread.sleep(2000);
                ele.sendKeys(Keys.BACK_SPACE);
                Thread.sleep(2000);
                ele.sendKeys(Keys.ARROW_DOWN);
                Thread.sleep(2000);
                break;

            case "KendoDropdown":
                Thread.sleep(600);
                ddhelper=new DropDownHelper();
                ddhelper.KendoDropdown(element,text);
                break;

            case "select_checkbox":
                Thread.sleep(600);
                chelp=new CheckBoxOrRadioButtonHelper();
                chelp.selectCheckBox(getDriver().findElement(By.xpath(element)));
                break;

            case "calender_year_select":
                int year = Integer.parseInt(text);
                int oyear=2023-year;
                for(int i=0;i<oyear;i++)
                {
                    waitForWebElementAndClick(By.xpath(element));
                    Thread.sleep(1000);
                }
                break;

            case "custom_waitandclick_js":
                Thread.sleep(2000);
                String value=text;
                getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
                waitFor(By.xpath(element+value+"']"));
                js=new JavaScriptHelper();
                js.scrollToElemet(getDriver().findElement(By.xpath(element+value+"']")));
                Thread.sleep(2000);
                javascriptexe(getDriver().findElement(By.xpath(element+value+"']")));
                break;

            case "Iframe_Payment":
                waitFor(By.xpath(element));
                getDriver().switchTo().frame("PortalOneFrame");
                break;

            case "Switch_driver":
                getDriver().switchTo().defaultContent();
                break;

            case "gettext":
                waitFor(By.xpath(element));
                 premium = getDriver().findElement(By.xpath(element)).getText().trim();


                System.out.println(premium);
               break;

            case "Alert":
                WebDriverWait waitload = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
                waitload.until(ExpectedConditions.alertIsPresent());
                Alert alert = getDriver().switchTo().alert();
                alert.accept();
                break;


            case "Js-sendkeys":
                waitFor(By.xpath(element));
                WebElement el = getDriver().findElement(By.xpath(element));
                el.click();
                JavascriptExecutor ex = (JavascriptExecutor)getDriver();
                ex.executeScript("arguments[0].value='"+ text +"';", el);
                el.click();
                el.sendKeys(Keys.TAB);
                break;

            case "Quote-search":
                Thread.sleep(3000);
                waitFor(By.xpath(element));
                getDriver().findElement(By.xpath(element)).clear();
                Thread.sleep(5000);
                sendKeys(getDriver().findElement(By.xpath(element)),quoteid);
                break;

            case "policynumber-enter":

                Thread.sleep(2000);
                waitFor(By.xpath(element));
                sendKeys(getDriver().findElement(By.xpath(element)),PN);
                break;

            case "New-window":

                getDriver().switchTo().newWindow(WindowType.WINDOW);
                String current=getDriver().getWindowHandle();

                for (String windowHandle : getDriver().getWindowHandles()) {
                    if (!current.contentEquals(windowHandle)) {
                        getDriver().switchTo().window(windowHandle).close();
                    }
                }
                getDriver().switchTo().window(current);
                break;

            case "Action_sendkey_withClear":
                WebElement ele1=getDriver().findElement(By.xpath(element));
                ele1.click();
                Actions action1=new Actions(getDriver());
                for(int i=0;i<6;i++)
                    action1.keyDown(Keys.BACK_SPACE).build().perform();
                action1.sendKeys(text);
                break;

            case "action_sendkey":
                Actions action2=new Actions(getDriver());
                WebElement eleact=getDriver().findElement(By.xpath(element));
                action2.sendKeys(eleact,text);
                break;
            case "enterpolno":
                Thread.sleep(200);

                waitFor(By.xpath(element));
                getDriver().findElement(By.xpath(element)).clear();
                Thread.sleep(200);
                sendKeys(getDriver().findElement(By.xpath(element)),PN);
                break;

            case "logout":
                System.out.println("im in after test");


                getDriver().manage().deleteAllCookies();
                break;

            case "uwvalidation":

                String uwexpected = text;


                List<String> checkvalue=new ArrayList<String>();
                checkvalue = List.of(uwexpected.split("\\|"));




                WebElement tables=getDriver().findElement(By.xpath(element));
                List<WebElement> row=tables.findElements(By.tagName("tr"));
                int count=row.size();
                List<String> uwvalue=new ArrayList<String>();
                int r=1;
                for(int q=0;q<count;q++)
                {
                    String x="";
                    x=getDriver().findElement(By.xpath(element + "/tr[" + r + "]/td[1]")).getText();
                    uwvalue.add(x);
                    r+=1;




                }
                uwpass="UW Triggered as expected: ";
                uwfail="UW Not Triggered as expected: ";

                for(String uwexp:checkvalue)  {
                    int icount =0;
                    for (String uwactual:uwvalue)
                       {
                           if(uwexp.equals(uwactual))
                           {
                               icount+=1;
                           }
                           else{


                           }
                       }
                    if(icount==1){
                        uwpass=uwpass+" [ "+uwexp+" ],";
                    }
                    else{
                        uwfail=uwfail+" [ "+uwexp+" ],";
                    }

                }



                System.out.println("Pass:"+uwpass);
                System.out.println("Fail:"+uwfail);


                break;

            case "hostnametextclear":
               WebElement a= getDriver().findElement(By.xpath(element));
                String s = Keys.chord(Keys.CONTROL, "a");
                a.sendKeys(s);
                // sending DELETE key
                a.sendKeys(Keys.DELETE);

                break;


        }
    }


        public void javascriptexe(WebElement ele){
            JavascriptExecutor exe = (JavascriptExecutor) getDriver();
            exe.executeScript("arguments[0].click();", ele);
        }
}
