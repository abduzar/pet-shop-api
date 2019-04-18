package api;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.Pet;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PetCreationTest extends BaseTest {

    @Test
    @DisplayName("Pet creation test")
    @Description("Creating new pet and check that pet exists")
    public void petCreation() {
        this.created = petCreationWithID(jfairy.baseProducer().randomInt(999));
        Pet petOnServer = given()
                .spec(defaultRequest)
                .get("/" + this.created.getId())
                .then()
                .spec(defaultResponse)
                .extract().body().as(Pet.class);
        Assert.assertThat(petOnServer.getName(), is(this.created.getName()));
    }

    @Test
    @DisplayName("Pet creation with corrupted data")
    @Description("Pet creation tryout with incorrect data")
    public void corruptedDataPetCreation() {
        Pet pet = Pet.generatePetWithID(jfairy.baseProducer().randomInt(999));
        given()
                .spec(defaultRequest)
                .body(gson.toJson(pet).substring(10))
                .post()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(500);
    }
}
