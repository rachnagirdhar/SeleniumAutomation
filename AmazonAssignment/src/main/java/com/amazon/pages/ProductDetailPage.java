package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.Util.WebFunctions;
import com.aventstack.extentreports.Status;

public class ProductDetailPage extends WebFunctions {

	WebDriver driver;

	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "nav-cart-count")
	WebElement addToCartOption;

	@FindBy(id = "add-to-cart-button")
	WebElement addToCart;

	@FindBy(xpath = "//*[@id='huc-v2-order-row-confirm-text']/h1")

	WebElement addedTocartText;

	public void addToCart() {

		try {

			test.log(Status.INFO, "Adding item to the cart");
			log.info("====Clicking on add to cart to add item into the cart====");

			elementClick(addToCart);
		} catch (Exception e) {
			log.info(e);
		}

	}

	public String addedItemCartText() {

		String actualCartText = null;
		try {
			log.info("====Fetching items added to cart text for verification====");
			actualCartText = getElementTitle(addedTocartText);

		} catch (Exception e) {
			log.info(e);
		}
		return actualCartText;

	}

	public ShoppingCartPage navigateToShoppoingCart() {
		try {
			test.log(Status.INFO, "Navigating to Shopping cart");
			log.info("====Clicking on Shoppping cart icon from main menu====");
			elementClick(addToCartOption);
		} catch (Exception e) {
			log.info(e);
		}
		return new ShoppingCartPage(driver);

	}

}
