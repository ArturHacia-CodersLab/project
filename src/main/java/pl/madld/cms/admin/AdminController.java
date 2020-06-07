package pl.madld.cms.admin;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.madld.cms.admin.security.CurrentAdmin;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @ModelAttribute("currentAdmin")
    public Admin getCurrentAdmin(@AuthenticationPrincipal CurrentAdmin currentAdmin) {
        return currentAdmin.getAdmin();
    }

    @GetMapping("")
    public String admin() {
        return "admin/home";
    }

    @GetMapping("/admins")
    public String getAdmins(Model model) {
        model.addAttribute("admins", adminService.findAll());
        return "admin/admins";
    }
}
