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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
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

    @RequestMapping("/showUsers")
    public String listUsers(@ModelAttribute("user") User user, Model model) {

        Iterable<User> allUsers = this.userRepository.findAll();
        ArrayList<User> admins = new ArrayList<>();
        ArrayList<User> logisticians = new ArrayList<>();
        ArrayList<User> drivers = new ArrayList<>();
        for(User user1 : allUsers){
            if(user1.getRoles().contains(roleRepository.findByRole("ROLE_ADMIN"))){
                admins.add(user1);
            }
            if(user1.getRoles().contains(roleRepository.findByRole("ROLE_LOGISTICIAN"))){
                logisticians.add(user1);
            }
            if(user1.getRoles().contains(roleRepository.findByRole("ROLE_DRIVER"))){
                drivers.add(user1);
            }
        }
        model.addAttribute("admins", admins);
        model.addAttribute("logisticians", logisticians);
        model.addAttribute("drivers", drivers);
        return "showUsers";
    }

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
