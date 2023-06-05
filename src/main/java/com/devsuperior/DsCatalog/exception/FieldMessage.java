package com.devsuperior.DsCatalog.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    private String filedName;
    private String message;

    public FieldMessage() {

    }

    public FieldMessage(String filedName, String message) {
        this.filedName = filedName;
        this.message = message;
    }

    public String getFiledName() {
        return filedName;
    }

    public String getMessage() {
        return message;
    }

}
