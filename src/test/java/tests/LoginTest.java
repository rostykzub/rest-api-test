package tests;

import model.LoginRequestModel;
import model.LoginResponseModel;
import org.testng.annotations.Test;
import util.UserGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertNotNull;
import static steps.LoginSteps.postLogin;
import static util.Config.addProperty;
import static util.Config.getProperty;

public class LoginTest {

    @Test(description = "POST /auth/login -> check 200 status and validate body. Set token property")
    public void testLogin() {
        LoginRequestModel loginRequestModel = UserGenerator.createLoginRequestModel();
        LoginResponseModel loginResponse = postLogin(loginRequestModel);

        assertThat(loginResponse.getEmail()).isEqualTo(getProperty("email"));
        assertNotNull(loginResponse.getToken());
        assertThat(loginResponse.getEmail()).isEqualTo(loginRequestModel.getEmail());

        addProperty("token",loginResponse.getToken()); // Setting token property for future use in tests
    }
}