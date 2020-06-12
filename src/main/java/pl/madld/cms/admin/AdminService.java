package pl.madld.cms.admin;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.madld.cms.admin.security.Role;
import pl.madld.cms.user.UserService;

import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class AdminService implements UserService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin findById(Long id) {
        return adminRepository.getOne(id);
    }
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public void createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setEnabled(1);
        admin.setRole(Role.ROLE_ADMIN.toString());
        adminRepository.save(admin);
    }
    public void saveAdmin(Admin admin, Admin editAdmin) {
        editAdmin.setEmail(admin.getEmail());
        editAdmin.setFirstname(admin.getFirstname());
        editAdmin.setLastname(admin.getLastname());
        adminRepository.save(editAdmin);
    }

    public void changePassword(Admin admin, Admin editAdmin) {
        editAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(editAdmin);
    }

    public void deleteAdmin(Long id) {
        Admin admin = findById(id);
        adminRepository.delete(admin);
    }
}
