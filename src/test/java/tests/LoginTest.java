package tests;

import model.LoginRequestModel;
import model.LoginResponseModel;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertNotNull;
import static steps.LoginSteps.postLogin;
import static util.Config.getProperty;

public class LoginTest {


    LoginRequestModel loginRequestModel = LoginRequestModel.builder()
            .email(getProperty("email"))
            .password(getProperty("password"))
            .build();


    @Test(description = "POST /auth/login -> check 200 status and validate body")
    public void testLogin() {
        LoginResponseModel loginResponse = postLogin(loginRequestModel);
        assertThat(loginResponse.getEmail()).isEqualTo(getProperty("email"));
        assertNotNull(loginResponse.getToken());
        assertThat(loginResponse.getEmail()).isEqualTo(loginRequestModel.getEmail());
    }
}
