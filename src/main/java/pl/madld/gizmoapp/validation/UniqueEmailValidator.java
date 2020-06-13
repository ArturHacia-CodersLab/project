package pl.madld.gizmoapp.validation;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import pl.madld.gizmoapp.user.User;
import pl.madld.gizmoapp.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Setter
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, User> {
    private final ApplicationContext context;
    private UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        setUserService(context.getBean(constraintAnnotation.service()));
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        User existUser = userService.findByEmail(user.getEmail());
        return existUser == null || existUser.getId().equals(user.getId());
    }
}
