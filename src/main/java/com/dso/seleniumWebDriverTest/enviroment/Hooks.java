package com.dso.seleniumWebDriverTest.enviroment;
/**
 * Created by David Solano.
 * 2018-07-24
 */

import com.dso.seleniumWebDriverTest.exception.NotFoundResourceException;
import com.dso.seleniumWebDriverTest.utilsType.CheckMethods;
import cucumber.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


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

    private static Environment environment;

    private static Scenario scenario;

    public static Environment getEnvironment() {
        return environment;
    }

    public static void setEnvironment(Environment environment) {
        Hooks.environment = environment;
    }



    /**
     * Public access to WebDriver object
     * @return
     */
    public static WebDriver getWebDriver( ) throws NotFoundResourceException {

        if (driver == null) { // Initialize singleton
            if (environment.getBrowser().equals(BrowserCodes.FIREFOX)) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (environment.getBrowser().equals(BrowserCodes.CHROME)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (environment.getBrowser().equals(BrowserCodes.EDGE)) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else if (environment.getBrowser().equals(BrowserCodes.INTERNET_EXPLORER)) {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            } else {
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
            wait = new WebDriverWait(driver, 15);
        }
        return wait;
    }

    public static void setWebDriverWait(WebDriverWait wait) {
        Hooks.wait = wait;
    }

    public static void setWebDriver(WebDriver driver) {
        Hooks.driver = driver;
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


    public static void setScenario(Scenario sc) {
        Hooks.scenario = sc;
    }

    public static Scenario getScenario() {
        return Hooks.scenario;
    }

    public static String getScenarioName() {
        String scenarioName = "Not Name Scenario";
        if (Hooks.scenario != null && Hooks.scenario.getName() != null) {
            scenarioName = Hooks.scenario.getName().toUpperCase();
        }

        return scenarioName;
    }
}
