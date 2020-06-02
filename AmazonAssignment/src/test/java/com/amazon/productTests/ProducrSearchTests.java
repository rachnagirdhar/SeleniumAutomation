package com.amazon.productTests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.amazon.base.AmazonBaseClass;
import com.amazon.pages.HomePage;
import com.amazon.pages.ProductSearchResultPage;
import com.aventstack.extentreports.Status;
//Enable the below line if you want to create report while debugging
//@Listeners(com.amazon.test.utils.AmazonListners.class)

public class ProducrSearchTests extends AmazonBaseClass {

	public static WebDriver driver;
	HomePage hm;
	ProductSearchResultPage pg;

	@BeforeClass

	public void init() {

		driver = initializeDriver();

		log.info("====Initializing the Home page object====");
		hm = new HomePage(driver);

		hm.navigateToURL(driver);

	}

	@Test(groups = { "Smoke", "Regression" })
	public void multiSearchResultVerification() throws InterruptedException {

		test.log(Status.INFO, "verifying Search Result page for multiple results");
		test.log(Status.INFO, "verifying home Page title first");

		log.info("=====Actual homepage title is " + hm.homePageTitle() + "====");
		Assert.assertTrue(hm.homePageTitle().contains("Amazon.in"));

		hm.clickOnCategoryMenu();

		hm.selectOption();

		hm.searchValidText();

		hm.clickGoIcon();

		test.log(Status.INFO, "Verifying that Search result page has been opened");

		pg = hm.navigateToSearchResult();

		test.log(Status.INFO, "Verifying the SearchResult page Title");
		log.info("=====Actual search page title is " + pg.searchResultPageTitle() + "====");

		Assert.assertTrue(pg.searchResultPageTitle().contains("Amazon.in"));

	}

	@Test(groups = { "Regression" })
	public void noResultVerification() {

		test.log(Status.INFO, "Verifying Search noSearchResult feature");
		hm.clickOnCategoryMenu();

		hm.selectOption();

		hm.searchInValidText();

		hm.clickGoIcon();
		pg = hm.navigateToSearchResult();

		test.log(Status.INFO, "Verifying that Search result page has been opened with no results");

		Assert.assertTrue(pg.noSearchresult().contains("No results for"));

	}

	@Test(groups = { "Regression" })
	public void goButtonVerificationWithEmptySearchText() {

		hm.clickOnCategoryMenu();

		hm.selectOption();

		test.log(Status.INFO, "Click on Go icon without adding any search text");

		hm.clickGoIcon();

		Assert.assertTrue(hm.homePageTitle().contains("Amazon.in"));

	}

	@Test(groups = { "Regression" })

	public void goButtonVerificationWithoutCategorySelection() {

		test.log(Status.INFO, "Verifying Search result without selecting Category");
		log.info("====Verifying Search result without selecting Category====");

		hm.searchValidText();

		hm.clickGoIcon();

		test.log(Status.INFO, "Verifying that Search result page has been opened");

		pg = hm.navigateToSearchResult();

		Assert.assertTrue(pg.searchResultPageTitle().contains("Amazon.in"));
	}

	@AfterClass
	public void afterTest() {
		hm.closeBrowser(driver);
	}

}
