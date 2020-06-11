package pl.madld.cms.admin;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.madld.cms.admin.security.Role;

import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin findById(long id) {
        return adminRepository.getOne(id);
    }
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public void saveAdmin(Admin admin, boolean isEdit) {
        if (isEdit) {
            Admin existAdmin = findById(admin.getId());
            admin.setPassword(existAdmin.getPassword());
        } else {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        }
        admin.setEnabled(1);
        admin.setRole(Role.ROLE_ADMIN.toString());
        adminRepository.save(admin);
    }

    public void deleteAdmin(long id) {
        Admin admin = findById(id);
        adminRepository.delete(admin);
    }
}
