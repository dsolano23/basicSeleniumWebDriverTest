package com.dso.seleniumWebDriverTest.exception;
/**
 * Created by David Solano.
 * 2018-07-24
 */

public class NotFoundResourceException extends Exception{
    /**
     * Main exception message
     */
    private static final String message = "Not found Resource.";

    /**
     * Default constructor
     */
    public NotFoundResourceException(){
        super();
    }

    /**
     * Argumented constructor
     */
    public NotFoundResourceException(String msg){

        super(String.format("%s %s", message, msg));
    }
}
