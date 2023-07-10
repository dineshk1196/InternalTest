package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestRunConfig{
    private static Logger log = LoggerFactory.getLogger(TestRunConfig.class);

    public static String BROWSER = get("browser");
    public static final boolean IS_PARALLEL = Boolean.parseBoolean(get("isParallel"));
    public static final String THREAD_COUNT = get("threadCount");
    public static String ENV = get("env");
    public static String BASE_URL = getBaseUrl(ENV);
    public static int RETRY_COUNTER=Integer.parseInt("0");
    public static final boolean ScreenShot_All = Boolean.parseBoolean(get("screenshotForAll"));
    public static String EXCEL_FILE_NAME = get("excelFileName");
    public static String LOB = get("lob");

    //********************************************************APIPath****************************************************************************************************
    public static final String APIPath = "C:\\Users\\DineshKumarKrishnamo\\Videos\\HYB\\BDDJavaFramework-maine\\BDDJavaFramework-main - Copy\\BDDJavaFramework-main\\src\\main\\resources\\WorkBook\\API";
    //*********************************************************************************************************************************************************************************
    public static String BS_USERNAME = getBS("bsUserName");
    public static String BS_ACCESSKEY = getBS("bsAccessKey");
    public static String BS_BROWSERNAME = getBS("browserName");
    public static String BS_DEVICE = getBS("device");
    public static String BS_OSVERSION = getBS("os_version");


    public TestRunConfig() {
    }

    public static String get(String property) {
        String value = null;

        if (System.getenv(property) != null) {
            value = System.getenv(property);
            log.info(String.format("from env:%s:%s", property, value));
        } else if (System.getenv(property.toUpperCase()) != null) {
            value = System.getenv(property);
            log.info(String.format("from env:%s:%s", property.toLowerCase(), value));
        } else if (System.getProperty(property) != null) {
            value = System.getProperty(property);
            log.info(String.format("from property:%s:%s", property, value));
        } else {
            try {
                FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");
                Properties properties = new Properties();
                properties.load(inputStream);
                value = properties.getProperty(property);

            }catch (IOException ex){
                log.error("config.properties file not found");
            }
        }
        return value;
    }

    private static String getBaseUrl(String key) {
        String value=null;
        try {
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/environment.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            value = properties.getProperty(key + ".base.url");
        }catch (IOException ex){
            log.error("environment.properties file not found");
        }
        return value;
    }

    public static String getBS(String property){
        String value = null;
        try {

            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/browserstack.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            value = properties.getProperty(property);

        }
        catch (IOException ex){
            log.error("browserstack.properties file not found");
        }
        return value;
    }

    public static String getpath(String Property) throws FileNotFoundException {
        String value=null;
        try{

            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            value = properties.getProperty(Property);

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
}
