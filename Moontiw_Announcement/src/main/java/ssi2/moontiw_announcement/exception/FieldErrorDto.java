package ssi2.moontiw_announcement.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldErrorDto {
    private String field;
    private String errorMessage;

    public FieldErrorDto(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
