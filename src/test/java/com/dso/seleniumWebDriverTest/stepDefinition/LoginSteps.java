package com.dso.seleniumWebDriverTest.stepDefinition;
import com.dso.seleniumWebDriverTest.enviroment.EnviromentConstantsNames;
import com.dso.seleniumWebDriverTest.utilsType.constans.KeyElementsLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.dso.seleniumWebDriverTest.enviroment.Hooks;
import com.dso.seleniumWebDriverTest.utilsType.FileWriter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import static com.dso.seleniumWebDriverTest.enviroment.Hooks.getWebDriver;


public class LoginSteps {

    /**
     * The Logger
     */
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoginSteps.class);


    
    @Given("^User is on Home Page$")
    public void user_is_on_Home_Page() throws Throwable {
    	LOGGER.debug("User is on Home Page - " + FileWriter.getProps().getProperty(EnviromentConstantsNames.MAIN_URL));
        getWebDriver().get(FileWriter.getProps().getProperty(EnviromentConstantsNames.MAIN_URL));
    }

    @When("^User Navigate to LogIn Page$")
    public void user_Navigate_to_LogIn_Page() throws Throwable {
    	Hooks.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id(KeyElementsLoginPage.ACCESS_TO_LOGIN.getIdElement()))).click();
    }
}

