package StepDefinitions;

import POJO.AddPlace;
import POJO.Location;
import Resources.APIResources;
import Resources.TestData;
import Resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class APIStepDefinitions extends Utils {
    RequestSpecification request;
    ResponseSpecification addPlaceResSpec;
    Response response;
    TestData  testData=new TestData();

    @Given("Prepare Add Place Payload with {string} {string} {string} details")
    public void prepare_add_place_payload(String name , String address, String phoneNumber) throws IOException {

        request=given().spec(requestSpecification()).body(testData.addPlacePayload(name,address,phoneNumber));

    }

    @When("User makes {string} request with {string}")
    public void user_makes_request(String httpMethodType,String resource) {
        APIResources resourceAPI=APIResources.valueOf(resource);
        addPlaceResSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(httpMethodType.equalsIgnoreCase("POST")) {
            response = request.when().post(resourceAPI.getResource());
        }
        else if(httpMethodType.equals("GET")) {
            response = request.when().get(resourceAPI.getResource());
        }


    }

    @Then("The status code should be {int}")
    public void the_status_code_should_be(int statusCode) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(response.getStatusCode(),statusCode);
    }

    @Then("Validate that {string} in response body is {string}")
    public void in_response_body_is(String Key, String Value) {
        // Write code here that turns the phrase above into concrete actions
        String res=response.asString();
        JsonPath resString=new JsonPath(res);
        assertEquals(resString.getString(Key),Value);
    }

    @And("Verify if the place added has the name {string}")
    public void verifyIfThePlaceAddedHasTheName(String expectedPlaceName) throws IOException {
        String placeId=getJsonValue(response,"place_id");
        request=  given().spec(requestSpecification()).queryParam("place_id",placeId);
        user_makes_request("GET","GetPlaceAPI");
        String actualPlaceName=getJsonValue(response,"name");
        assertEquals(expectedPlaceName,actualPlaceName);
    }

    @Given("Prepare Delete Place payload")
    public void prepareDeletePlacePayload() throws IOException {
        String placeId=getJsonValue(response,"place_id");
        request=given().spec(requestSpecification()).body(testData.deletePlacePayload(placeId));
    }
}
