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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(DriverController.class)
public class ClientRequestTest {

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

        mockMvc = MockMvcBuilders.standaloneSetup(new ClientController())
                .setViewResolvers(viewResolver)
                .build();
    }


    @Test
    public void getDriver() throws Exception {
        this.mockMvc.perform(get("/selectClient"))
                .andExpect(status().isOk())
                .andExpect(view().name("selectClient"));
    }


}

