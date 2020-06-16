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

    public void createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setEnabled((short) 1);
        admin.setRole(Role.ROLE_ADMIN.toString());
        admin.setUsername(admin.getFirstname() + " " + admin.getLastname());
        adminRepository.save(admin);
    }
    protected void saveAdmin(Admin admin, Admin baseAdmin) {
        baseAdmin.setEmail(admin.getEmail());
        baseAdmin.setFirstname(admin.getFirstname());
        baseAdmin.setLastname(admin.getLastname());
        baseAdmin.setUsername(admin.getFirstname() + " " + admin.getLastname());
        adminRepository.save(baseAdmin);
    }

    protected void changePassword(Admin admin, Admin baseAdmin) {
        baseAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(baseAdmin);
    }

    protected void deleteAdmin(Admin admin) {
        adminRepository.delete(admin);
    }
}
