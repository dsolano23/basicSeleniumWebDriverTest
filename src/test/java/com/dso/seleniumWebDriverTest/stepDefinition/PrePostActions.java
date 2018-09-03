package com.dso.seleniumWebDriverTest.stepDefinition;

import com.dso.seleniumWebDriverTest.enviroment.Hooks;
import com.dso.seleniumWebDriverTest.exception.NotFoundResourceException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


public class PrePostActions {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PrePostActions.class);

    @Before
    public void setUp(Scenario scenario) throws MalformedURLException, NotFoundResourceException {
        LOGGER.info("*************************************");
        LOGGER.info(String.format(" Started Test %s ", scenario.getName()));
        Hooks.setScenario(scenario);
        LOGGER.debug("Called openBrowser(beforeScenario)");

        // Clean all residual configuration test before each execution
        Hooks.getWebDriver().manage().window().maximize();
        Hooks.getWebDriver().manage().deleteAllCookies();
        Hooks.getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void tearDown(Scenario scenario) throws NotFoundResourceException {

        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is: " + Hooks.getWebDriver().getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) Hooks.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                // Controlled exception does not stop the test
                LOGGER.error(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
        destroyDriver();
    }

    /**
     * Destroy driver browser for each test execution
     */
    public void destroyDriver() throws NotFoundResourceException {

        Hooks.getWebDriver().quit();
        //Hooks.getWebDriver().close();
        Hooks.setWebDriver(null);
        Hooks.setWebDriverWait(null);
        LOGGER.info("Finalized  Execution Test");
        LOGGER.info("********************************");

    }
}
