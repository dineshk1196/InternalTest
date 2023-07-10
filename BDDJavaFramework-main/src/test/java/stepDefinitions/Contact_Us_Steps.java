package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.netty.handler.codec.http.QueryStringDecoder;
import pageObjects.Base_PO;
import pageObjects.Contact_Us_PO;
import utils.PDFutils.ComparePDFVisually;
import utils.dbOperations.QueryTestDataExcel;

import java.util.List;

public class Contact_Us_Steps extends Base_PO {
    private Contact_Us_PO contact_us_po;
    private QueryTestDataExcel queryTestDataExcel;
    private ComparePDFVisually comparePDFVisually;

    public Contact_Us_Steps(Contact_Us_PO contact_us_po, QueryTestDataExcel queryTestDataExcel, ComparePDFVisually comparePDFVisually) {
        this.contact_us_po = contact_us_po;
        this.queryTestDataExcel = queryTestDataExcel;
        this.comparePDFVisually = comparePDFVisually;
    }

    @Given("I access the webdriver university contact us page")
    public void i_access_the_webdriver_university_contact_us_page() {
        contact_us_po.navigateTo_WebDriverUniversity_Contact_Us_Page();
    }

    @When("I enter a unique first name")
    public void i_enter_a_unique_first_name() {
        contact_us_po.setUnique_FirstName();
    }

    @And("I enter a unique last name")
    public void i_enter_a_unique_last_name() {
        contact_us_po.setUnique_LastName();
    }

    @And("I enter a unique email address")
    public void i_enter_a_unique_email_address() {
        contact_us_po.setUnique_EmailAddress();
    }

    @And("I enter a unique comment")
    public void i_enter_a_unique_comment() {
        contact_us_po.setUnique_Comment();
    }

    @When("I enter a specific first name {word}")
    public void i_enter_a_specific_first_name(String firstName) {
        contact_us_po.setSpecific_FirstName(firstName);
    }

    @When("I enter a specific last name {word}")
    public void i_enter_a_specific_last_name(String lastName) {
        contact_us_po.setSpecific_LastName(lastName);
    }

    @When("I enter a specific email address {word}")
    public void i_enter_a_specific_email_address(String email) {
        contact_us_po.setSpecific_EmailAddress(email);
    }

    @When("I enter a specific comment {string}")
    public void i_enter_a_specific_comment(String comment) {
        contact_us_po.setSpecific_Comment(comment);
    }

    @And("I click on the submit button")
    public void i_click_on_the_submit_button() {
        contact_us_po.clickOn_Submit_Button();
    }

    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
        contact_us_po.validate_Successful_SubmissionMessage_Text();
    }

    @When("I enter a specific first name, last name, email and comment")
    public void iEnterASpecificFirstNameLastNameEmailAndComment() {

    }

    @Given("User tests the pdf files")
    public void userTestsThePdfFiles() throws Exception {
        String file1 = "C:\\Users\\phaneendrapo\\Downloads\\CANCELLATION And NonRenewal Farm Insurance.pdf";
        String file2 = "C:\\Users\\phaneendrapo\\Downloads\\CANCELLATION And NonRenewal Farm Insurance1.pdf";
        comparePDFVisually.ComparePDFs(file1, file2);
    }

    @And("User store the result")
    public void userStoreTheResult() {

    }


//    @Given("the actor is configured with below variables")
//    public void theActorIsConfiguredWithBelowVariables(List<Actor> actorList) {
//        testContext.setSubmission(queryTestDataExcel.retrieveSubmissionObj(actorList.get(0).getSubmissionId()));
//
//    }
}
