package hello.Controllers;

import hello.LoginRole.Role;
import hello.Repositories.DriverRepository;
import hello.Repositories.RoleRepository;
import hello.Repositories.UserRepository;
import hello.Users.Driver.Driver;
import hello.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@Controller
public class UserController extends WebMvcConfigurerAdapter {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DriverRepository driverRepository;

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("roles", this.roleRepository.findAll());
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/newUser")
    public String userSubmit(@ModelAttribute("user") User user, @RequestParam("roleId") Long roleId, Model model) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findOne(roleId);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
        model.addAttribute("userRole", userRole);

        // Falls User ein Driver ist, wird noch ein neuer Driver erstellt
        if(roleId == 2){
            Driver driver = new Driver();
            driver.setId(user.getId());
            driver.setEmail(user.getEmail());
            driver.setFirstName(user.getName());
            driver.setLastName(user.getLastName());
            this.driverRepository.save(driver);
        }

        return "addedUser";
    }
}
