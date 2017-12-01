package hello.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import hello.Users.Driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hello.LoginRole.Role;
import hello.Users.User;
import hello.Repositories.RoleRepository;
import hello.Repositories.UserRepository;

/**
 * Service responsible for handling requests concerning users
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DriverService driverService;


    /**
     * returns User with Email email
     */
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * saves user in userRepository, encodes its password, sets user active and sets the role of the user
     * @param roleId Role that user has
     */
    @Override
    public void saveUser(User user, Long roleId) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findOne(roleId);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);

        // Falls User ein Driver ist, wird noch ein neuer Driver erstellt
        if(roleId == 2){
            Driver driver = new Driver();
            driver.setId(user.getId());
            driver.setEmail(user.getEmail());
            driver.setFirstName(user.getName());
            driver.setLastName(user.getLastName());
            driverService.save(driver);
        }
    }

    /**
     * @return Iterable of all Users in userRepository
     */
    @Override
    public Iterable<User> listAllUsers(){ return this.userRepository.findAll(); }


    @Override
    public Iterable<User> listLogisticians(){
        ArrayList<User> logisticians = new ArrayList<>();
        for(User user1 : listAllUsers()){
            if(user1.getRoles().contains(roleRepository.findByRole("ROLE_LOGISTICIAN"))){
                logisticians.add(user1);
            }
        }
        return logisticians;
    }

    @Override
    public Iterable<User> listDrivers(){
        ArrayList<User> drivers = new ArrayList<>();
        for(User user1 : listAllUsers()){
            if(user1.getRoles().contains(roleRepository.findByRole("ROLE_DRIVER"))){
                drivers.add(user1);
            }
        }
        return drivers;
    }

}