package api;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import junit.framework.AssertionFailedError;
import models.Pet;
import org.junit.Test;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;

public class PetUpdateWithFormTest extends BaseTest {

    @Test
    @DisplayName("Update with form data")
    @Description("Creating new pet and delete it")
    public void petUpdateForm() {
        this.created = petCreationWithID(jfairy.baseProducer().randomInt(999));
        String updatedName = "Some New Name";
        String updatedStatus = "SOLD";
        given()
                .header("api_key", "special-key")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("name", updatedName)
                .formParam("status", updatedStatus)
                .post("/" + this.created.getId())
                .then()
                .assertThat()
                .statusCode(200);

        Optional<Pet> updatedPet = findPetWithID(this.created.getId());
        if (!updatedPet.isPresent())
            throw new AssertionFailedError("Pet with id " + this.created.getId() + " is lost somewhere");
        updatedPet.ifPresent(pet -> {
            assertThat(pet.getName(), is(updatedName));
            assertThat(pet.getStatus(), is(updatedStatus.toLowerCase()));
        });
    }
}
