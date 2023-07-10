package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int counter=0;

    @Override
    public boolean retry(ITestResult result) {
        if(counter< TestRunConfig.RETRY_COUNTER){
            counter++;
            return true;
        }
        return false;
    }
}


