package com.qaprep.tests;

import org.openqa.selenium.WebDriver;

import com.qaprep.pages.CartPage;
import com.qaprep.pages.CheckoutPage;
import com.qaprep.pages.InventoryPage;
import com.qaprep.pages.LoginPage;
import com.qaprep.utils.DriverFactory;

public class PurchaseUiTest {

public static void main(String[] args) {

    WebDriver driver = DriverFactory.getDriver();

    try {

        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Login
        loginPage.login(
                "standard_user",
                "secret_sauce");

        // Add two products
        inventoryPage.addFirstTwoProducts();

        if (!"2".equals(inventoryPage.getCartCount())) {
            throw new RuntimeException("Cart count is incorrect");
        }

        // Open cart
        inventoryPage.openCart();

        // Verify cart
        if (cartPage.getNumberOfItems() != 2) {
            throw new RuntimeException(
                    "Expected 2 items in cart");
        }

        // Checkout
        cartPage.checkout();

        checkoutPage.fillCustomerInfo(
                "John",
                "Doe",
                "12345");

        checkoutPage.finishOrder();

        // Final verification
        String message =
                checkoutPage.getConfirmationMessage();

        if (!"Thank you for your order!"
                .equals(message)) {

            throw new RuntimeException(
                    "Confirmation message not found");
        }

        System.out.println(
                "UI challenge completed successfully");

    } finally {

        driver.quit();
    }
}

}
