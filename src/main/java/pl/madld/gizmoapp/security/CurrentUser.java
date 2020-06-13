package pl.madld.gizmoapp.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CurrentUser extends User {
    private final pl.madld.gizmoapp.user.User user;

    public CurrentUser(String email, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.madld.gizmoapp.user.User user) {
        super(email, password, authorities);
        this.user = user;
    }
}
