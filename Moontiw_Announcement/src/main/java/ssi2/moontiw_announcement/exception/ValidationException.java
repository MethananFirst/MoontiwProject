package ssi2.moontiw_announcement.exception;

import java.util.List;

public class ValidationException extends RuntimeException {
    private List<FieldErrorDto> fieldErrors;

    public ValidationException(List<FieldErrorDto> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }
}


