package com.amazon.Util;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.base.AmazonBaseClass;

public class WebFunctions extends AmazonBaseClass {

	WebDriver driver;

	public void navigateToURL(WebDriver driver)

	{

		log.info("====Opening the URL====");
		try {

			driver.get(Constants.url);
		} catch (Exception e) {
			log.error(e);

		}

	}

	public void elementClick(WebElement element) {

		try {

			log.info("====If element is present click on that Element====");

			if (element.isDisplayed() == true) {

				element.click();
			}

			else

			{
				log.error("Element is not available to click");
			}
		} catch (Exception e)

		{
			log.error(e);
		}

	}

	public void selectValueFromDropDown(WebElement category, String optionValue) {

		log.info("=====select the menu option using select by index====");
		try {
			if (category.isDisplayed()) {

				Select select = new Select(category);

				List<WebElement> l = select.getOptions();
				for (int i = 0; i < l.size(); i++) {
					int k = 0;
					if (l.get(i).getAttribute("value").toLowerCase().contains(optionValue.toLowerCase())) {

						select.selectByIndex(i);

						break;
					}

					else {
						k++;

					}

					if (k == l.size()) {
						log.error(optionValue + " option is not available in the category dropdown");

					}
				}
			} else {
				log.error("Category menu is not present");
			}

		} catch (Exception e) {
			log.error(e);
		}

	}

	public void enterText(String text, WebElement element) {

		log.info("====if text box element is present enter the text in textbox=====");

		try {

			if (element.isDisplayed() == true) {
				element.clear();
				element.sendKeys(text);
			} else

			{
				log.error("====Element is not available to Enter text====");
			}
		} catch (Exception e) {
			log.info(e);
		}
	}

	public String getElementTitle(WebElement element) {

		log.info("=====fetching the title of the element====");

		try {
			if (element.isDisplayed() == false) {

				log.error("Element is not available to get the title");
			}
		} catch (Exception e) {
			log.info(e);
		}
		return element.getText();
	}

	public String getPageTitle(WebDriver driver) {

		log.info("====Getting the page title====");
		String pageTitle = null;

		try {
			pageTitle = driver.getTitle();
		} catch (Exception e) {
			log.info(e);
		}
		return pageTitle;

	}

	public void closeBrowser(WebDriver driver) {
		try {
			log.info("====Closing the browser====");
			driver.quit();
		} catch (Exception e) {
			log.info(e);
		}

	}

	public void moveToNextTab(WebDriver driver) {

		log.info("====Switching the driver to next child window====");

		try {
			Set<String> handler = driver.getWindowHandles();

			Iterator<String> it = handler.iterator();

			String parent = it.next();
			String child = null;

			while (it.hasNext()) {
				child = it.next();
			}

			driver.switchTo().window(child);

		} catch (Exception e) {
			log.info(e);
		}
	}

	public void waitUntilElementIsEnabled(WebElement element) {

		try {

			log.info("====waiting for the element to get clickable====");

			WebDriverWait wait = new WebDriverWait(driver, 10);

			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			log.info(e);
		}
	}
}
