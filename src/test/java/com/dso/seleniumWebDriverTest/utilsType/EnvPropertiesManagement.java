package com.dso.seleniumWebDriverTest.utilsType;
/**
 * Created by David Solano.
 * 2018-07-24
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.dso.seleniumWebDriverTest.enviroment.EnvironmentConstantsNames;
import com.dso.seleniumWebDriverTest.exception.NotFoundResourceException;

public class EnvPropertiesManagement {
    /**
     * properties path
     */
    private static String PropertiesDir = new File( "." ).getAbsolutePath();
    /**
     * Singleton properties object
     */
    private static Properties props = null;



    /**
     * Always return the same instance (singleton)
     * @return
     */
    public static Properties getEnvProps() throws NotFoundResourceException {
        if(props == null){
            props = loadDefaultEnvironmentProperties();
        }
        return props;
    }


    public static Properties loadMvnEnvironmentProperties(String environmentFile) throws NotFoundResourceException {

        InputStream input = null;
        String path = EnvironmentConstantsNames.PATH_ENVIRONMENT_PROPERTIES;
        //String fileName = "PreProdEnvironment";
        String enviromentProperties = PropertiesDir + path + environmentFile + ".properties";
        try {

            boolean exist = new File(enviromentProperties).exists();
            if(exist) {
                input = new FileInputStream(enviromentProperties);
                // load a properties file
                if(EnvPropertiesManagement.props == null){
                    EnvPropertiesManagement.props = new Properties();
                }
                EnvPropertiesManagement.props.load(input);
            }else {
                throw new NotFoundResourceException("The file "+ enviromentProperties + " does not exists");
            }


        } catch (IOException ex) {
            ex.printStackTrace();
            throw new NotFoundResourceException(String.format("%s %s", "Error on access to"+  enviromentProperties +" does not exists ", ex.getMessage() ));
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props;
    }

    /**
     * Fill data from file into properties object
     * @return
     */
    public static Properties loadDefaultEnvironmentProperties() throws NotFoundResourceException {

        InputStream input = null;
        String path = EnvironmentConstantsNames.PATH_ENVIRONMENT_PROPERTIES;
        String fileName = "defaultEnvironment";
        //String fileName = "PreProdEnvironment";
        String enviromentProperties = PropertiesDir + path + fileName + ".properties";
        try {

            boolean exist = new File(enviromentProperties).exists();
            if(exist) {
                input = new FileInputStream(enviromentProperties);
                // load a properties file
                if(EnvPropertiesManagement.props == null){
                    EnvPropertiesManagement.props = new Properties();
                }
                EnvPropertiesManagement.props.load(input);
            }else {
                throw new NotFoundResourceException("The file "+ enviromentProperties + " does not exists");
            }


        } catch (IOException ex) {
            ex.printStackTrace();
            throw new NotFoundResourceException(String.format("%s %s", "Error on access to"+  enviromentProperties +" does not exists ", ex.getMessage() ));
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props;
    }


    /**
     * Persist data object to file
     *
     */
    public static void saveProps(){
        OutputStream output = null;
        String path = EnvironmentConstantsNames.PATH_ENVIRONMENT_PROPERTIES;
        String fileName = "defaultEnvironment";
        String enviromentProperties = PropertiesDir + path + fileName + ".properties";
        try {
            output = new FileOutputStream(enviromentProperties);
            // Save properties to project root folder

            EnvPropertiesManagement.props.store(output, null);
        }

        catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
