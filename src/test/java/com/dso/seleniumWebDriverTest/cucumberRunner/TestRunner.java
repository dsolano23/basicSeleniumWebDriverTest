package com.dso.seleniumWebDriverTest.cucumberRunner;

/**
 * Created by David Solano.
 * 2018-07-24
 */
import org.junit.runner.RunWith;

import com.dso.seleniumWebDriverTest.stepDefinition.Hooks;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;

@RunWith(ExtendedCucumber.class)
//@RunWith(Cucumber.class)

@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
jsonUsageReport = "target/cucumber-usage.json",
usageReport = true,
outputFolder = "target")
@CucumberOptions(
		plugin = {"usage:target/cucumber-usage.json" 
				//"pretty",
				//"json:target/cucumber.json", 
				//"html:target/site/cucumber-pretty",
				},
		
		//
       // "html:target/cucumber-html-report",
        //"json:target/cucumber.json"},
       // "pretty:target/cucumber-pretty.txt",
       // "junit:target/cucumber-results.xml"},
        // Used only if you want run a specific feature by tag : @login, @search or @filter
        //tags = {"@<tagName>"},
       // features = {"src/test/resources/features"},
        //glue = {"stepDefinition", "src/test/java/com/dso/seleniumWebDriverTest/stepDefinition"})
features = {"src/test/resources/features"},
glue = {"com.dso.seleniumWebDriverTest.stepDefinition"})

public class TestRunner {
	private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Hooks.class);

	
    }


