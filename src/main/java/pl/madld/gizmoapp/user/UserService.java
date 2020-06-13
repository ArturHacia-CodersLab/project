package pl.madld.gizmoapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.madld.gizmoapp.admin.Admin;
import pl.madld.gizmoapp.admin.AdminService;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final AdminService adminService;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    protected List<Admin> findAllAdmins() {
        return adminService.findAll();
    }
    protected void createAdmin(Admin admin) {
        adminService.createAdmin(admin);
    }
}
