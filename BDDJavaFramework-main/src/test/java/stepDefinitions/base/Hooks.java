package stepDefinitions.base;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestContext;

import java.util.Arrays;

import static driver.DriverFactory.cleanupDriver;
import static driver.DriverFactory.getDriver;

public class Hooks {
    private Logger log = LoggerFactory.getLogger(Hooks.class);
    private TestContext testContext;
    public Hooks(TestContext testContext){
        this.testContext = testContext;
    }
    @Before
    public void setup() {
        getDriver();
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {

        //validate if scenario has failed
        //if (scenario.isFailed()) {
            try {
                final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "image");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    //}


    @After
    public void tearDown(Scenario scenario) {
        try{
            log.info("Completed:" + scenario.getName() + "-" + scenario.getStatus());

            }catch (Exception e){
                log.error("Exception at @After class"+e.getMessage()+ Arrays.toString(e.getStackTrace()));
        }finally {
            System.gc();
            cleanupDriver();
            scenarioPrint(scenario);
        }
    }

    public void scenarioPrint(Scenario scenario) {

        testContext.setTestLogs("\n----------------------------------------------------------------------");
//        testContext.setTestLogs("\nPolicy Number :"+"1234567890");
        testContext.setTestLogs("\n----------------------------------------------------------------------");
        log.info(testContext.getTestLogs());
        scenario.attach(testContext.getTestLogs().getBytes(),"text/plain", "Policy");
    }
}
