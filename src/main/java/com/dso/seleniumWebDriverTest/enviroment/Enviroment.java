package com.dso.seleniumWebDriverTest.enviroment;

public class Enviroment {
	
	private final String browser;
	private final String mainURL;
	
	public Enviroment(String browser, String mainURL) {
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
