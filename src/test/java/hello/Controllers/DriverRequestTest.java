package hello.Controllers;

import hello.Services.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.security.Principal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(DriverController.class)
public class DriverRequestTest {

    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @MockBean
    private ProductOrderService productOrderService;

    @MockBean
    private DriverService driverService;

    @MockBean
    private ProductService productService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private TourService tourService;

    @MockBean
    private TrailerService trailerService;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private VehicleService vehicleService;



    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(new DriverController())
                .setViewResolvers(viewResolver)
                .build();
    }


    @Test
    public void getDriver() throws Exception {
        this.mockMvc.perform(get("/driver"))
                .andExpect(status().isOk())
                .andExpect(view().name("driver"));
    }

    // does not work since it needs id of user logged in
    /*@Test
    public void getShowToursDriver() throws Exception {
        this.mockMvc.perform(get("/showToursDriver"))
                .andExpect(status().isOk())
                .andExpect(view().name("showToursDriver"));
    }*/



    // will we probably need, does not work yet
    /*private Principal getPrincipal(String name){
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return name;
            }
        };
        return principal;
    }*/
}
