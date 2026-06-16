package com.qaprep.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BookingApi {

public int createBooking() {

    String body = "{"
            + "\"firstname\":\"Giovani\","
            + "\"lastname\":\"Silva\","
            + "\"totalprice\":100,"
            + "\"depositpaid\":true,"
            + "\"bookingdates\":{"
            + "\"checkin\":\"2025-07-01\","
            + "\"checkout\":\"2025-07-10\""
            + "},"
            + "\"additionalneeds\":\"Breakfast\""
            + "}";

    Response response = RestAssured.given()
            .contentType("application/json")
            .body(body)
            .post("/booking");

    if (response.getStatusCode() != 200) {
        throw new RuntimeException(
                "Create booking failed: " + response.getStatusCode());
    }

    return response.jsonPath().getInt("bookingid");
}

public Response getBooking(int bookingId) {

    return RestAssured.given()
            .get("/booking/" + bookingId);
}

public Response updateBooking(int bookingId, String token) {

    String body = "{"
            + "\"firstname\":\"John\","
            + "\"lastname\":\"Doe\","
            + "\"totalprice\":100,"
            + "\"depositpaid\":true,"
            + "\"bookingdates\":{"
            + "\"checkin\":\"2025-07-01\","
            + "\"checkout\":\"2025-07-20\""
            + "},"
            + "\"additionalneeds\":\"Breakfast\""
            + "}";

    return RestAssured.given()
            .contentType("application/json")
            .header("Cookie", "token=" + token)
            .body(body)
            .put("/booking/" + bookingId);
}

public Response deleteBooking(int bookingId, String token) {

    return RestAssured.given()
            .header("Cookie", "token=" + token)
            .delete("/booking/" + bookingId);
}

}
