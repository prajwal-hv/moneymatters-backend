package com.prajwal.moneymatters.dto;

public class UserLoginResponse {

    private final String token;
    private final String type = "Bearer";

    public UserLoginResponse(String token){
        this.token= token;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
