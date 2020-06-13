package pl.madld.gizmoapp.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.madld.gizmoapp.security.Role;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    protected Admin findById(Long id) {
        return adminRepository.getOne(id);
    }
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public void createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setEnabled(1);
        admin.setRole(Role.ROLE_ADMIN.toString());
        admin.setUsername(admin.getFirstname() + " " + admin.getLastname());
        adminRepository.save(admin);
    }
    protected void saveAdmin(Admin admin, Admin editAdmin) {
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
