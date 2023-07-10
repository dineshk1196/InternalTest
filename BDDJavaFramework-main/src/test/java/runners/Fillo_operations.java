package runners;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import utils.DateUtilities;

import java.io.File;
import java.io.IOException;

public class Fillo_operations extends MainRunner{
    static DateUtilities date;
    public String getxpath(String obj) throws FilloException {
        connection = fillo.getConnection(ExcelPath);
        String strQuery = "Select * from Object_Repository where Object_name='" + obj + "'";
        //String strQuery1 = "Select * from Object_Repository";
        Recordset recordset2 = connection.executeQuery(strQuery);
        String xpath = null;
        while (recordset2.next()) {
            xpath = recordset2.getField("Locator");
        }
        return xpath;
    }

    public static String capture(WebDriver driver,String tc) throws IOException {
        date=new DateUtilities();
        String date_time=date.getdate_time();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("src/../Images/"+tc+"/"+date_time+"-screenshot.png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return errflpath;
    }
}
