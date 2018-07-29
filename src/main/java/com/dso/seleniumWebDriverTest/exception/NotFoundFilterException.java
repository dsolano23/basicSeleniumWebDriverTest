package com.dso.seleniumWebDriverTest.exception;

/**
 * Created by David Solano.
 * 2018-07-24
 */

public class NotFoundFilterException extends Exception{
    /**
     * Main exception message
     */
    private static final String message = "Not found filter on web";

    /**
     * Default constructor
     */
    public NotFoundFilterException(){
        super();
    }

    /**
     * Argumented constructor
     */
    public NotFoundFilterException(String msg){

        super(String.format("%s %s", message, msg));
    }
}
