package hello.Controllers;

import hello.Login.User;
import hello.Login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by angelakeller on 26.10.17.
 */

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    private User user;


    @RequestMapping("/loginForm")
    public String login(@ModelAttribute("User") User user, Model model) {

        Iterable<User> users = this.userRepository.findAll();
        model.addAttribute("user", user);
        return "loginForm";
    }


    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "loginForm";
    }


    @PostMapping("/loginForm")
    public String loginSubmit(@ModelAttribute("user") User user) {
        //this.productOrderRepository.save(productOrder);
        return "driver";
    }
}
