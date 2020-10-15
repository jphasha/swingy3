package com.wethinkcode.java.swingy.helperClasses;

public class CustomException extends Exception {
    private static final long serialVersionUID = -1213983183038569661L;
    
    public CustomException(String reason, String statement) {
        super(reason + ": " + statement);
    }

    public CustomException(String reason, String statement, Throwable cause) {
        super(reason + ": " + statement, cause);
    }
}
