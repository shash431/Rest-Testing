package step_definitions;

import cucumber.api.DataTable;
import cucumber.api.java8.En;
import io.restassured.response.Response;
import support.ResponseSupport;
import support.RestSupport;

public class FakeApiSteps implements En {
    private static final String POSTS = "posts/";

    private RestSupport restSupport = new RestSupport();
    private Response response;
    private ResponseSupport responseSupport = new ResponseSupport();

    public FakeApiSteps() {
        When("I get post (\\d+)", (Integer postNo) -> {
            response = restSupport.get(POSTS + postNo);
        });

        When("^I delete post (\\d+)$", (Integer postNo) -> {
            response = restSupport.delete(POSTS + postNo);
        });

        When("^I get all posts$", () -> {
            response = restSupport.get(POSTS);
        });

        Then("the response returns a HTTP (\\d+) status code", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        Then("^the response has (\\d+) entries$", (Integer i) -> {
            responseSupport.assertResponseBodyHasNumberOfEntries(response, i);
        });

        And("response includes the following:$", (DataTable table) -> {
            responseSupport.assertResponseBodyIncludes(response, table);
        });

        Then("^each entry has the following attributes:$", (DataTable table) -> {
            responseSupport.assertResponseBodyHasTheFollowingAttributes(response, table);
        });

        When("^I add a post with the following details:$", (DataTable table) -> {
            response = restSupport.post(POSTS, table);
        });
    }

}