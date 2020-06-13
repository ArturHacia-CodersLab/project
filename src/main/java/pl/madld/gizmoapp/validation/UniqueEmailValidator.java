package pl.madld.gizmoapp.validation;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.madld.gizmoapp.user.User;
import pl.madld.gizmoapp.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Setter
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, User> {
    private final UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        User existUser = userService.findByEmail(user.getEmail());
        return existUser == null || existUser.getId().equals(user.getId());
    }
}
