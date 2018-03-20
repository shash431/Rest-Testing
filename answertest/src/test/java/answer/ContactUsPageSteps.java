package answer;

import cucumber.api.DataTable;
import cucumber.api.java8.En;
import support.ContactUsPageSupport;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactUsPageSteps implements En {

    private static final String TEST_NAME = "Test Name";
    private static final String TEST_EMAIL_ADDRESS = "test@email.com";
    private static final String TEST_MESSAGE = "Test message";

    public ContactUsPageSteps() {
        ContactUsPageSupport contactUsPageSupport = new ContactUsPageSupport();

        Given("^I navigate to the Answer Digital Contact Us page$", () -> {
            contactUsPageSupport.navigateToPage();
        });

        When("^I send a query for an empty form$", () -> {
            contactUsPageSupport.clickSendQueryButton();
        });

        Then("^the following validation messages are returned:$", (DataTable messages) -> {
            List expMessages = messages.asList(String.class);
            assertThat(contactUsPageSupport.getValidationMessages(), is(expMessages));
        });

        When("^I send a query populating only a name and an email address$", () -> {
            contactUsPageSupport.populateNameField(TEST_NAME);
            contactUsPageSupport.populateEmailField(TEST_EMAIL_ADDRESS);
            contactUsPageSupport.clickSendQueryButton();
        });

        When("^I send a query populating only a name, an email address and a message$", () -> {
            contactUsPageSupport.populateNameField(TEST_NAME);
            contactUsPageSupport.populateEmailField(TEST_EMAIL_ADDRESS);
            contactUsPageSupport.populateMessage(TEST_MESSAGE);
            contactUsPageSupport.clickSendQueryButton();
        });
    }

}