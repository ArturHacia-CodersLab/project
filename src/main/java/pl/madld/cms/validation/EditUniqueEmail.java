package pl.madld.cms.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EditUniqueEmailValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EditUniqueEmail {
    String message() default "{invalid.email.email-unique}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
