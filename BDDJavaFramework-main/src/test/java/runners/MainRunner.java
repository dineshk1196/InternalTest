package runners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import io.appium.java_client.android.options.context.SupportsEnsureWebviewsHavePagesOption;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WindowType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import pageObjects.Base_PO;
import utils.DateUtilities;
import utils.RandomGenerator;
import utils.TestRunConfig;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.aventstack.extentreports.Status.*;
import static runners.Fillo_operations.capture;

public class MainRunner extends AbstractTestNGCucumberTests {

    private static final Logger log                = LoggerFactory.getLogger(MainRunner.class);
    private static final String THREAD_COUNT_KEY   = "dataproviderthreadcount";
    private static String THREAD_COUNT_VALUE = TestRunConfig.THREAD_COUNT;
    private static final boolean isParallel        = true;
    private TestNGCucumberRunner testNGCucumberRunner;
    private Base_PO base; private API_Operations apiop; private Fillo_operations fo;
    ExtentHtmlReporter htmlReporter; ExtentSparkReporter spark;
    ExtentReports extent; public static ExtentTest test;
    static DateUtilities date                      = new DateUtilities();
    public static ThreadLocal < ExtentTest > test1 = new ThreadLocal < ExtentTest > ();
    Fillo fillo                                    = new Fillo();
    public static com.codoid.products.fillo.Connection connection = null;
    String img_dir   = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + "-" + RandomGenerator.numeric(2);
    String date_time = date.getdate_time();
    String PathR     = System.getProperty("user.dir") + "/Reports/" + date_time + "_Report/";
    String Policynumber;
    String faillist;

    int rowno=2,cellno=2,con=0;
    String[] sectionalpremium=new String[2];
    String[] uwrule=new String[2];
    String premium=null;
    String[] a;
    //*****************************************ExcelFilePath*****************************************************
    String ExcelPath = System.getProperty("user.dir")+ "/src/main/resources/WorkBook/SmokeFileCT.xlsx";  //***

    //******************************************ExcelFilePath******************************************************
    //private ExcelOperations operator;
    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws IOException, FilloException {
        String filePath = null;

        connection                  = fillo.getConnection(ExcelPath);
        String testlabquery         = "Select * from Test_Lab where Execution_Flag='Yes'";
        Recordset recordsettestcase = connection.executeQuery(testlabquery);

        while(recordsettestcase.next())
        {
            if(recordsettestcase.getField("ParallelExecution").toUpperCase().equals("YES"))
                THREAD_COUNT_VALUE="3";
            break;
        }

        System.setProperty(THREAD_COUNT_KEY, THREAD_COUNT_VALUE);
        try {
            if (!System.getProperty("os.name").toLowerCase().contains("linux"))
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        htmlReporter                     = new ExtentHtmlReporter(PathR + "testReport_" + date_time + ".html");
        spark                            = new ExtentSparkReporter(PathR + "testReport_spark.html");
        Workbook workbook                = new XSSFWorkbook();
        FileOutputStream outputStream    = new FileOutputStream(PathR + "ExcelTestReport.xlsx");
        workbook.write(outputStream);
        workbook.close();
        System.out.println(PathR);

        connection                       = fillo.getConnection(PathR + "ExcelTestReport.xlsx");

        connection.createTable("FAIL_TestResults", new String[] {
                "TestCase",
                "RequestCovId",
                "ResponseCovId",
                "RequestXpath",
                "ResponseXpath",
                "RequestAttribute",
                "ReqAttributeValue",
                "ResponseAttribute",
                "ResAttributeValue"
        });

        String imgdir                    = date.getdate_time();
        extent                           = new ExtentReports();

        extent.attachReporter(htmlReporter, spark);
        htmlReporter.config().setDocumentTitle("Covertree Automation Report");
        htmlReporter.config().setReportName("TEST REPORT");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");


    }

    @Override
    @DataProvider(parallel = isParallel)
    public Object[][] scenarios() {
        if (testNGCucumberRunner == null) {
            testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        }
        return testNGCucumberRunner.provideScenarios();
    }

    @DataProvider(name = "testlabcases",parallel = true)
    public Object[][] ParallelExcelExec() throws FilloException {

        connection                  = fillo.getConnection(ExcelPath);
        String testlabquery         = "Select * from Test_Lab where Execution_Flag='Yes'";
        Recordset recordsettestcase = connection.executeQuery(testlabquery);
        List<Object[]> testlabcases = new ArrayList<Object[]>();




        while (recordsettestcase.next()) {
            String testing          = recordsettestcase.getField("Test_case");
            String state            = recordsettestcase.getField("state specific");
            String Token            = recordsettestcase.getField("Authentication_Token");
            String Project          = recordsettestcase.getField("Project");
            String description      = recordsettestcase.getField("Description");

            testlabcases.add(new Object[] {testing,state,Token,Project,description});
        }
        return testlabcases.toArray(new Object[testlabcases.size()][]);
    }

    @Test(dataProvider = "testlabcases")
    public void runScenario(String TestCaseName,String state, String Token,String Project, String description) throws FilloException {

        //*******************************************************Variables**************************************************************************
        String  step=null, Action=null, obj=null, txt=null, xpath=null, sno=null, teststep=null, Environment=null, verb=null, headder1=null,
                validateRes=null, Endpoint=null, requestattribute=null, request=null, variables=null, responseattribute=null, IgnoredCoverage = null,
                Expected1 = null ,Avar1,AddLine=null;
        String[] Expected = new String[0];
        String[] requestattributeArray = new String[0];
        String[] resattributeArray = new String[0];
        String[] IgnoredCoverageArray =new String[0];
        String[] headder=new String[0];
        String Attributevariables[]=new String[0];
        String addLine[]=new String[0];
        int flag=0;int api=0;

        //*************************************************************************************************************************************



            connection              = fillo.getConnection(ExcelPath); //Relative address no need to modify
            String strQuery1        = "Select * from " +TestCaseName;
            Recordset recordset1    = connection.executeQuery(strQuery1);


            base                    = new Base_PO();
            apiop                   = new API_Operations();
            fo                      = new Fillo_operations();

            System.out.println(Policynumber);

            if (recordset1.getFieldNames().contains("EndPoint")) {

                api                 = 1;
                if (!Project.toUpperCase().contains("FAMI"))
                    test1.set(extent.createTest(TestCaseName, description));

            }
            else {

                api                 = 0;
                test1.set(extent.createTest(TestCaseName, description));

            }

            try {

                while (recordset1.next()) {
                    flag           = 0;

                    if (api == 1) {

                        sno                = recordset1.getField("TestcaseName");
                        teststep           = recordset1.getField("TestDescription");
                        Environment        = recordset1.getField("Environment");
                        verb               = recordset1.getField("Verb");
                        Endpoint           = recordset1.getField("EndPoint");
                        headder1           = recordset1.getField("Hedder");
                        headder            = headder1.split(",");
                        request            = recordset1.getField("Request");
                        Avar1              = recordset1.getField("AttributeValue");
                        Attributevariables = Avar1.split(",");
                        variables          = recordset1.getField("SetVariables");
                        validateRes        = recordset1.getField("Response");


                        if (Project.toUpperCase().contains("FAMI")) {

                            test1.set(extent.createTest(sno, teststep));
                            requestattribute      = recordset1.getField("RequestAttributes");
                            requestattributeArray = requestattribute.split(",");
                            responseattribute     = recordset1.getField("ResponseAttributes");
                            resattributeArray     = responseattribute.split(",");
                            IgnoredCoverage       = recordset1.getField("NotImplementedCoverages");
                            IgnoredCoverageArray  = IgnoredCoverage.split(",");
//                            AddLine               = recordset1.getField("AddLine");
//                            addLine               = AddLine.split(",");


                        }
                        else
                        {
                            Expected1            = recordset1.getField("Expected");
                            sno                  = TestCaseName;
                            Expected             = Expected1.split("\\|\\|");
                        }
                    }
                    else
                    {

                        System.out.println(recordset1.getField("Test_Step"));

                        if (recordset1.getField("custom").equals(state) || recordset1.getField("custom").contains(state) || recordset1.getField("custom").equals("")) {

                            step               = recordset1.getField("Test_Step");
                            obj                = recordset1.getField("Object_name");
                            Action             = recordset1.getField("Action");
                            txt                = recordset1.getField("Test_data");
                            xpath              = fo.getxpath(obj);

                        }
                    }



                    if (api == 0) {

                        base.actions(Action, xpath, txt, Policynumber);
                        if(Action.equals("gettext")) {
                            premium = base.premiummatch();
                            if (premium.equals(txt)) {
                                test1.get().log(PASS, "Expected premium is : " + " " + txt + "actual premium is : " + " " + premium);
                            } else {
                                test1.get().log(FAIL, "Expected premium is : " + " " + txt + "actual premium is : " + " " + premium);
                            }
                        }
                        if(Action.equals("sectionalpremiumvalidation")){
                            sectionalpremium =base.sectionalpremiumvalidation();
                            if(sectionalpremium[1].length()>31)
                            {
                                test1.get().log(FAIL,sectionalpremium[1]);
                                test1.get().log(PASS,sectionalpremium[0]);
                            }
                            else{
                            test1.get().log(PASS,sectionalpremium[0]);}


                        }

                        if(Action.equals("uwvalidation")){
                            uwrule =base.uwrulevalidation();
                            if(uwrule[1].length()>30)
                            {

                                test1.get().log(PASS,uwrule[0]);
                                test1.get().log(FAIL,uwrule[1]);
                            }
                            else{
                                test1.get().log(PASS,uwrule[0]);}


                        }

                        if(Action.equals("cptable")){
                                faillist=base.controlpanelvalidation();
                                if(faillist.length()>2){

                                    test1.get().log(FAIL,"Control panel validation Failure for :\n "+faillist);
                                }
                                else{
                                    test1.get().log(PASS,"Control panel validation succesfull and All flows have passed");
                                }

                            }



                        test1.get().log(PASS, "<b>Test Step    : <b>" + step + "<br>" +
                                              "<b>Object name  : <b>" + xpath +"<br>" +
                                              "<b>Action method: <b>" + Action +"<br>" +
                                              "<b>Testdata used: <b>" + txt);
                        test1.get().pass("Screenshot captured: ", MediaEntityBuilder.createScreenCaptureFromPath(capture(base.getDriver(), img_dir)).build());

                    }

                    else {

                        apiop.apiactions(Environment,           verb,              Endpoint,            headder,
                                         request,               variables,         validateRes,
                                         requestattributeArray, resattributeArray, IgnoredCoverageArray,
                                         sno,                   PathR,             Attributevariables,
                                         Token,                 Project,           Expected, addLine);


                        test1.get().log(PASS, "<b>Environment  :<b>" + Environment + "<br>" +
                                              "<b>Endpoint url :<b>"+ Endpoint + "<br> " +
                                              "<b>Request file :<b>"+ request + "<br>" +
                                              "<b>Response file:<b>"+ validateRes);

                        String request1        = apiop.sendreq();
                        test1.get().log(PASS, "Request:    <textarea rows='20' cols='40' style='border:none;'>" + request1 + "<response><abc value=10></abc></response>" + "</textarea>");

                        String response       = apiop.sendres();
                        test1.get().log(PASS, "Response:    <textarea rows='20' cols='40' style='border:none;'>" + response + "<response><abc value=10></abc></response>" + "</textarea>");

                        if (Project.toUpperCase().contains("FAMI"))
                            test1.get().log(INFO, "<b>These are the Ignored Coverages: " + IgnoredCoverage.replace(",", ", ") + "</b>");

                        int i                 = 0;

                        while (apiop.send_notmatchedresults()[i] != null) {

                            if (apiop.send_notmatchedresults()[i].contains("PASS,"))
                                test1.get().log(PASS, String.format(apiop.send_notmatchedresults()[i]));

                            else
                                test1.get().log(FAIL, String.format(apiop.send_notmatchedresults()[i]));
                                i++;
                        }

                        Policynumber = apiop.getpolicynumber();

                        test1.get().log(PASS,TestCaseName+" Policy no is: "+Policynumber);
                        String ExcelPaths = System.getProperty("user.dir")+ "/src/main/resources/WorkBook/API/";

                        Workbook workbook = new XSSFWorkbook();
                        Sheet sheet = workbook.createSheet("Sheet1");
                        Boolean isValuePresent=true;
                        // Write the runtime variable to the Excel file

                        int lastRowNum = sheet.getLastRowNum();

                        // Write the value to the subsequent row if the element is present

                            Row newRow = sheet.createRow(lastRowNum + 1);
                            Cell cello = newRow.createCell(1);
                            cello.setCellValue(Policynumber);





                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(ExcelPaths+"runtime_variable.xlsx");
                            workbook.write(fileOutputStream);
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }





                    }

                }

                base.getDriver().switchTo().newWindow(WindowType.WINDOW);
                String current          = base.getDriver().getWindowHandle();
                for (String windowHandle: base.getDriver().getWindowHandles()) {
                    if (!current.contentEquals(windowHandle)) {
                        base.getDriver().switchTo().window(windowHandle).close();
                    }}
                base.getDriver().switchTo().window(current);

            }
            catch (Exception e) {

                try {
                    String excep         = e.getMessage();
                    test1.get().log(FAIL, "<b>Test Step    : <b>" + step + "<br>" +
                                          "<b>Object name  : <b>" + xpath +"<br>" +
                                          "<b>Action method: <b>" + Action +"<br>" +
                                          "<b>Testdata used: <b>" + txt);

                    test1.get().fail("Screenshot captured: ", MediaEntityBuilder.createScreenCaptureFromPath(capture(base.getDriver(), img_dir)).build());
                    test1.get().log(FAIL, "Error: " + excep + "");
                    System.out.println(excep);

                }

                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                base.getDriver().switchTo().newWindow(WindowType.WINDOW);

            }
        }




        @AfterClass(alwaysRun = true)
    public void testDownClass() throws IOException {
        extent.flush();
        connection.close();
        base = new Base_PO();
            base.getDriver().close();
            base.getDriver().quit();
    }


}