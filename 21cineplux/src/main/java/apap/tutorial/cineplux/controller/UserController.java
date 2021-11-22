package apap.tutorial.cineplux.controller;
import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.RoleModel;
import apap.tutorial.cineplux.model.UserModel;
import apap.tutorial.cineplux.service.RoleService;
import apap.tutorial.cineplux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    private String addUserFormPage(Model model){
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.getListRole();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user" ;
    }

    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        userService.addUser (user);
        model.addAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/viewall")
    private String viewAllUser(Model model){
        List<UserModel> listUser = userService.getListUser();
        model.addAttribute("listUser",listUser);
        return "viewall-user" ;
    }

    @GetMapping(value="/viewall/delete/{idUser}")
    public String deleteUser(
            @PathVariable String idUser,
            Model model
    ){
        UserModel user = userService.getUser(idUser);
        userService.deleteUser(user);
        return "delete-user";
    }

    @GetMapping(value="/setpassword")
    public String setPassword(){
        return "set-password";
    }

    @PostMapping(value="/setpassword/change")
    public String changePassword(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "passLama") String passLama,
            @RequestParam(value = "passBaru") String passBaru,
            @RequestParam(value = "konfPass") String konfPass,
            HttpServletRequest request,
            Model model
    ) {
        UserModel user = userService.getUserByUsername(username);
        if (userService.passwordMatch(passLama, user.getPassword())) {
            if (passLama.equals(passBaru)){
                model.addAttribute("status", "Password baru tidak boleh sama dengan password lama.");
            }
            else if (passBaru.equals(konfPass)) {
                userService.setPassword(user, passBaru);
                model.addAttribute("status", "Password berhasil diubah!");
            } else {
                model.addAttribute("status", "Password baru tidak sama!");
            }
        } else {
            model.addAttribute("status", "Password salah!");
        }

        return "change-password";
    }


}
