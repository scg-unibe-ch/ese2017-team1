package hello.Services;

import hello.LoginRole.Role;
import hello.Repositories.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests functionality of RoleService class.
 * We do not test the methods listAll() and findRole() since they only depend on the RoleRepository
 * to work and do not have a complex logic.
 */
@RunWith(SpringRunner.class)
public class RoleServiceTest {

    ArrayList<Role> roles;
    ArrayList<Role> rolesLD;

    @TestConfiguration
    static class RoleServiceTestContextConfiguration {
        @Bean
        public RoleService roleService() {
            return new RoleService();
        }
    }

    @Autowired
    private RoleService roleService;

    @MockBean
    private RoleRepository roleRepository;

    @Before
    public void setup(){
        Role logistician = new Role();
        Role driver = new Role();
        Role admin = new Role();
        roles = new ArrayList<>();
        rolesLD = new ArrayList<>();

        roles.add(logistician);
        roles.add(driver);
        roles.add(admin);

        rolesLD.add(logistician);
        rolesLD.add(driver);

        Mockito.when(roleRepository.findAll())
                .thenReturn(roles);

        Mockito.when(roleRepository.findByRole("ROLE_Admin"))
                .thenReturn(admin);

    }


    @Test
    public void list(){
        assertThat(roleService.list()).isEqualTo(rolesLD);
    }
}
