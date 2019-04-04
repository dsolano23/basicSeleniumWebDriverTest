package com.dso.seleniumWebDriverTest.enviroment;

public class Environment {


	private final String browser;
	private final String mainURL;


	private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Environment.class);

	public Environment(String browser, String mainURL) {
		this.browser = browser;
		this.mainURL = mainURL;
	}

	public String getBrowser() {
		return browser;
	}
	public String getMainURL() {
		return mainURL;
	}


}