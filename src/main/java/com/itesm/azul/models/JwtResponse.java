package com.itesm.azul.models;

public class JwtResponse {

    public JwtResponse(String token, String type) {
        this.token = token;
        this.type = type;
    }

    private String token;
    private String type = "Bearer";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
