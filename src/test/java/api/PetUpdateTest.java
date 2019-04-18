package api;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.Pet;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;

public class PetUpdateTest extends BaseTest {

    @Test
    @DisplayName("Update existing pet")
    @Description("Update existing pet data")
    public void updateExcisingPet() {
        int petID = jfairy.baseProducer().randomInt(200);
        String freshPetName = jfairy.person().getUsername();
        Pet freshPet = petCreationWithID(petID);
        String oldName = freshPet.getName();
        freshPet.setName(freshPetName);
        Pet petFromServer = given()
                .spec(defaultRequest)
                .body(gson.toJson(freshPet))
                .put()
                .then()
                .spec(defaultResponse)
                .extract().body().as(Pet.class);
        assertThat(petFromServer.getName(), is(not(oldName)));
        assertThat(petFromServer.getName(), is(freshPetName));
    }

    @Test
    @DisplayName("Update with incorrect ID")
    @Description("Update pet with incorrect value ID")
    public void updatePetWithInvalidID() {
        Pet pet = petCreationWithID(jfairy.baseProducer().randomInt(200));
        int petID = pet.getId();
        given()
                .spec(defaultRequest)
                .body(gson.toJson(pet).replace("\"id\":" + petID, "\"id\": abc"))
                .log().body()
                .put()
                .then()
                .log().all()
                .assertThat()
                .statusCode(400);
    }

}
