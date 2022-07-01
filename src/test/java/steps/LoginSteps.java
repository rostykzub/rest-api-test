package steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import model.LoginRequestModel;
import model.LoginResponseModel;
import static util.Config.getProperty;


import static io.restassured.RestAssured.given;

public class LoginSteps {

    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri(getProperty("baseUri")) //taken from config
                    .setBasePath("/auth/login")
                    .setContentType("application/json")
                    .build();

    public static LoginResponseModel postLogin(LoginRequestModel loginRequestModel){
        return given().spec(REQ_SPEC)
                .body(loginRequestModel)
                .post()
                .then()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
    }
}
