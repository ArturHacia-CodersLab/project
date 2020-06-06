package pl.madld.cms.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.madld.cms.admin.Admin;
import pl.madld.cms.admin.AdminService;

import java.util.HashSet;
import java.util.Set;

public class SpringDataAdminDetailsService implements UserDetailsService {
    private AdminService adminService;

    @Autowired
    public void setUserRepository(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Admin admin = adminService.findByUserName(username);
        if (admin == null) {throw new UsernameNotFoundException(username); }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(admin.getRole()));
        return new CurrentAdmin(admin.getUsername(),admin.getPassword(),
                grantedAuthorities, admin);
    }
}
