package PLSBackOffice.Utilities.APIUtilities;


import PLSBackOffice.Utilities.APIUtilities.PojoClasses.BasePojo;
import PLSBackOffice.Utilities.APIUtilities.PojoClasses.Details;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;


import static PLSBackOffice.Utilities.Credentials.userCredentials;
import static io.restassured.RestAssured.given;

public class GenerateToken {

    public static String apiLogin(String userType) {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        Details details = new Details();
        BasePojo basePojo = new BasePojo();

        details.setUSER_NAME(userCredentials(userType).get("Username") + ":" + userCredentials(userType).get("COMPID"));
        details.setPASSWORD(userCredentials(userType).get("Password"));
        basePojo.setDETAILS(details);
        String SAT = "";
        try {
            SAT =
                    given()
                            .body(objectWriter.writeValueAsString(basePojo))
                            .header("SOURCE_REF", new Faker().number().randomDigitNotZero())
                            .contentType(ContentType.JSON).
                    when()
                            .post("/event-login-auth").
                    then()
                            .extract().jsonPath().getString("SESSION_AUTH_TOKEN");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return SAT;
    }
}
