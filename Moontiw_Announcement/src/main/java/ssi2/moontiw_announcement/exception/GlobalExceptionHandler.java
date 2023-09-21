package ssi2.moontiw_announcement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
//        Map<String, Object> response = new LinkedHashMap<>();
//        response.put("status", "error");
//        response.put("title", "Validation Error");
//        response.put("instance", "/api/announcements");
//
//        List<Map<String, String>> errors = new ArrayList<>();
//        for (FieldErrorDto fieldError : ex.getFieldErrors()) {
//            Map<String, String> error = new HashMap<>();
//            error.put("field", fieldError.getField());
//            error.put("errorMessage", fieldError.getErrorMessage());
//            errors.add(error);
//        }
//        response.put("detail", errors);
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//    }
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
    String title="Announcement attributes validation failed";
    ErrorResponse er = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),title,request.getDescription(false));
    for (ObjectError field : ex.getBindingResult().getAllErrors()) {
        String fieldName = field.getCode();
        if (field instanceof FieldError) fieldName = ((FieldError) field).getField();
        er.addValidateError(fieldName, field.getDefaultMessage());
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
}
}

