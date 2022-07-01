package util;

import model.LoginRequestModel;

import static util.Config.getProperty;

public class UserGenerator {

    public static LoginRequestModel createLoginRequestModel(){
        return LoginRequestModel.builder()
                .email(getProperty("email"))
                .password(getProperty("password"))
                .build();
    }
}
