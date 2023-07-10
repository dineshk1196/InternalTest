package pageObjects;

import model.Policy;
import model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Global_Vars;
import utils.TestRunConfig;

public class Post_Login extends Base_PO {
    private @FindBy(xpath = "//input[@type='text']")
    WebElement username_TextField;

    private @FindBy(xpath ="//input[@type='password']")
    WebElement password_TextField;

    private @FindBy(xpath="//button[@type='submit']")
    WebElement login_Button;

    public Post_Login() {

        super();
    }

    public void navigateTo_WebDriverUniversity_Login_Page() {
        navigateTo_URL(Global_Vars.WEBDRIVER_UNIVERSITY_HOMEPAGE_URL);
    }

    public Policy setUsername(String username) {
        Policy policy = new Policy();
        sendKeys(username_TextField, username);
        policy.setPolicyNumber(username);
        return policy;
    }

    public void setPassword(String password) {
        sendKeys(password_TextField, password);
    }

    public void clickOn_Login_Button() {
        waitForWebElementAndClick(login_Button);
    }

    public void validate_SuccessfulLogin_Message() {
        waitForAlert_And_ValidateText("validation succeeded");
    }

    public void validate_UnsuccessfulLogin_Message() {
        waitForAlert_And_ValidateText("validation failed");
    }

    public void logsIntoApplication (User user) {
        navigateTo_URL(TestRunConfig.BASE_URL);
        setUsername(user.getUserId());
        setPassword(user.getPassword());
        clickOn_Login_Button();
        }

}
