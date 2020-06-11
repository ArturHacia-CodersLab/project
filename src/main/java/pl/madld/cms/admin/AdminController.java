package pl.madld.cms.admin;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.madld.cms.admin.security.CurrentAdmin;
import pl.madld.cms.validation.AddFormValidators;
import pl.madld.cms.validation.EditFormValidators;

import javax.validation.groups.Default;

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

    @GetMapping("/admin")
    public String addAdmin(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/admin";
    }
    @PostMapping("/admin")
    public String saveAdmin(@Validated({Default.class, AddFormValidators.class}) Admin admin, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/admin";
        }
        adminService.saveAdmin(admin, false);
        return "redirect:/admin/admins";
    }

    @GetMapping("/admin/{id}")
    public String editAdmin(@PathVariable long id, Model model) {
        Admin admin = adminService.findById(id);
        if (admin == null) {
            return "redirect:/admin/admins";
        }
        model.addAttribute("mode", "edit");
        model.addAttribute("admin", admin);
        return "admin/admin";
    }
    @PostMapping("/admin/{id}")
    public String saveAdmin(@Validated({Default.class, EditFormValidators.class}) Admin admin, BindingResult result,
                            Model model) {
        model.addAttribute("mode", "edit");
        if (result.hasErrors()) {
            return "admin/admin";
        }
        adminService.saveAdmin(admin, true);
        return "redirect:/admin/admins";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable long id) {
        adminService.deleteAdmin(id);
        return "redirect:/admin/admins";
    }
}
