package com.dso.seleniumWebDriverTest.cucumberRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.dso.seleniumWebDriverTest.enviroment.BrowserCodes;
import com.dso.seleniumWebDriverTest.enviroment.Enviroment;
import com.dso.seleniumWebDriverTest.enviroment.Hooks;
import com.dso.seleniumWebDriverTest.exception.NotFoundResourceException;
import com.dso.seleniumWebDriverTest.utilsType.FileWriter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

@RunWith(Cucumber.class)


@CucumberOptions(
				plugin = { 
						"pretty",
						"json:target/cucumber.json", 
						//"html:target/site/cucumber-pretty",
						},
		
				//Used only if you want run a specific feature by tag : @login, @search or @filter
				//tags = {"@<tagName>"},
				
				features = {"src/test/resources/features"},
				glue = {"com.dso.seleniumWebDriverTest.stepDefinition"}
				)

public class TestRunner {
	
	private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(TestRunner.class);
	
	
		@BeforeClass
		public static void startEnviroment() throws Exception {
			if (FileWriter.getProps().getProperty(BrowserCodes.BROWSER) != null && FileWriter.getProps().getProperty(BrowserCodes.BROWSER) != null) {
				String browser = FileWriter.getProps().getProperty(BrowserCodes.BROWSER);
				String mainURL = "";
		    	Enviroment enviroment = new Enviroment(browser, mainURL );
		    	    	
		    	LOGGER.debug("Generating masterthought HTML reports .......");
		    	Hooks.setEnviroment(enviroment);
		    	
		        LOGGER.info("*************************************");

		        LOGGER.debug("Called openBrowser(beforeScenario)");

		        // Clean all residual configuration test before each execution
		        Hooks.getWebDriver().manage().window().maximize();
		        Hooks.getWebDriver().manage().deleteAllCookies();
		        Hooks.getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		        
			}

		}
		
   
		@AfterClass	
	    public static void renerateHTMLReport() throws InterruptedException, NotFoundResourceException {
			
	        LOGGER.debug("Generating masterthought HTML reports .......");
	        
			File reportOutputDirectory = new File("target");
			List<String> jsonFiles = new ArrayList<>();
			jsonFiles.add("target/cucumber.json");

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
			LOGGER.debug("Generated Masterthought HTML reports");
			Hooks.getWebDriver().close();
	    }
    }


