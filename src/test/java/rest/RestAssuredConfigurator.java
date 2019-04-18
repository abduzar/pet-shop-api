package rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestAssuredConfigurator {

    public RestAssuredConfigurator() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "/pet";
    }

    public RequestSpecification requestSpecification() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("api_key", "special-key");
        builder.addHeader("Content-Type", "application/json");
        return builder.build();
    }

    public ResponseSpecification responseSpecification() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        builder.expectContentType("application/json");
        return builder.build();
    }
}
