//package pageObjects;
//
//import model.Policy;
//import model.User;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import utils.Global_Vars;
//import utils.TestRunConfig;
//
//public class Bamboo_Login extends Base_PO {
//    private @FindBy(xpath = "//input[@id='UserName']")
//    WebElement username_TextField;
//
//    private @FindBy(xpath ="//input[@id='Password']")
//    WebElement password_TextField;
//
//    private @FindBy(xpath="//button[@class='btn btn-primary mt-5 w-100']")
//    WebElement login_Button;
//
//    private @FindBy(xpath ="//button[@class='btn btn-primary mt-5 w-100']")
//    WebElement login_Button_org;
//
//    private @FindBy(xpath = "//a[@href='https://bmb-bde-qa-quotingapp.azurewebsites.net']")
//    WebElement start_a_quote;
//
//
//
//    public Bamboo_Login() {
//
//        super();
//    }
//
//    public void navigateTo_WebDriverUniversity_Login_Page() throws InterruptedException {
//        navigateTo_URL(Global_Vars.WEBDRIVER_UNIVERSITY_HOMEPAGE_URL);
//        Thread.sleep(20000);
//    }
//
//    public void setUsername(String username) throws InterruptedException {
//        waitFor(username_TextField);
//        //Policy policy = new Policy();
//        sendKeys(username_TextField, username);
//       // policy.setPolicyNumber(username);
//        //return policy;
//    }
//
//
//
//    public void clickOn_Login_Button() {
//
//        waitForWebElementAndClick(login_Button);
//    }
//
//    public void setPassword(String password) {
//        sendKeys(password_TextField, password);
//    }
//
//    public void clickOn_Login_Button_org() throws InterruptedException {
//        waitForWebElementAndClick(login_Button_org);
//        Thread.sleep(2000);
//    }
//
//    public void clickOn_start_new_quote() throws InterruptedException {
//        waitFor(start_a_quote);
//        Thread.sleep(2000);
//        JavascriptExecutor js = (JavascriptExecutor)getDriver();
//        js.executeScript("arguments[0].click();",start_a_quote);
//        Thread.sleep(5000);
//    }
//    public void validate_SuccessfulLogin_Message() {
//        waitForAlert_And_ValidateText("validation succeeded");
//    }
//
//    public void validate_UnsuccessfulLogin_Message() {
//        waitForAlert_And_ValidateText("validation failed");
//    }
//
//    public void logsIntoApplication (User user) throws InterruptedException {
//        navigateTo_URL(TestRunConfig.BASE_URL);
//        setUsername(user.getUserId());
//        setPassword(user.getPassword());
//        clickOn_Login_Button();
//        }
//
//}
