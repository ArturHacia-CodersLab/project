package pl.madld.gizmoapp.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.madld.gizmoapp.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = Admin.TABLE_NAME)
@DiscriminatorValue(Admin.USER_TYPE)
public class Admin extends User {
    public static final String TABLE_NAME = "admins";
    public static final String USER_TYPE = "admin";

    @NotNull
    @Size(min = 3, max = 20, message = "{invalid.firstname.size}")
    private String firstname;
    @NotNull
    @Size(min = 3, max = 30, message = "{invalid.lastname.size}")
    private String lastname;

    @Override
    public String toString() {
        return "Admin {" +
                "id=" + getId() +
                ", email='" + getEmail() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
