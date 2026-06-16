package com.qaprep.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthApi {

public String generateToken() {

    String body = "{"
            + "\"username\":\"admin\","
            + "\"password\":\"password123\""
            + "}";

    Response response = RestAssured.given()
            .contentType("application/json")
            .body(body)
            .post("/auth");

    if (response.getStatusCode() != 200) {
        throw new RuntimeException(
                "Auth request failed: " + response.getStatusCode());
    }

    return response.jsonPath().getString("token");
}

}