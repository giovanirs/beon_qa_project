package com.qaprep.tests;

import com.qaprep.api.AuthApi;
import com.qaprep.api.BookingApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BookingApiTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        AuthApi authApi = new AuthApi();
        BookingApi bookingApi = new BookingApi();

        // Authentication
        String token = authApi.generateToken();

        System.out.println("Token generated");
        System.out.println("Token: " + token);

        // Create Booking
        int bookingId = bookingApi.createBooking();

        System.out.println("Booking created");
        System.out.println("Booking ID: " + bookingId);

        // Read Booking
        Response booking = bookingApi.getBooking(bookingId);

        System.out.println("GET Status: " + booking.getStatusCode());

        if (booking.getStatusCode() != 200) {
            throw new RuntimeException("GET booking failed");
        }

        String firstName =
                booking.jsonPath().getString("firstname");

        String lastName =
                booking.jsonPath().getString("lastname");

        System.out.println("Firstname: " + firstName);
        System.out.println("Lastname: " + lastName);

        if (!"Giovani".equals(firstName)) {
            throw new RuntimeException("Firstname validation failed");
        }

        if (!"Silva".equals(lastName)) {
            throw new RuntimeException("Lastname validation failed");
        }

        System.out.println("GET validation passed");

        // Update Booking
        Response update =
                bookingApi.updateBooking(bookingId, token);

        System.out.println("UPDATE Status: " +
                update.getStatusCode());

        System.out.println("Update Response:");
        System.out.println(update.asPrettyString());

        if (update.getStatusCode() != 200) {
            throw new RuntimeException("Update failed");
        }

        String checkoutDate =
                update.jsonPath()
                        .getString("bookingdates.checkout");

        System.out.println(
                "Updated checkout date: " + checkoutDate);

        if (!"2025-07-20".equals(checkoutDate)) {
            throw new RuntimeException(
                    "Checkout date not updated");
        }

        System.out.println("Update validation passed");

        // Delete Booking
        Response delete =
                bookingApi.deleteBooking(bookingId, token);

        System.out.println("DELETE Status: " +
                delete.getStatusCode());

        if (delete.getStatusCode() != 201) {
            throw new RuntimeException("Delete failed");
        }

        System.out.println("Delete successful");

        // Verify Delete
        Response verify =
                bookingApi.getBooking(bookingId);

        System.out.println("VERIFY Status: " +
                verify.getStatusCode());

        if (verify.getStatusCode() != 404) {
            throw new RuntimeException(
                    "Booking still exists after delete");
        }

        System.out.println("404 confirmed");
    }
}