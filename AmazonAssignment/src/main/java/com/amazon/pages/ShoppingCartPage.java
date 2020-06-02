package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.Util.WebFunctions;

public class ShoppingCartPage extends WebFunctions {

	WebDriver driver;

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@data-item-count='1'] //span[@class='a-size-medium sc-product-title a-text-bold']")

	WebElement productTitleElement;

	public String productTitle() {
		String firstDisplayedProductTitle = null;

		try {
			log.info("====Getting the title of top item in the shopping cart====");
			firstDisplayedProductTitle = getElementTitle(productTitleElement);
		} catch (Exception e) {
			log.info(e);
		}
		return firstDisplayedProductTitle;
	}

}
