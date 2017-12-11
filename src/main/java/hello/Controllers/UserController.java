package hello.Controllers;

import hello.Services.RoleService;
import hello.Services.UserService;
import hello.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import hello.Users.Password;


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
        model.addAttribute("roles", roleService.list());
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/newUser")
    public String userSubmit(@ModelAttribute("user") User user, @RequestParam("roleId") Long roleId, Model model) {
        userService.saveUser(user, roleId);
        model.addAttribute("userRole", roleService.findRole(roleId));
        return "addedUser";
    }

    @GetMapping("/changePassword")
    public String newPassword(Model model) {
        model.addAttribute("password", new Password());
        return "changePassword";
    }

    @PostMapping(value = "/changePassword")
    public String passwordSubmit( @ModelAttribute("password") Password password, Model model) {
        userService.changePassword(password.getPassword());
        model.addAttribute("changed", true);
        return "changePassword";
    }

    /**
     * Double checks if logistician really wants to delete user.
     * @param userId Id of user that would be deleted
     */
    @RequestMapping(value = "/deleteUserCheck/{userId}")
    public String deleteUserCheck(@PathVariable("userId") Long userId, Model model){
        model.addAttribute("user", userService.findUser(userId));
        return "deleteUserCheck";
    }

    /**
     * Deletes user and shows user that has been deleted.
     * @param userId Id of user that has been deleted
     */
    @RequestMapping(value = "/deleteUser/{userId}")
    public String deleteTour(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.findUser(userId));
        userService.deleteUser(userId);
        return "deleteUser";
    }
}
