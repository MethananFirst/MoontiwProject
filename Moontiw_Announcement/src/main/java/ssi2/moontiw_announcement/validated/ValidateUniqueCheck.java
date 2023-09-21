package ssi2.moontiw_announcement.validated;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = UniqueUserDetail.class)
@Target({TYPE,FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidateUniqueCheck {
    String message() default "does not unique";
    Class<?>[] groups()  default {};
    Class<? extends Payload>[] payload() default {};

}
