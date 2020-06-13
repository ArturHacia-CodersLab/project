package pl.madld.gizmoapp.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.madld.gizmoapp.admin.AdminService;

@AllArgsConstructor
@Controller
public class HomeController {
    private final AdminService adminService;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }
}
