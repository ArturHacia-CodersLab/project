package pl.madld.gizmoapp.util;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class UtilService {
    public Set<Message> getMessages(HttpSession session, Model model) {
        Set<Message> sessionMesssages = (Set<Message>) session.getAttribute("sessionMessages");
        Set<Message> messages = new HashSet<>();
        if (sessionMesssages != null && sessionMesssages.size() != 0) {
            messages = sessionMesssages;
            model.addAttribute("sessionMessages", new HashSet<String>());
        }
        return messages;
    }

    public void addMessage(HttpSession session, Model model, Message message) {
        Set<Message> sessionMesssages = (Set<Message>) session.getAttribute("sessionMessages");
        if (sessionMesssages == null) {
            sessionMesssages = new HashSet<>();
        }
        sessionMesssages.add(message);
        model.addAttribute("sessionMessages", sessionMesssages);
    }
}
