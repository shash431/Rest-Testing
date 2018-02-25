package support;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cucumber.api.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestSupport {
    private static final String APPLICATION_JSON = "application/json";
    private static final String REQUEST_HEADER = "==== REQUEST ======";
    private static final String RESPONSE_HEADER = "==== RESPONSE =====";
    private static final String FOOTER = "===================";
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String DELETE = "DELETE";
    private static final String FAKE_API_BASE_URI = "fake_api_base_uri";
    private static final String OUTPUT_CALLS_TO_CONSOLE_ENABLED = "output_calls_to_console_enabled";

    private RequestSpecification request = RestAssured.given();
    private String requestUri;
    private String requestBody;
    private Response response;
    private PropertiesSupport prop = new PropertiesSupport();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private boolean outputToConsoleEnabled =
            Boolean.parseBoolean(prop.getPropertyValue(OUTPUT_CALLS_TO_CONSOLE_ENABLED));

    public Response get(String path) {
        response = getDefaultRequest().get(path);
        requestUri += String.format("/%s", path);
        printGetRequestToConsole();
        return getResponse();
    }

    public Response post(String path, DataTable table) {
        Map<String, Object> json = table.asMap(String.class, Object.class);
        response = getDefaultRequest().body(json).post(path);
        requestUri += String.format("/%s", path);
        requestBody = gson.toJson(json);
        printPostRequestToConsole();
        return getResponse();
    }

    public Response delete(String path) {
        response = getDefaultRequest().delete(path);
        requestUri += String.format("/%s", path);
        printDeleteRequestToConsole();
        return getResponse();
    }

    private RequestSpecification getDefaultRequest() {
        requestUri = prop.getPropertyValue(FAKE_API_BASE_URI);
        request.baseUri(requestUri);
        request.contentType(APPLICATION_JSON);
        return request;
    }

    private Response getResponse() {
        printResponseToConsole();
        return response;
    }

    private void printGetRequestToConsole() {
        printRequestWithoutBodyToConsole(GET);
    }

    private void printDeleteRequestToConsole() {
        printRequestWithoutBodyToConsole(DELETE);
    }

    private void printRequestWithoutBodyToConsole(String httpMethod) {
        if (outputToConsoleEnabled) {
            System.out.println(String.format("\n%s", REQUEST_HEADER));
            System.out.println(String.format("%s %s", httpMethod, requestUri));
            System.out.println(String.format("%s\n", FOOTER));
        }
    }

    private void printPostRequestToConsole() {
        printRequestWithBodyToConsole(POST);
    }

    private void printRequestWithBodyToConsole(String httpMethod) {
        if (outputToConsoleEnabled) {
            System.out.println(String.format("\n%s", REQUEST_HEADER));
            System.out.println(String.format("%s %s", httpMethod, requestUri));
            System.out.println(requestBody);
            System.out.println(String.format("%s\n", FOOTER));
        }
    }

    private void printResponseToConsole() {
        if (outputToConsoleEnabled) {
            System.out.println(RESPONSE_HEADER);
            System.out.println(response.getHeaders().toString());
            response.prettyPrint();
            System.out.println(String.format("%s\n", FOOTER));
        }
    }

}
