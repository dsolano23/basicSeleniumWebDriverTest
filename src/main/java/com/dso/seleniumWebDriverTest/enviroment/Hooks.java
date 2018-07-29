package com.dso.seleniumWebDriverTest.enviroment;
/**
 * Created by David Solano.
 * 2018-07-24
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dso.seleniumWebDriverTest.exception.NotFoundResourceException;
import com.dso.seleniumWebDriverTest.utilsType.CheckMethods;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Hooks {

    /**
     * The Logger
     */
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Hooks.class);

    /**
     * Singleton instance strategy
     */
    private static WebDriverWait wait;
    /**
     * Singleton instance strategy
     */
    private static WebDriver driver;
    /**
     * Singleton instance strategy
     */
    private static CheckMethods checkMethods;
    
    
    private static Enviroment enviroment;

    public static Enviroment getEnviroment() {
		return enviroment;
	}

	public static void setEnviroment (Enviroment enviroment) {
		Hooks.enviroment = enviroment;
    }
    
//    public static Enviroment getEnviroment() {
//    	return enviroment;
//    }

    /**
     * Public access to WebDriver object
     * @return
     */
    public static WebDriver getWebDriver( ) throws NotFoundResourceException {
    	
  	if (driver == null){ // Initialize singleton
            if (enviroment.getBrowser().equals(BrowserCodes.FIREFOX)) {
            	WebDriverManager.firefoxdriver().setup();	   
                driver = new FirefoxDriver();
            } else if (enviroment.getBrowser().equals(BrowserCodes.CHROME)) {
            	WebDriverManager.chromedriver().setup();	
                driver = new ChromeDriver();
            }else if (enviroment.getBrowser().equals(BrowserCodes.EDGE)) {
            	WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }else if (enviroment.getBrowser().equals(BrowserCodes.INTERNET_EXPLORER)) {
            	WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }else {
                throw new NotFoundResourceException("Not found a valid browser type at properties file. ");
            }
        }
        return driver;
    }

    /**
     * Public access to webDriverWait object
     * @return
     */
    public static WebDriverWait getWebDriverWait() throws NotFoundResourceException {
        if(wait== null){ // Initialize singleton
            wait = new WebDriverWait(driver, 360);
        }
        return wait;
    }

    /**
     * Public access to CheckMethods Object
     * @return
     */
    public static CheckMethods getCheckMethods(){
        if(checkMethods== null){
            checkMethods = new CheckMethods();
        }
        return checkMethods;
    }
}
