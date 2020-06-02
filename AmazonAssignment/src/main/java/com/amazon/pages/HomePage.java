package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.Util.Constants;
import com.amazon.Util.WebFunctions;
import com.aventstack.extentreports.Status;

public class HomePage extends WebFunctions {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "searchDropdownBox")
	WebElement selectCategoryDropdown;

	@FindBy(id = "twotabsearchtextbox")
	WebElement searchBox;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement searchIcon;

	public void selectOption()

	{
		log.info("====Selecting option from category dropdown====");

		try {
			selectValueFromDropDown(selectCategoryDropdown, Constants.searchOption);
			test.log(Status.INFO, "Option " + Constants.searchOption + " is selected from the dropdown");

		} catch (NullPointerException e) {
			log.error("Search Option didn't fetch from TestData File");
		}

		catch (Exception e) {
			log.error(e);

		}

	}

	public ProductSearchResultPage navigateToSearchResult() {

		try {

			log.info("====Navigate to Search Result Page====");

			elementClick(searchIcon);

			test.log(Status.INFO, "Navigated to Search result");
		}

		catch (Exception e) {
			log.error(e);
		}
		return new ProductSearchResultPage(driver);

	}

	public void searchValidText() {

		try {
			log.info("====entering keyword to search into the Search text box====");

			enterText(Constants.searchText, searchBox);

			test.log(Status.INFO, "Searched results for valid keyword " + Constants.searchText);
		}

		catch (Exception e) {
			log.error(e);
		}
	}

	public void searchInValidText() {

		try {
			log.info("====entering invalid search keyword to Search text box====");

			enterText(Constants.noSearchText, searchBox);
			test.log(Status.INFO, "Searched results for invalid " + Constants.noSearchText);
		}

		catch (Exception e) {
			log.error(e);
		}
	}

	public void clickGoIcon() {

		try {

			log.info("====Clicking on Go icon====");

			elementClick(searchIcon);

			test.log(Status.INFO, "Clicked on Go");
		}

		catch (Exception e) {
			log.error(e);
		}
	}

	public void clickOnCategoryMenu() {

		try {

			log.info("====Clicking on category menu====");

			waitUntilElementIsEnabled(selectCategoryDropdown);
			elementClick(selectCategoryDropdown);

			test.log(Status.INFO, "Category Menu clicked");
		} catch (Exception e) {
			log.error(e);
		}

	}

	public String homePageTitle() {
		String homePageTitle = null;

		try {

			log.info("=====Getting Home page title====");
			homePageTitle = getPageTitle(driver);
		}

		catch (Exception e) {
			log.error(e);
		}
		return homePageTitle;

	}

}
