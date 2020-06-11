package pl.madld.cms.validation;

import pl.madld.cms.admin.Admin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {
    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        Admin admin = (Admin) object;
        return admin.getPassword().equals(admin.getConfirmPassword());
    }
}
