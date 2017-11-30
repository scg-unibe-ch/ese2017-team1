package hello.Controllers;

import hello.Services.RoleService;
import hello.Services.UserService;
import hello.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Controller
public class UserController extends WebMvcConfigurerAdapter {


    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @RequestMapping("/showUsers")
    public String listUsers(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("logisticians", userService.listLogisticians());
        model.addAttribute("drivers", userService.listDrivers());
        return "showUsers";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("roles", roleService.listAll());
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/newUser")
    public String userSubmit(@ModelAttribute("user") User user, @RequestParam("roleId") Long roleId, Model model) {
        userService.saveUser(user, roleId);
        model.addAttribute("userRole", roleService.findRole(roleId));
        return "addedUser";
    }
}
