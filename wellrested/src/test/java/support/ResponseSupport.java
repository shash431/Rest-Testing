package support;

import com.google.gson.JsonElement;
import cucumber.api.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ResponseSupport {
    private static final String INCORRECT_NUMBER_OF_ENTRIES_MSG = "Incorrect number of entries returned.";
    private JsonSupport jsonSupport = new JsonSupport();

    public void assertResponseBodyIncludes(Response response, DataTable table) {
        Map<String, Object> expectedFields = table.asMap(String.class, Object.class);

        for (Map.Entry<String, Object> expectedField : expectedFields.entrySet()) {
            String path = expectedField.getKey();
            Object expectedValue = expectedField.getValue();
            String jsonBody = response.getBody().asString();
            JsonElement element = jsonSupport.getJsonElementFromPath(jsonBody, path);
            String actualValue = element.getAsString();
            assertThat(String.format("Attribute %s has an incorrect value.", path), actualValue, is(expectedValue));
        }
    }

    public void assertResponseBodyHasNumberOfEntries(Response response, int entriesNo) {
        String jsonBody = response.asString();
        ArrayList<Map<String, ?>> jsonAsArrayList = JsonPath.from(jsonBody).get("");
        assertThat(INCORRECT_NUMBER_OF_ENTRIES_MSG, jsonAsArrayList.size(), equalTo(entriesNo));
    }

    public void assertResponseBodyHasTheFollowingAttributes(Response response, DataTable table) {
        List<String> responseFields = table.asList(String.class);
        String jsonBody = response.asString();
        ArrayList<Map<String, ?>> jsonAsArrayList = JsonPath.from(jsonBody).get("");
        assertThat(jsonAsArrayList, notNullValue());

        for (Map<String, ?> entry : jsonAsArrayList) {
            for (String field : responseFields) {
                assertThat(entry.containsKey(field), is(true));
            }
        }
    }

}
