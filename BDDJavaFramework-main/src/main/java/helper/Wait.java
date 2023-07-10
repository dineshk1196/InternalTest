//package helper;

//import driver.DriverFactory;
//import org.openqa.selenium.WebDriver;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.function.Function;
//
//import static utils.Global_Vars.DEFAULT_EXPLICIT_TIMEOUT;
//
//public class Wait {
//
//    private static Logger log = LoggerFactory.getLogger(Wait.class);
//    public WebDriver getDriver() {
//        return DriverFactory.getDriver();
//
//    }
//
//    public void w

//    public static void untilJqueryIsDone(){
//        untilJqueryIsDone(, (long) DEFAULT_EXPLICIT_TIMEOUT);
//    }
//
//    public static void untilJqueryIsDone(Long timeoutInSeconds){
//        until(, (d) ->
//        {
//            Boolean isJqueryCallDone = (Boolean)((JavascriptExecutor)).executeScript("return jQuery.active==0");
//            if (!isJqueryCallDone) System.out.println("JQuery call is in Progress");
//            return isJqueryCallDone;
//        }, timeoutInSeconds);
//    }
//
//    public static void untilPageLoadComplete(WebDriver driver) {
//        untilPageLoadComplete(driver, (long) DEFAULT_EXPLICIT_TIMEOUT);
//    }
//
//    public static void untilPageLoadComplete(WebDriver driver, Long timeoutInSeconds){
//        until(driver, (d) ->
//        {
//            Boolean isPageLoaded = (Boolean)((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
//            if (!isPageLoaded) System.out.println("Document is loading");
//            return isPageLoaded;
//        }, timeoutInSeconds);
//    }
//
//    public static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition){
//        until(driver, waitCondition, (long) DEFAULT_EXPLICIT_TIMEOUT);
//    }
//
//
//    private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds){
//        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), timeoutInSeconds);
//        webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
//        try{
//            webDriverWait.until(waitCondition);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//
//}
