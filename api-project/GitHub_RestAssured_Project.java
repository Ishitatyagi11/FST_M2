package liveProject;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class GitHub_RestAssured_Project {
    RequestSpecification requestSpec;
    RequestSpecification responseSpec;
    String ssh = "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIDebX2GyMYniLVSKmUDwzciiNqZnj5fIDpFD4uRu9ynf gmx\\0662h6744@DESKTOP-3ST4QS8";
    Integer keyId = 25519;
    String authorizationHeader = "token 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";

    @BeforeClass
    public void setUp(){
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", authorizationHeader)
                .setBaseUri("https://api.github.com")
                .build();


        responseSpec = new RequestSpecBuilder()
                .build();


    }

    @Test(priority = 1)
    public void postRequest(){
        String reqBody =  "{\"title\": \"TestAPIKey\", \"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAg....\"}";

        Response response = given().spec(requestSpec)
                .pathParam("keyID",keyId)
                .body(reqBody)
                .when().post("/user/keys");

        //Assertions
        response.then().statusCode(201);
        //response.then().spec(responseSpec);

    }

    @Test(priority = 2)
    public void getRequest(){
        Response response = given().spec(requestSpec)
                .pathParam("keyID",keyId)
                .when().get("\"/user/keys/{keyId}\"");
        System.out.println("Response in get method is: " +response);

        response.then().statusCode(200);
        //response.then().spec(responseSpec);


    }

    @Test(priority = 3)
    public void delRequest(){
        Response response = given().spec(requestSpec)
                .pathParam("keyID",keyId)
                .when().delete(" /user/keys/{keyId}");
        System.out.println("response in delete method is: " +response);
        response.then().statusCode(204);
        response.then().body("message", equalTo(ssh));

    }


}
