package pl.madld.gizmoapp.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.madld.gizmoapp.validation.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ConfirmPassword(groups = {AddValidators.class, ChangePasswordValidators.class})
@UniqueEmail(groups = {AddValidators.class, EditValidators.class})
@Entity
@Table(name = User.TABLE_NAME)
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name=User.USER_TYPE_COLUMN ,discriminatorType=DiscriminatorType.STRING)
public class User {
    public static final String TABLE_NAME = "users";
    public static final String USER_TYPE_COLUMN = "user_type";

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
    private int enabled;
    private String role;
    private String username;
}
