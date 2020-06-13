package pl.madld.gizmoapp.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.madld.gizmoapp.security.CurrentUser;
import pl.madld.gizmoapp.util.Message;
import pl.madld.gizmoapp.util.MessageType;
import pl.madld.gizmoapp.util.UtilService;
import pl.madld.gizmoapp.validation.AddValidators;
import pl.madld.gizmoapp.validation.ChangePasswordValidators;
import pl.madld.gizmoapp.validation.EditValidators;

import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;
import java.util.Set;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
@SessionAttributes({"sessionMessages", "editAdmin"})
public class AdminController {
    private final UtilService utilService;
    private final AdminService adminService;

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser;
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
        model.addAttribute("mode", "add");
        model.addAttribute("admin", new Admin());
        return "admin/admin";
    }
    @PostMapping("/admin")
    public String createAdmin(@Validated({Default.class, AddValidators.class}) Admin admin, BindingResult result,
                              HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "add");
            model.addAttribute("bindingResult", result);
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
        if (result.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("bindingResult", result);
            return "admin/admin";
        }
        Admin editAdmin = (Admin) session.getAttribute("editAdmin");
        if (editAdmin == null || !editAdmin.getId().equals(admin.getId())) {
            utilService.addMessage(session, model, new Message("message.critical-save", MessageType.danger));
            return "redirect:/admin/admins";
        }
        adminService.saveAdmin(admin, editAdmin);
        utilService.addMessage(session, model, new Message("message.admin-save", MessageType.success));
        return "redirect:/admin/admins";
    }

    @GetMapping("/pass/{id}")
    public String password(@PathVariable long id, Model model) {
        Admin admin = adminService.findById(id);
        if (admin == null) {
            return "redirect:/admin/admins";
        }
        model.addAttribute("mode", "pass");
        model.addAttribute("admin", admin);
        model.addAttribute("editAdmin", admin);
        return "admin/admin";
    }
    @PostMapping("/pass/{id}")
    public String changePassword(@Validated({ChangePasswordValidators.class}) Admin admin, BindingResult result,
                                 HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "pass");
            model.addAttribute("bindingResult", result);
            return "admin/admin";
        }
        Admin editAdmin = (Admin) session.getAttribute("editAdmin");
        if (editAdmin == null || !editAdmin.getId().equals(admin.getId())) {
            utilService.addMessage(session, model, new Message("message.critical-save", MessageType.danger));
            return "redirect:/admin/admins";
        }
        adminService.changePassword(admin, editAdmin);
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