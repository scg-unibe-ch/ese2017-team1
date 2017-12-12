package hello.Controllers;

import hello.Repositories.ClientRepository;
import hello.Services.ClientService;
import hello.Services.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
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

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ClientController clientController;


    @TestConfiguration
    static class ClientControllerTestContextConfiguration {
        @Bean
        public ClientController clientController() {
            return new ClientController();
        }
    }

    @Before
    public void setup() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(new ClientController())
                .setViewResolvers(viewResolver)
                .build();

        //mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void getSelectClient() throws Exception {
        this.mockMvc.perform(get("/selectClient"))
                .andExpect(status().isOk())
                .andExpect(view().name("selectClient"));
    }

    @Test
    public void clientForm() throws Exception {
        this.mockMvc.perform(get("/clientForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("clientForm"));
    }

    /*@Test
    public void getSearchClient() throws Exception {

        ArrayList<Client> clients = new ArrayList<>();
        Client client = new Client();
        client.setCity("City");
        client.setId((long) 1);
        client.setLand("Land");
        client.setStreet("Street");
        client.setPhone("063 255 25 25");
        client.setEmail("c@email.com");
        client.setName("Name");
        clients.add(client);

        this.clientController.clientService = new ClientService();
        this.clientController.clientService.clientRepository = clientRepository;

        given(this.clientController.clientService.clientRepository.findAll())
                .willReturn(clients);


        given(this.clientController.clientService.listClients())
                .willReturn(clients);


        this.mockMvc.perform(get("/searchClient"))
                .andExpect(status().isOk())
                .andExpect(view().name("searchClient"));
    }*/
}

