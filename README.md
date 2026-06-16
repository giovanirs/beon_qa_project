# QA Automation Challenge

This project contains solutions for both API and UI automation challenges using Java.

## Tech Stack

* Java 21
* Selenium WebDriver
* Rest Assured
* Maven

## Project Structure

```text
src
├── main
│   └── java
│       └── com.qaprep
│           ├── api
│           │   ├── AuthApi.java
│           │   └── BookingApi.java
│           ├── pages
│           │   ├── LoginPage.java
│           │   ├── InventoryPage.java
│           │   ├── CartPage.java
│           │   └── CheckoutPage.java
│           └── utils
│               └── DriverFactory.java
│
└── test
    └── java
        └── com.qaprep.tests
            ├── BookingApiTest.java
            └── PurchaseUiTest.java
```

## API Automation

The API solution covers the complete booking lifecycle using the Restful Booker application:

* Authentication
* Create Booking
* Read Booking
* Update Booking
* Delete Booking
* Verify deletion (404)

## UI Automation

The UI solution automates the SauceDemo purchase flow:

* Login
* Add two products to cart
* Cart validation
* Checkout
* Order confirmation

The UI framework follows the Page Object Model (POM) pattern to separate page interactions from test logic.

## Running the Tests

API Test:

```bash
mvn exec:java -Dexec.mainClass="com.qaprep.tests.BookingApiTest"
```

UI Test:

```bash
mvn exec:java -Dexec.mainClass="com.qaprep.tests.PurchaseUiTest"
```
