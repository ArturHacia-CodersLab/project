package pl.madld.gizmoapp.admin;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@SessionAttributes({"sessionMessages", "baseAdmin"})
public class AdminController {
    Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final UtilService utilService;
    private final AdminService adminService;

    private CurrentUser currentUser;

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        this.currentUser = currentUser;
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
        logger.info(currentUser.getUsername() + " add admin: " + admin);
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
        model.addAttribute("baseAdmin", admin);
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
        Admin baseAdmin = (Admin) session.getAttribute("baseAdmin");
        if (baseAdmin == null || !baseAdmin.getId().equals(admin.getId())) {
            utilService.addMessage(session, model, new Message("message.critical-save", MessageType.danger));
            return "redirect:/admin/admins";
        }
        adminService.saveAdmin(admin, baseAdmin);
        utilService.addMessage(session, model, new Message("message.admin-save", MessageType.success));
        logger.info(currentUser.getUsername() + " edit admin: " + baseAdmin);
        return "redirect:/admin/admins";
    }

    @GetMapping("/admin/pass/{id}")
    public String password(@PathVariable long id, Model model) {
        Admin admin = adminService.findById(id);
        if (admin == null) {
            return "redirect:/admin/admins";
        }
        model.addAttribute("mode", "pass");
        model.addAttribute("admin", admin);
        model.addAttribute("baseAdmin", admin);
        return "admin/admin";
    }
    @PostMapping("/admin/pass/{id}")
    public String changePassword(@Validated({ChangePasswordValidators.class}) Admin admin, BindingResult result,
                                 HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "pass");
            model.addAttribute("bindingResult", result);
            return "admin/admin";
        }
        Admin baseAdmin = (Admin) session.getAttribute("baseAdmin");
        if (baseAdmin == null || !baseAdmin.getId().equals(admin.getId())) {
            utilService.addMessage(session, model, new Message("message.critical-save", MessageType.danger));
            return "redirect:/admin/admins";
        }
        adminService.changePassword(admin, baseAdmin);
        utilService.addMessage(session, model, new Message("message.admin-save", MessageType.success));
        logger.info(currentUser.getUsername() + " change password admin: " + baseAdmin);
        return "redirect:/admin/admins";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteAdmin(@PathVariable long id, HttpSession session, Model model) {
        Admin admin = adminService.findById(id);
        if (admin == null) {
            utilService.addMessage(session, model, new Message("message.critical-save", MessageType.danger));
            return "redirect:/admin/admins";
        }
        adminService.deleteAdmin(admin);
        utilService.addMessage(session, model, new Message("message.admin-delete", MessageType.success));
        logger.info(currentUser.getUsername() + " delete admin: " + admin);
        return "redirect:/admin/admins";
    }
}
