import static org.assertj.core.api.Assertions.assertThat;

import hello.Client.Client;
import hello.Configuration.WebMvcConfig;
import hello.Controllers.ClientController;
import hello.Controllers.LoginController;
import hello.Repositories.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoginController.class)
public class SmokeTest {

    /**
     * Tests whether application creates controllers
     * Does not work yet, don't know why though...
     */
    @Autowired
    private LoginController controller;



    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
