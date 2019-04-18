package base;

import com.devskiller.jfairy.Fairy;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.Pet;
import org.junit.BeforeClass;
import rest.RestAssuredConfigurator;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class BaseTest {
    protected static RequestSpecification defaultRequest;
    protected static ResponseSpecification defaultResponse;
    protected static Fairy jfairy = Fairy.create();
    protected Gson gson = new Gson();
    protected Pet created;

    @BeforeClass
    public static void beforeClass() {
        RestAssuredConfigurator configurator = new RestAssuredConfigurator();
        defaultRequest = configurator.requestSpecification();
        defaultResponse = configurator.responseSpecification();
    }

    @Step
    protected Pet petCreationWithID(int petID) {
        Pet pet = Pet.generatePetWithID(petID);
        given()
                .spec(defaultRequest)
                .body(gson.toJson(pet))
                .post()
                .then()
                .spec(defaultResponse);
        return pet;
    }

    @Step
    protected Optional<Pet> findPetWithID(int petID) {
        Optional<Pet> search = Optional.empty();
        Response response = given()
                .spec(defaultRequest)
                .get("/" + petID);
        if (response.getStatusCode() == 200) {
            search = Optional.of(response.getBody().as(Pet.class));
        } else if (response.getStatusCode() == 404) {
            search = Optional.empty();
        }
        return search;
    }

    @Step
    protected void removePetWithID(int petID) {
        Optional<Pet> result = findPetWithID(petID);
        result.ifPresent(pet -> given()
                .spec(defaultRequest)
                .delete("/" + petID)
                .then()
                .log());
    }
}
