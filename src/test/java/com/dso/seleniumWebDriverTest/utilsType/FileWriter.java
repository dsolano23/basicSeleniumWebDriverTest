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

import com.dso.seleniumWebDriverTest.exception.NotFoundResourceException;

public class FileWriter {
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
    public static Properties getProps() throws NotFoundResourceException {
        if(props == null){
            props = loadProperties();
        }
        return props;
    }

    /**
     * Fill data from file into properties object
     * @return
     */
    private static Properties loadProperties() throws NotFoundResourceException {

        InputStream input = null;

        try {
            boolean exist = new File(PropertiesDir+ConstantsIDKeyNames.Path_testProperties).exists();
            if(exist) {
                input = new FileInputStream(PropertiesDir+ConstantsIDKeyNames.Path_testProperties);
                // load a properties file
                if(FileWriter.props == null){
                    FileWriter.props = new Properties();
                }
                FileWriter.props.load(input);
            }else {
                throw new NotFoundResourceException("The file "+ ConstantsIDKeyNames.Path_testProperties + " does not exists");
            }


        } catch (IOException ex) {
            ex.printStackTrace();
            throw new NotFoundResourceException(String.format("%s %s", "Error on access to"+  ConstantsIDKeyNames.Path_testProperties +" does not exists ", ex.getMessage() ));
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

        try {
            output = new FileOutputStream(PropertiesDir+ConstantsIDKeyNames.Path_testProperties);
            // Save properties to project root folder

            FileWriter.props.store(output, null);
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
