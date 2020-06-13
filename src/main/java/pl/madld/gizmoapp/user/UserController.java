package pl.madld.gizmoapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.madld.gizmoapp.admin.Admin;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    public final UserService userService;

    @GetMapping("/create-admin")
    public String createAdmin() {
        List<Admin> admins = userService.findAllAdmins();
        if (admins.isEmpty()) {
            Admin admin = new Admin();
            admin.setEmail("haciaa@gmail.com");
            admin.setPassword("haciaa123");
            admin.setFirstname("Artur");
            admin.setLastname("Hacia");
            userService.createAdmin(admin);
        }
        return "redirect:/admin/";
    }
}
