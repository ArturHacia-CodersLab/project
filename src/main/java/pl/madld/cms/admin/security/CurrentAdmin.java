package pl.madld.cms.admin.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import pl.madld.cms.admin.Admin;

import java.util.Collection;

@Getter
public class CurrentAdmin extends User {
    private final Admin admin;

    public CurrentAdmin(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       Admin admin) {
        super(username, password, authorities);
        this.admin = admin;
    }
}
