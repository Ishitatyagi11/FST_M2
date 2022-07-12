package Examples;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class getExample {
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp(){
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/pet")
                .setContentType(ContentType.JSON)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("status",equalTo("alive"))
                .expectBody("name",equalTo("Riley"))
                .build();

    }

    @Test(priority = 1)
    public void postRequestTest(){
        String reqBody = "{\"id\": 77232, \"name\": \"Riley\", \"status\": \"alive\"}";

        Response response = given().spec(requestSpec)
                .body(reqBody)
                .when().post();

        //Assertions
        response.then().spec(responseSpec);

    }

    @Test(priority = 2)
    public void getRequestTest(){
        Response response = given().spec(requestSpec)
                        .when().get();

        //Assertions

        response.then().spec(responseSpec);

    }

    @Test(priority = 3)
    public void deleteRequestTest(){
        Response response = given().spec(requestSpec)
                .when().delete("/user/keys/{keyId}");


        response.then().body("message",equalTo("77237"));

    }

}
