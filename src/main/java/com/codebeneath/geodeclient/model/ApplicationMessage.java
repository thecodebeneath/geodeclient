package com.codebeneath.geodeclient.model;

import java.io.Serializable;

/**
 *
 * @author thomas.black
 */
public class ApplicationMessage implements Serializable {

    private String message;

    public ApplicationMessage() {

    }

    public ApplicationMessage(String msg) {
        this.message = msg;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
    
    public String toString() {
       return "ApplicationMessage[message=" + message + "]"; 
    }

}
