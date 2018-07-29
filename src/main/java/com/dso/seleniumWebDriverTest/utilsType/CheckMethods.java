package com.dso.seleniumWebDriverTest.utilsType;
/**
 * Created by David Solano.
 * 2018-07-24
 */


//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.dso.seleniumWebDriverTest.enviroment.Hooks;



public class CheckMethods {
	private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CheckMethods.class);



    public void checkMessageStatus(String expectedMessage, String constantKey) throws Throwable {

        WebElement strMessage = Hooks.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id(constantKey)));
        String currentMessage = strMessage.getText();
        LOGGER.info("Current Message:  " + currentMessage);
      //  Assert.assertEquals(expectedMessage, currentMessage);
    }


    public void mouseHoverAction(String selectMainMenu, String selectSubMenu) throws Throwable {

        WebElement element = Hooks.getWebDriver().findElement(By.id(selectMainMenu));
        Actions action = new Actions(Hooks.getWebDriver());
        action.moveToElement(element).perform();
        WebElement subElement = Hooks.getWebDriver().findElement(By.cssSelector(selectSubMenu));
        action.moveToElement(subElement);
        action.click();
        action.perform();
    }

}
