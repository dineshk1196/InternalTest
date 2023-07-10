package pageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import helper.DropDownHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;


import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.DateUtilities;

import java.util.Map;

public class Test_currentpage extends Base_PO{

static DateUtilities util;

static DropDownHelper helper;
static Base_PO base;

    public  static void main(String args[]) throws InterruptedException {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable notifications");
        // options.addArguments("--incognito");
        DesiredCapabilities cp = new DesiredCapabilities();
        cp.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(cp);
        EdgeDriver driver = new EdgeDriver(options);

// getCapabilities will return all browser capabilities

        Capabilities cap=driver.getCapabilities();

// asMap method will return all capability in MAP
        Map<String, Object> myCap=cap.asMap();

// print the map data-
        System.out.println(myCap);


            }
        }





















