package com.dso.seleniumWebDriverTest.utilsType;


import com.dso.seleniumWebDriverTest.exception.NotFoundResourceException;
import com.dso.seleniumWebDriverTest.utilsType.constans.front.common.ElementAttributeKeys;
import com.dso.seleniumWebDriverTest.utilsType.constans.front.common.WebComponentKeys;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebSelector {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WebSelector.class);

    private static String PropertiesDir = new File( "." ).getAbsolutePath();
    private static Properties loginForm = null;
    private static Properties createFinalUserForm = null;
    private static Properties bookingPanel = null;
    private static Properties currentProp = new Properties();

    /**
     * Always return the same instance (singleton)
     * @return
     */
    private static Properties getWebElemntsProps( String keyWebComponent ) throws NotFoundResourceException {

        if (keyWebComponent.equalsIgnoreCase(WebComponentKeys.loginForm.name()) ) {
            if (loginForm == null){
                loginForm = new Properties();
                String fileName = WebComponentKeys.loginForm.name();
                currentProp.putAll(loadWebElementProperties(fileName));
            }else {
                currentProp.putAll(loginForm);
            }
        }
        return currentProp;
    }

    private static Properties loadWebElementProperties(String keyWebComponent) throws NotFoundResourceException {
        Properties props2 = new Properties();
        InputStream input = null;
        String path = "/src/main/Resources/webElementsProperties/";

        String webElemntsProperties = PropertiesDir + path + keyWebComponent + ".properties";
        try {

            boolean exist = new File(webElemntsProperties).exists();
            if(exist) {
                input = new FileInputStream(webElemntsProperties);
                // load a properties file
                props2.load(input);
            }else {
                throw new NotFoundResourceException("The file "+ webElemntsProperties + " does not exists");
            }


        } catch (IOException ex) {
            ex.printStackTrace();
            throw new NotFoundResourceException(String.format("%s %s", "Error on access to"+  webElemntsProperties +" does not exists ", ex.getMessage() ));
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props2;
    }

    public static String getElementAttribute (String keyWebComponent, String key, String attribute) throws NotFoundResourceException {
        String textValue="";

        if (!attribute.equalsIgnoreCase("class")){
            //TODO Optimize for multi language with a relaccionation to environment configuration extracting the set language
            key = key + "." + attribute + ".es";
            textValue = WebSelector.getWebElemntsProps(keyWebComponent).getProperty(key);
        }else {
            key = key + "." + attribute;
            textValue = WebSelector.getWebElemntsProps(keyWebComponent).getProperty(key);
        }

        return textValue;
    }

    public static By getElementAttribute(String keyWebComponent, String key) throws NotFoundResourceException {
        By target = null;
        String[] subValues = null;
        key = key + "." + ElementAttributeKeys.id.name();

        String value = WebSelector.getWebElemntsProps(keyWebComponent).getProperty(key);
        LOGGER.debug("The " + key + " property has the value: " + value);
        if(value.startsWith(ElementAttributeKeys.byId.name())){
            subValues = value.split(ElementAttributeKeys.byId.name()+ ".");
            value = subValues[1];
            target = By.id(value);
            LOGGER.debug("The location mode is by id and final value is: " + value);
        }else if(value.startsWith(ElementAttributeKeys.byName.name())){
            subValues = value.split(ElementAttributeKeys.byName.name()+ ".");
            value = subValues[1];
            target = By.name(value);
            LOGGER.debug("The location mode is by name and final value is: " + value);
        }else if(value.startsWith(ElementAttributeKeys.byCss.name())){
            subValues = value.split(ElementAttributeKeys.byCss.name()+ ".");
            value = subValues[1];
            target = By.cssSelector(value);
            LOGGER.debug("The location mode is by css and final value is: " + value);
        }else if(value.startsWith(ElementAttributeKeys.byClass.name())){
            subValues = value.split(ElementAttributeKeys.byClass.name()+ ".");
            value = subValues[1];
            target = By.cssSelector(value);
            LOGGER.debug("The location mode is by css and final value is: " + value);
        }
        return target;
    }
}
