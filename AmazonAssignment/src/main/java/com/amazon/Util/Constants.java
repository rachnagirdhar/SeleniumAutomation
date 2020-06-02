package com.amazon.Util;

import com.amazon.base.AmazonBaseClass;

public class Constants extends AmazonBaseClass {

	public final static String browser = readPropertyFile("Config", "browser");

	public static final String url = readPropertyFile("Config", "url");
	public final static long waitTime = Long.parseLong(readPropertyFile("Config", "waitTime"));

	public final static String searchOption = readExcelFile("Testdata", "Search option", "TestCaseData");
	public final static String searchText = readExcelFile("Testdata", "Search text", "TestCaseData");
	public final static String noSearchText = readExcelFile("Testdata", "No Result text", "TestCaseData");

}
