package tests;

import model.LoginRequestModel;
import model.LoginResponseModel;
import org.testng.annotations.Test;
import util.Config;
import util.UserGenerator;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertNotNull;
import static steps.LoginSteps.postLogin;

public class LoginTest {

    @Test(description = "POST /auth/login -> check 200 status and validate body. Set token property")
    public void testLogin() {
        LoginRequestModel loginRequestModel = UserGenerator.createLoginRequestModel();
        LoginResponseModel loginResponse = postLogin(loginRequestModel);

        assertThat(loginResponse.getEmail()).isEqualTo(Config.getProperty("email"));
        assertNotNull(loginResponse.getToken());
        assertThat(loginResponse.getEmail()).isEqualTo(loginRequestModel.getEmail());

        Config.addProperty("token",loginResponse.getToken()); // Setting token property for future use in tests
        Config.addProperty("userId",loginResponse.getUserId()); // Setting userId property for future use in tests
    }

    @Test(description = "GET /user/{id} -> check 200 status")
    public void dummy() {
        given()
                .baseUri(Config.getProperty("baseUri"))
                .basePath("/users/" +Config.getProperty("userId"))
                .contentType("application/json")
                .header("Authorization",Config.getProperty("token"))
                .log().all()
                .when()
                .get()
                .then()
                .statusCode(200);
    }
}