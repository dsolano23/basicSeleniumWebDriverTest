package com.dso.seleniumWebDriverTest.stepDefinition;
/**
 * Created by David Solano.
 * 2018-07-24
 */

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dso.seleniumWebDriverTest.exception.NotFoundResourceException;
import com.dso.seleniumWebDriverTest.utilsType.CheckMethods;
import com.dso.seleniumWebDriverTest.utilsType.FileWriter;
import com.dso.seleniumWebDriverTest.utilsType.PropertyConstantsNames;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
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


    @Before
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    public void setUp(Scenario scenario) throws MalformedURLException, NotFoundResourceException {
        LOGGER.info("*************************************");
        LOGGER.info(String.format(" Started Test %s ", scenario.getName()));
        LOGGER.debug("Called openBrowser(beforeScenario)");

        // Clean all residual configuration test before each execution
        getWebDriver().manage().window().maximize();
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After(order=0)
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void tearDown(Scenario scenario) throws NotFoundResourceException {

        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is: " + getWebDriver().getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                // Controlled exception does not stop the test
                LOGGER.error(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
      		
    		destroyDriver();
    }


    /**
     * Public access to WebDriver object
     * @return
     */
    public static WebDriver getWebDriver() throws NotFoundResourceException {

        if (driver == null){ // Initialize singleton
            if (FileWriter.getProps().getProperty(PropertyConstantsNames.BROWSER).equals(PropertyConstantsNames.FIREFOX)) {
            	WebDriverManager.firefoxdriver().setup();	   
                driver = new FirefoxDriver();
            } else if (FileWriter.getProps().getProperty(PropertyConstantsNames.BROWSER).equals(PropertyConstantsNames.CHROME)) {
            	WebDriverManager.chromedriver().setup();	
                driver = new ChromeDriver();
            }else if (FileWriter.getProps().getProperty(PropertyConstantsNames.BROWSER).equals(PropertyConstantsNames.EDGE)) {
            	WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }else if (FileWriter.getProps().getProperty(PropertyConstantsNames.BROWSER).equals(PropertyConstantsNames.INTERNET_EXPLORER)) {
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


    /**
     * Destroy driver browser for each test execution
     */
    public void destroyDriver() {
        driver.quit();
        driver = null;
        wait = null;
        LOGGER.info("Finalized  Execution Test");
        LOGGER.info("********************************");

    }
}