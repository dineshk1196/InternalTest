package utils.dbOperations;

import model.DataSheetType;
import model.User;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import utils.excelOperations.ExcelOperations;

import java.util.List;

public class QueryTestDataExcel {
    private Logger log = LoggerFactory.getLogger(QueryTestDataExcel.class);
    /*
     * Retrives the user
     */
    public User retrieveUserObj(User user) {
        if (user == null)
            return null;

        List<User> userList = new ExcelOperations<User>().get(DataSheetType.USERS);
        for (User curr : userList) {
            if (curr.getUserRole().equalsIgnoreCase(user.getUserRole()) &&
                    curr.getEnvironment().contains(user.getEnvironment())) {
                return curr;
            }
        }
        Assert.fail("Record not found:retrieveUserObj");
        return null;
    }
//
//    public Submission retrieveSubmissionObj(String submissionId) {
//        if (submissionId == null)
//            return null;
//        // Fetch Submission record from submission sheet
//        List<Submission> submissionList = new ExcelOperations<Submission>().get(DataSheetType.SUBMISSION);
//        for (Submission curr : submissionList) {
//            if (curr.getSubmissionId().equalsIgnoreCase(submissionId)) {
//                return curr;
//            }
//        }
//        Assert.fail("Record not found:retrieveSubmissionObj");
//        return null;
//    }
}
