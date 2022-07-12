package Examples;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class FirstProg {

    @Test
    public void newOne(){
    baseURI = "https://petstore.swagger.io/v2/pet";


    Response response =

            given().contentType(ContentType.JSON) // Set headers

                    .when().get(baseURI + "/findByStatus?status=sold"); // Run GET request


    String responseBody = response.getBody().asString();

        System.out.println("Response Body is =>  " +responseBody);



    // Assertions

        response.then().statusCode(200);

        response.then().body("[0].status", equalTo("sold"));

        responseBody = response.getBody().prettyPrint();
}

}
