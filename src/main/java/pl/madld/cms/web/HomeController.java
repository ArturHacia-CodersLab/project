package pl.madld.cms.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.madld.cms.admin.Admin;
import pl.madld.cms.admin.AdminService;

import java.util.List;

@AllArgsConstructor
@Controller
public class HomeController {
    private final AdminService adminService;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/create-admin")
    public String createAdmin() {
        List<Admin> admins = adminService.findAll();
        if (admins.isEmpty()) {
            Admin admin = new Admin();
            admin.setEmail("haciaa@gmail.com");
            admin.setPassword("haciaa123");
            admin.setFirstname("Artur");
            admin.setLastname("Hacia");
            adminService.saveAdmin(admin, false);
        }
        return "redirect:/admin/";
    }
}
