package com.amazon.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.amazon.Util.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class AmazonBaseClass {

	WebDriver driver;

	public static ExtentTest test;

	public static ExtentReports extent;

	public String browser;

	public static Logger log = LogManager.getLogger(AmazonBaseClass.class);

	public WebDriver initializeDriver() {

		log.info("====Initializing browser====");

		try {

			String path = System.getProperty("user.dir") + "/Resources/browsers/";

			if (Constants.browser.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", path + "/chromedriver");

				driver = new ChromeDriver();

			} else if (Constants.browser.equalsIgnoreCase("firefox"))

			{
				System.setProperty("webdriver.gecko.driver", path + "/geckodriver");

				driver = new FirefoxDriver();

			} else if (Constants.browser.equalsIgnoreCase("ie")) {

				System.setProperty("webdriver.ie.driver", path + "/IeDriver");

				driver = new InternetExplorerDriver();

			}
		} catch (Exception e) {
			log.error(e);

		}
		driver.manage().timeouts().implicitlyWait(Constants.waitTime, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		return driver;

	}

	public static String readPropertyFile(String fileName, String key) {

		log.info("=====Reading value from Property file====");

		String value = null;

		Properties prop = new Properties();

		String path = System.getProperty("user.dir") + "/Resources/Data/" + fileName + ".properties";
		try {
			FileInputStream fis = new FileInputStream(path);

			prop.load(fis);

			value = prop.getProperty(key);

		} catch (Exception e) {
			log.error(e);

		}

		return value;
	}

	@SuppressWarnings("unlikely-arg-type")
	public static String readExcelFile(String fileName, String key, String sheetname)

	{

		log.info("====Reading value from Excel====");
		String value = null;
		try {
			String filePath = System.getProperty("user.dir") + "/Resources/Data/" + fileName + ".xlsx";
			FileInputStream fis = new FileInputStream(filePath);

			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			int numOfSheets = workbook.getNumberOfSheets();

			for (int i = 0; i < numOfSheets; i++) {
				if (workbook.getSheetAt(i).getSheetName().equals(sheetname)) {
					XSSFSheet sheet = workbook.getSheetAt(i);

					int rowCount = sheet.getLastRowNum() + 1;

					Iterator<Row> row = sheet.iterator();

					Row firstRow = row.next();

					int colCount = firstRow.getPhysicalNumberOfCells();

					while (row.hasNext()) {

						for (int j = 0; j < colCount; j++) {

							if (firstRow.getCell(j).getStringCellValue().equalsIgnoreCase(key)) {
								value = (row.next().getCell(j).getStringCellValue());
							}
						}

					}

				}
			}

		}

		catch (Exception e) {
			log.error(e);
		}
		return value;

	}

	public String takeScreenShot(String testCaseName, WebDriver driver) throws Exception {

		log.info("====Taking ScreenShot====");
		String destination = null;

		// try {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		destination = System.getProperty("user.dir") + "/Reports/ScreenShots/" + testCaseName + ".png";

		FileUtils.copyFile(source, new File(destination));
		// } catch (Exception e) {
		// log.error(e);
		// }
		return destination;
	}

	public static ExtentReports amazonReports() {
		ExtentReports extent = new ExtentReports();

		log.info("====Generating Extent report====");

		String path = System.getProperty("user.dir") + "/Reports/ExtentReports/amazon.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		try {
			reporter.config().setDocumentTitle("Amazon Test Report");
			reporter.config().setReportName("Amazon Automation Report");

			extent.attachReporter(reporter);
			extent.setSystemInfo("Author", "Rachna");
		} catch (Exception e) {
			log.error(e);
		}

		return extent;
	}

}
