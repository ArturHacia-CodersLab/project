package pl.madld.cms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.madld.cms.user.User;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(Model model) {
        User user = new User(1, "Artur", "Hacia");
        model.addAttribute("user", user);
        return "home";
    }
}
