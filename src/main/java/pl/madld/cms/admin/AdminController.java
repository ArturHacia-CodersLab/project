package pl.madld.cms.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.madld.cms.admin.security.CurrentAdmin;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("")
    public String admin(@AuthenticationPrincipal CurrentAdmin currentAdmin, Model model) {
        model.addAttribute("currentAdmin", currentAdmin.getAdmin());
        return "admin/home";
    }
}
