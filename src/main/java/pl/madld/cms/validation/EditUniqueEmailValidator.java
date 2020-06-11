package pl.madld.cms.validation;

import lombok.AllArgsConstructor;
import pl.madld.cms.admin.Admin;
import pl.madld.cms.admin.AdminService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class EditUniqueEmailValidator implements ConstraintValidator<EditUniqueEmail, Object> {
    private AdminService adminService;

    @Override
    public void initialize(EditUniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        Admin admin = (Admin) object;
        Admin existAdmin = adminService.findByEmail(admin.getEmail());
        return existAdmin == null || admin.getId().equals(existAdmin.getId());
    }
}
