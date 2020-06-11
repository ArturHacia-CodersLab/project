package pl.madld.cms.util;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class UtilService {
    public Set<String> getMessages(HttpSession session, Model model) {
        Set<String> sessionMesssages = (Set<String>) session.getAttribute("sessionMessages");
        Set<String> messages = new HashSet<>();
        if (sessionMesssages != null && sessionMesssages.size() != 0) {
            messages = sessionMesssages;
            model.addAttribute("sessionMessages", new HashSet<String>());
        }
        return messages;
    }

    public void addMessage(HttpSession session, Model model, String messages) {
        Set<String> sessionMesssages = (Set<String>) session.getAttribute("sessionMessages");
        if (sessionMesssages == null) {
            sessionMesssages = new HashSet<>();
        }
        sessionMesssages.add(messages);
        model.addAttribute("sessionMessages", sessionMesssages);
    }
}
