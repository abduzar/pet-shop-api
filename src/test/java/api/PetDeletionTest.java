package api;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PetDeletionTest extends BaseTest {

    @Test
    @DisplayName("Delete existing pet")
    @Description("Creating new pet and delete it")
    public void petRemoval() {
        this.created = petCreationWithID(jfairy.baseProducer().randomInt(999));
        given()
                .spec(defaultRequest)
                .delete("/" + this.created.getId())
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete not existing pet")
    @Description("Search if pet exists. If not, try to delete it")
    public void notExistingPetRemoval() {
        int petID = jfairy.baseProducer().randomInt(100);
        removePetWithID(petID);
        given()
                .spec(defaultRequest)
                .delete("/" + petID)
                .then()
                .assertThat()
                .statusCode(404);
    }
}
