package answer;

import cucumber.api.DataTable;
import cucumber.api.java8.En;
import support.WhoAreWePageSupport;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WhoAreWePageSteps implements En {

    public WhoAreWePageSteps() {
        WhoAreWePageSupport whoAreWePageSupport = new WhoAreWePageSupport();

        When("^I navigate to the Answer Digital Who Are We page$", () -> {
            whoAreWePageSupport.navigateToPage();
        });

        Then("^the following company values are displayed:$", (DataTable values) -> {
            List expValues = values.asList(String.class);
            assertThat(whoAreWePageSupport.getCompanyValues(), is(expValues));
        });

        When("^I play the Answer Atmosphere video$", () -> {
            whoAreWePageSupport.playAnswerAtmosphereVideo();
        });

        Then("^the Answer Atmosphere video plays$", () -> {
            try {
                whoAreWePageSupport.isAnswerAtmosphereVideoPlaying();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

}
