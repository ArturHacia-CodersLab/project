package pl.madld.gizmoapp.validation;

import pl.madld.gizmoapp.user.UserService;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    Class<? extends UserService> service();
    String message() default "{invalid.email.email-unique}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
