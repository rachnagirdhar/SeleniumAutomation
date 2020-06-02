package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.Util.WebFunctions;
import com.aventstack.extentreports.Status;

public class ProductSearchResultPage extends WebFunctions {

	WebDriver driver;

	public ProductSearchResultPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".a-link-normal.a-text-normal")

	WebElement firstProductLink;

	@FindBy(xpath = "div[@data-index='0']")

	WebElement firstSearchResult;

	@FindBy(xpath = "//div[@data-index='0'] [//span[@class='a-size-medium a-color-base a-text-normal']")
	WebElement firstProdTitle;

	@FindBy(xpath = " //span[@class='a-size-medium a-color-base']")
	List<WebElement> noResultFound;

	@FindBy(css = ".a-size-medium.a-color-base.a-text-normal")
	List<WebElement> resultTitles;

	public String firstProductTitle() {
		String actualTitle = null;
		log.info("==========Getting title of first searched product=========");
		try {
			actualTitle = getElementTitle(resultTitles.get(0)).toLowerCase();
		} catch (Exception e) {
			log.info(e);
		}

		return actualTitle;
	}

	public ProductDetailPage clickFirstSearchedProduct() {

		try {
			log.info("====Clicking on first searched item====");

			elementClick(resultTitles.get(0));

			log.info("====Moving to the new opened tab ====");

			moveToNextTab(driver);
		} catch (Exception e) {
			log.info(e);
		}

		return new ProductDetailPage(driver);

	}

	public String searchResultPageTitle() {
		String searchResultPageTitle = null;

		try {

			searchResultPageTitle = getPageTitle(driver);
			log.info("=====Fetching the SearchResult page Title====");

		}

		catch (Exception e) {
			log.info(e);

		}
		return searchResultPageTitle;
	}

	public String noSearchresult() {

		String resultTitle = null;
		log.info("====Fetching No search results output title====");
		test.log(Status.INFO, "Verifying noSearchResult");

		try {

			String text1 = noResultFound.get(0).getText();

			String text2 = noResultFound.get(1).getText();

			log.info("====getting tile for no search result====");

			resultTitle = text1.concat(" " + text2);

		} catch (Exception e) {
			log.info(e);
		}

		return resultTitle;

	}

}
