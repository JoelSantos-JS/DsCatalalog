package com.devsuperior.DsCatalog.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErro extends StandardError {

    private List<FieldMessage> erros = new ArrayList<>();

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addError(String fieldName, String message) {
        erros.add(new FieldMessage(fieldName, message));
    }

}
