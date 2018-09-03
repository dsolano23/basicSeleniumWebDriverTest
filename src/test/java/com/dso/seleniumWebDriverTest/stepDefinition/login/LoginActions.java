package com.dso.seleniumWebDriverTest.stepDefinition.login;

import com.dso.seleniumWebDriverTest.enviroment.EnvironmentConstantsNames;
import com.dso.seleniumWebDriverTest.enviroment.Hooks;
import com.dso.seleniumWebDriverTest.pom.LoginPagePOM;
import com.dso.seleniumWebDriverTest.utilsType.EnvPropertiesManagement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.concurrent.TimeUnit;


public class LoginActions {

    /**
     * The Logger
     */
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoginActions.class);
    LoginPagePOM loginPage;


    @Given("^User Navigate to Home Page$")
    public void user_navigate_to_Home_Page() throws Throwable {
        LOGGER.debug("User is on Home Page - " + EnvPropertiesManagement.getEnvProps().getProperty(EnvironmentConstantsNames.MAIN_URL));
        Hooks.getWebDriver().manage().deleteAllCookies();
        Hooks.getWebDriver().navigate().to(EnvPropertiesManagement.getEnvProps().getProperty(EnvironmentConstantsNames.MAIN_URL));
        Hooks.getWebDriver().navigate().refresh();
    }

    @When("^User Navigate to LogIn Page$")
    public void user_Navigate_to_LogIn_Page() throws Throwable {
        loginPage = new LoginPagePOM(Hooks.getWebDriver());
        loginPage.accessToLoginPage();

    }

    @Given("^I insert the user (.+)$")
    public void i_insert_the_user(String userName) throws Throwable {
        loginPage = new LoginPagePOM(Hooks.getWebDriver());
        loginPage.setUserName(userName);
        Hooks.getScenario().write("User Login values - userName: " + userName );
    }

    @When("^I insert the password (.+)$")
    public void i_insert_the_password(String password) throws Throwable {
        loginPage = new LoginPagePOM(Hooks.getWebDriver());
        loginPage.setPassword(password);
        Hooks.getScenario().write("User Login values -  password: " + password);
    }

    @And("^I go to insert pwd$")
    public void i_go_to_insert_pwd() throws Throwable {
        loginPage = new LoginPagePOM(Hooks.getWebDriver());
        loginPage.goToInsertPWD();

    }

    @And("^I do summit$")
    public void i_do_summit() throws Throwable {
        loginPage = new LoginPagePOM(Hooks.getWebDriver());
        loginPage.commit();
    }

}

