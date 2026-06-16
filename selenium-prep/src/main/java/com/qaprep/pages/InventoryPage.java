package com.qaprep.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage {

private WebDriver driver;

private By addToCartButtons =
        By.xpath("//button[contains(@id,'add-to-cart')]");

private By cartButton =
        By.className("shopping_cart_link");

private By cartBadge =
        By.className("shopping_cart_badge");

public InventoryPage(WebDriver driver) {
    this.driver = driver;
}

public void addFirstTwoProducts() {

    List<WebElement> products =
            driver.findElements(addToCartButtons);

    products.get(0).click();
    products.get(1).click();
}

public String getCartCount() {

    return driver.findElement(cartBadge).getText();
}

public void openCart() {

    driver.findElement(cartButton).click();
}

}
