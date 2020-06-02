package com.amazon.productTests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.amazon.Util.Constants;
import com.amazon.base.AmazonBaseClass;
import com.amazon.pages.HomePage;
import com.amazon.pages.ProductDetailPage;
import com.amazon.pages.ProductSearchResultPage;
import com.amazon.pages.ShoppingCartPage;
import com.aventstack.extentreports.Status;

//Enable the below line if you want to create report while debugging
//@Listeners(com.amazon.test.utils.AmazonListners.class)

public class AddProductToCartTests extends AmazonBaseClass {

	public static WebDriver driver;
	HomePage hm;
	ProductSearchResultPage pg;
	ProductDetailPage detailPage;
	ShoppingCartPage cartPage;
	String firstSearchedProductTitle;

	@BeforeClass

	public void init() {

		driver = initializeDriver();
		hm = new HomePage(driver);
		hm.navigateToURL(driver);

	}

	@Test(groups = { "Regression" })
	public void firstItemOfSearchResultVerification() {
		test.log(Status.INFO, "verifying Search Result page for multiple results");
		test.log(Status.INFO, "verifying home Page title first");
		Assert.assertTrue(hm.homePageTitle().contains("Amazon.in"));

		hm.clickOnCategoryMenu();

		hm.selectOption();

		hm.searchValidText();

		hm.clickGoIcon();

		test.log(Status.INFO, "Verifying that Search result page has been opened");

		pg = hm.navigateToSearchResult();

		test.log(Status.INFO, "Verifying the SearchResult page Title");

		firstSearchedProductTitle = pg.firstProductTitle();

		Assert.assertTrue(firstSearchedProductTitle.contains(Constants.searchText.toLowerCase()));

	}

	@Test(dependsOnMethods = { "firstItemOfSearchResultVerification" }, groups = { "Regression" })

	public void addToCartProductVerification() {

		detailPage = pg.clickFirstSearchedProduct();
		detailPage.addToCart();

		Assert.assertEquals(detailPage.addedItemCartText(), "Added to Cart");
		cartPage = detailPage.navigateToShoppoingCart();

		Assert.assertEquals(cartPage.productTitle().toLowerCase(), firstSearchedProductTitle);

	}

	@AfterClass
	public void afterTest() {
		hm.closeBrowser(driver);
	}

}
