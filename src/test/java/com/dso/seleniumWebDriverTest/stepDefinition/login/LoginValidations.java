package com.dso.seleniumWebDriverTest.stepDefinition.login;

import com.dso.seleniumWebDriverTest.enviroment.Hooks;
import com.dso.seleniumWebDriverTest.pom.LoginPagePOM;
import com.dso.seleniumWebDriverTest.utilsType.WebSelector;
import com.dso.seleniumWebDriverTest.utilsType.constans.front.common.ElementAttributeKeys;
import com.dso.seleniumWebDriverTest.utilsType.constans.front.common.WebComponentKeys;
import com.dso.seleniumWebDriverTest.utilsType.constans.front.specific.LoginFormKeys;
import cucumber.api.java.en.Then;
import org.junit.Assert;


public class LoginValidations {

    /**
     * The Logger
     */
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoginValidations.class);
    LoginPagePOM loginPage;


    //TODO
/*
    @Then("^Message displayed Login Successfully for the user (.+)$")
    public void message_displayed_login_successfully_for_the_user(String userName) throws Throwable {
        loginPage = new LoginPagePOM(Hooks.getWebDriver());

        String resultReceived = loginPage.getLogInSuccessfullyMessage().toLowerCase();
        String resultExpected = userName.toLowerCase();
        String assertTrace = " Bad Message LogInSuccessfully  Received: " + resultReceived +" & Expected: " + resultExpected;
        Assert.assertEquals(assertTrace, resultExpected, resultReceived);
    }*/

    @Then("^Message displayed Login unsuccessfully$")
    public void message_displayed_login_unsuccessfully() throws Throwable {
        loginPage = new LoginPagePOM(Hooks.getWebDriver());

        String assertTrace = " Message displayed Login unsuccessfully: ";
        Assert.assertEquals(assertTrace,true, loginPage.logInUnsuccessfullyIsDisplayed());

        String resultReceived = loginPage.getLogInUnsuccessfullyMessage();
        String keyWebComponent = WebComponentKeys.loginForm.name();
        String keyWebElement = LoginFormKeys.UnsuccessfullyLabel.name();
        String attribute = ElementAttributeKeys.labelText.name();
        String resultExpected = WebSelector.getElementAttribute(keyWebComponent, keyWebElement, attribute );
        assertTrace = " Bad Message LogInUnsuccessfully  Received: " + resultReceived +" & Expected: " + resultExpected;
        Assert.assertEquals(assertTrace, resultExpected, resultReceived);

    }

}

