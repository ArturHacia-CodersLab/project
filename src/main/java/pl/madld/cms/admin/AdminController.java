package pl.madld.cms.admin;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.madld.cms.admin.security.CurrentAdmin;
import pl.madld.cms.util.Message;
import pl.madld.cms.util.MessageType;
import pl.madld.cms.util.UtilService;
import pl.madld.cms.validation.AddValidators;
import pl.madld.cms.validation.EditValidators;

import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;
import java.util.Set;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
@SessionAttributes({"sessionMessages", "editAdmin"})
public class AdminController {
    private final UtilService utilService;
    private final AdminService adminService;

    @ModelAttribute("currentAdmin")
    public CurrentAdmin getCurrentAdmin(@AuthenticationPrincipal CurrentAdmin currentAdmin) {
        return currentAdmin;
    }
    @ModelAttribute("messages")
    public Set<Message> getMessages(HttpSession session, Model model) {
        return utilService.getMessages(session, model);
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
    public String createAdmin(@Validated({Default.class, AddValidators.class}) Admin admin, BindingResult result,
                              HttpSession session, Model model) {
        if (result.hasErrors()) {
            return "admin/admin";
        }
        adminService.createAdmin(admin);
        utilService.addMessage(session, model, new Message("message.admin-add", MessageType.success));
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
        model.addAttribute("editAdmin", admin);
        return "admin/admin";
    }
    @PostMapping("/admin/{id}")
    public String saveAdmin(@Validated({Default.class, EditValidators.class}) Admin admin, BindingResult result,
                            HttpSession session, Model model) {
        model.addAttribute("mode", "edit");
        if (result.hasErrors()) {
            return "admin/admin";
        }
        Admin editAdmin = (Admin) session.getAttribute("editAdmin");
        if (editAdmin == null || !editAdmin.getId().equals(admin.getId())) {
            utilService.addMessage(session, model, new Message("message.critical-edit", MessageType.danger));
            return "redirect:/admin/admins";
        }
        adminService.saveAdmin(admin, editAdmin);
        utilService.addMessage(session, model, new Message("message.admin-save", MessageType.success));
        return "redirect:/admin/admins";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable long id, HttpSession session, Model model) {
        adminService.deleteAdmin(id);
        utilService.addMessage(session, model, new Message("message.admin-delete", MessageType.success));
        return "redirect:/admin/admins";
    }
}
