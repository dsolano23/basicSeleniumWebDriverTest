package com.dso.seleniumWebDriverTest.stepDefinition;
/**
 * Created by David Solano.
 * 2018-07-24
 */

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dso.seleniumWebDriverTest.exception.NotFoundResourceException;
import com.dso.seleniumWebDriverTest.utilsType.CheckMethods;
import com.dso.seleniumWebDriverTest.utilsType.FileWriter;
import com.dso.seleniumWebDriverTest.utilsType.PropertyConstantsNames;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;


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

	   @After(order=1)
	    public void renerateHTMLReport() throws InterruptedException {
	        LOGGER.debug("Wait 5 seconds init");
	        TimeUnit.SECONDS.sleep(5);
	        LOGGER.debug("Wait 5 seconds end");
			File reportOutputDirectory = new File("target");
			List<String> jsonFiles = new ArrayList<>();
			jsonFiles.add("target/cucumber-usage.json");
			//jsonFiles.add("cucumber-report-2.json");

			String buildNumber = "1";
			String projectName = "cucumberProject";
			boolean runWithJenkins = false;
			boolean parallelTesting = false;

			Configuration configuration = new Configuration(reportOutputDirectory, projectName);
			// optional configuration
			
			configuration.setParallelTesting(parallelTesting);
			configuration.setRunWithJenkins(runWithJenkins);
			configuration.setBuildNumber(buildNumber);
			// addidtional metadata presented on main page
			configuration.addClassifications("Platform", "Windows");
			configuration.addClassifications("Browser", "Firefox");
			configuration.addClassifications("Branch", "release/1.0");

			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
			Reportable result = reportBuilder.generateReports();
			// and here validate 'result' to decide what to do
			// if report has failed features, undefined steps etc
	    	
	    	
	    }
    /**
     * Public access to WebDriver object
     * @return
     */
    public static WebDriver getWebDriver() throws NotFoundResourceException {

        if (driver == null){ // Initialize singleton
            if (FileWriter.getProps().getProperty(PropertyConstantsNames.BROWSER).equals(PropertyConstantsNames.FIREFOX)) {
            	if (FileWriter.getProps().getProperty(PropertyConstantsNames.OPERATING_SYSTEM).equals(PropertyConstantsNames.WIN_64)) {
            		System.setProperty("webdriver.gecko.driver", "..\\\\/basicSeleniumWebDriverTest/src/test/resources/geckodriver/geckodriverWin64.exe");
            	}else if (FileWriter.getProps().getProperty(PropertyConstantsNames.OPERATING_SYSTEM).equals(PropertyConstantsNames.LINUX_64)) {
            		System.setProperty("webdriver.gecko.driver", "..\\\\/basicSeleniumWebDriverTest/src/test/resources/geckodriver/geckodriverLinux64");
            	}
            	    
                driver = new FirefoxDriver();
            } else if (FileWriter.getProps().getProperty(PropertyConstantsNames.BROWSER).equals(PropertyConstantsNames.CHROME)) {
            	if (FileWriter.getProps().getProperty(PropertyConstantsNames.OPERATING_SYSTEM).equals(PropertyConstantsNames.WIN_64)) {
            		System.setProperty("webdriver.chrome.driver", "..\\\\\\\\/basicSeleniumWebDriverTest/src/test/resources/browserDrivers/chromedriver/chromedriverWin32.exe");
            	}
                driver = new ChromeDriver();
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