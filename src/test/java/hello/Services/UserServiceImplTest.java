package hello.Services;

import hello.LoginRole.Role;
import hello.Repositories.RoleRepository;
import hello.Repositories.UserRepository;
import hello.Users.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Tests functionality of UserServiceImpl class.
 * We do not test the methods findUserByEmail(), findUser(), listAllUser(), currentUser(), changePasseord() and
 * deleteUser() since they only depend on the UserRepository to work and do not have a complex logic.
 */
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    User driver;
    User logistician;
    Role driverRole;

    ArrayList<User> drivers;
    ArrayList<User> logisticians;
    ArrayList<User> users;

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserServiceImpl userServiceImpl() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @MockBean
    private DriverService driverService;


    @Before
    public void setUp(){
        drivers = new ArrayList<>();
        logisticians = new ArrayList<>();
        users = new ArrayList<>();

        driver = new User();
        driver.setId((long) 1);
        driver.setPassword("password");
        driver.setEmail("email@driver.com");
        driver.setName("name");
        driver.setLastName("lastName");
        driver.setPhone("123456789");
        drivers.add(driver);
        users.add(driver);

        Role adminRole = new Role();
        adminRole.setId((long) 1);
        adminRole.setRole("ROLE_ADMIN");

        driverRole = new Role();
        driverRole.setId((long) 2);
        driverRole.setRole("ROLE_DRIVER");

        Role logisticianRole = new Role();
        logisticianRole.setId((long) 3);
        logisticianRole.setRole("ROLE_LOGISTICIAN");

        logistician = new User();
        logistician.setId((long) 2);
        logisticians.add(logistician);
        users.add(logistician);


        Mockito.when(roleRepository.findOne((long) 2)).thenReturn(driverRole);
        Mockito.when(roleRepository.findOne((long) 3)).thenReturn(logisticianRole);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(roleRepository.findByRole("ROLE_LOGISTICIAN")).thenReturn(logisticianRole);
        Mockito.when(roleRepository.findByRole("ROLE_DRIVER")).thenReturn(driverRole);
    }

    @Test
    public void saveUser(){
        userService.saveUser(driver,(long) 2);
        assertThat(driver.getActive()).isEqualTo(1);
        HashSet<Role> role = new HashSet<Role>(Arrays.asList(driverRole));
        assertThat(driver.getRoles()).isEqualTo(role);
    }

    @Test
    public void listLogisticians(){
        userService.saveUser(driver,(long)2);
        userService.saveUser(logistician,(long) 3);
        assertThat(userService.listLogisticians()).isEqualTo(logisticians);
    }

    @Test
    public void listDrivers(){
        userService.saveUser(driver,(long)2);
        userService.saveUser(logistician,(long) 3);
        assertThat(userService.listDrivers()).isEqualTo(drivers);
    }

}
