package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.List;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home(Model model, final HttpServletRequest req){
        String role = userService.getUserByUsername(req.getRemoteUser()).getRole().getRole();
        model.addAttribute("role", role);
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}