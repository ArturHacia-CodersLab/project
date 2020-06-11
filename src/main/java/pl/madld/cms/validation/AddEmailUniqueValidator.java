package pl.madld.cms.validation;

import lombok.AllArgsConstructor;
import pl.madld.cms.admin.AdminService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class AddEmailUniqueValidator implements ConstraintValidator<AddEmailUnique, String> {
    private AdminService adminService;

    @Override
    public void initialize(AddEmailUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return adminService.findByEmail(email) == null;
    }
}
