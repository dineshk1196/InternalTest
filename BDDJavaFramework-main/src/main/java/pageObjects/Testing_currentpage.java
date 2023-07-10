package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Testing_currentpage {

    public static void main (String args[]) throws InterruptedException {

        EdgeOptions opt=new EdgeOptions();

       opt.setExperimentalOption("debuggerAddress","localhost:60789");

       WebDriver driver=new EdgeDriver(opt);
       WebElement next=driver.findElement(By.xpath("//span[text()=' Number of Persons Receiving Day Care ']/parent::div/following-sibling::div[2]/div/p-inputnumber/span/input"));

        driver.findElement(By.cssSelector("span.k-select")).click();

        WebElement dropdownList = driver.findElement(By.cssSelector("ul.k-list"));
        dropdownList.sendKeys("Option 1", Keys.ENTER);

        driver.findElement(By.cssSelector("body")).click();
Thread.sleep(4000);


    }
        }



