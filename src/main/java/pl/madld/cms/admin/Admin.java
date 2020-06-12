package pl.madld.cms.admin;

import lombok.*;
import pl.madld.cms.user.User;
import pl.madld.cms.validation.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ConfirmPassword(groups = {AddValidators.class, ChangePasswordValidators.class})
@UniqueEmail(service = AdminService.class, groups = {AddValidators.class, EditValidators.class})
@Entity
@Table(name = Admin.TABLE_NAME)
public class Admin implements User {
    public static final String TABLE_NAME = "admins";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "{invalid.email.not-empty}")
    @Email(message = "{invalid.email.email}")
    @Column(nullable = false, unique = true, length = 60)
    private String email;
    @AdminPassword(groups = {AddValidators.class, ChangePasswordValidators.class})
    private String password;
    @Transient
    private String confirmPassword;
    @NotNull
    @Size(min = 5, max = 20, message = "{invalid.firstname.size}")
    private String firstname;
    @NotNull
    @Size(min = 5, max = 30, message = "{invalid.lastname.size}")
    private String lastname;
    private int enabled;
    private String role;
}
