package com.dso.seleniumWebDriverTest.pom;

import com.dso.seleniumWebDriverTest.enviroment.Hooks;
import com.dso.seleniumWebDriverTest.exception.NotFoundResourceException;
import com.dso.seleniumWebDriverTest.utilsType.WebSelector;
import com.dso.seleniumWebDriverTest.utilsType.constans.LoginPageKeys;
import com.dso.seleniumWebDriverTest.utilsType.constans.WebComponentKeys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPagePOM {

    private String keyWebComponent =  WebComponentKeys.loginPage.name();
    private String keyWebElement = "";
    private By elementId = null;
    private WebElement accessToLoginPage;
    private WebElement insertUserName;

    private WebDriver currentDriver;

    public LoginPagePOM(WebDriver currentDriver) throws NotFoundResourceException {
        this.currentDriver = currentDriver;

        keyWebElement = LoginPageKeys.AccessToLoginPage.name();
        elementId = WebSelector.getElementAttribute(keyWebComponent, keyWebElement);
        accessToLoginPage =  Hooks.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elementId));

        keyWebElement = LoginPageKeys.InsertUser.name();
        elementId = WebSelector.getElementAttribute(keyWebComponent, keyWebElement);
        insertUserName =  Hooks.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elementId));
       // PageFactory.initElements(currentDriver,this);

    }

    public void accessToIt() {
        this.accessToLoginPage.click();
    }

    public void setUserName( String userName ) {
        insertUserName.clear();
        this.insertUserName.sendKeys(userName);
    }
}
