package utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Policy;

@Setter
@Getter
@ToString
public class TestContext {
    private Policy policy;
    private StringBuilder testLogs =new StringBuilder();

    public String getTestLogs (){
        return testLogs.toString();
    }

    public void setTestLogs(String log){
        if(null!= log)
            testLogs.append(log);
    }

}
