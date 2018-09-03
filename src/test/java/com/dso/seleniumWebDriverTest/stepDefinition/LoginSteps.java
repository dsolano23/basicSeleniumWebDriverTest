package com.dso.seleniumWebDriverTest.stepDefinition;
import com.dso.seleniumWebDriverTest.beans.Credentials;
import com.dso.seleniumWebDriverTest.enviroment.EnviromentConstantsNames;
import com.dso.seleniumWebDriverTest.pom.LoginPagePOM;
import com.dso.seleniumWebDriverTest.utilsType.constans.KeyElementsLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.dso.seleniumWebDriverTest.enviroment.Hooks;
import com.dso.seleniumWebDriverTest.utilsType.FileWriter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.List;


public class LoginSteps {

    /**
     * The Logger
     */
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoginSteps.class);
    LoginPagePOM loginPage;

    @Given("^User is on Home Page$")
    public void user_is_on_Home_Page() throws Throwable {
    	LOGGER.debug("User is on Home Page - " + FileWriter.getProps().getProperty(EnviromentConstantsNames.MAIN_URL));
        Hooks.getWebDriver().get(FileWriter.getProps().getProperty(EnviromentConstantsNames.MAIN_URL));
    }

    @When("^User Navigate to LogIn Page$")
    public void user_Navigate_to_LogIn_Page() throws Throwable {
        loginPage = new LoginPagePOM(Hooks.getWebDriver());
        loginPage.accessToIt();
    }

    @When("^User enters UserName and Password$")
    public void user_enters_UserName_and_Password(List<Credentials> usercredentials) throws Throwable {
        for (Credentials credentials : usercredentials) {
            //LoginPagePOM loginPage = new LoginPagePOM(Hooks.getWebDriver());
            loginPage.setUserName(credentials.getUsername());

        }
    }
}

