package hello.Controllers;

import hello.Client.Client;
import hello.Client.ClientRepository;
import hello.ProductOrders.ProductOrder;
import hello.ProductOrders.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Controller
public class FormController extends WebMvcConfigurerAdapter {

    /**
     * to add infos in database (don't know how yet)
     * let Spring inject an instance of the JobDAOimplementation into this controller automatically
     */
    @Autowired
    private ClientRepository clientRepository;
    private Client client;
    @Autowired
    private ProductOrderRepository productOrderRepository;
    private ProductOrder productOrder;


    /**
     * First page is shown
     * @return index template
     */
    @RequestMapping(value="/")
    public String index(){
        return "index";
    }

    @RequestMapping(value="/selectClient")
    public String selectClient(){
        return "selectClient";
    }

    @RequestMapping(value="/searchClient")
    public String searchClient(){
        return "searchClient";
    }
    
    /**
     * Some information taken from the Spring Guide "Handling Form Submission"
     * concerning the methods jobForm() and jobSubmit():
     * (https://spring.io/guides/gs/handling-form-submission/)
     *
     * Mapping annotations allows us to map HTTP requests to specific controller methods.
     * The two methods in this controller are both mapped to /greeting.
     * @RequestMapping: by default maps all HTTP operations, such as GET, POST, and so forth.
     * jobForm() is specifically mapped to GET using @GetMapping.
     * jobSubmit() is mapped to POST with @PostMapping.
     * This mapping allows the controller to differentiate the requests to the /greeting endpoint.
     *
     * jobForm() uses a Model object to expose a new Job to the view template.
     * The Job object in the following code contains fields such as id and content that correspond to the form fields in the job view,
     * and will be used to capture the information from the form.
     */


    @GetMapping("/clientForm")
    public String clientForm(Model model) {
        model.addAttribute("client", new Client());
        return "clientForm";

    }

    @PostMapping("/clientForm")
    public String clientSubmit(@ModelAttribute("client") Client client) {
        this.clientRepository.save(client);
        return "addedClient";
    }

    @GetMapping("/orderForm")
    public String productForm(Model model) {
        model.addAttribute("productOrder", new ProductOrder());
        return "orderForm";

    }


    @PostMapping("/orderForm")
    public String productSubmit(@ModelAttribute("productOrder") ProductOrder productOrder) {
        this.productOrderRepository.save(productOrder);
        return "addedProduct";
    }

    /**
     * Working on displaying data from database. Does not work yet
     * @param productOrder
     * @return
     */
    @RequestMapping("/showJobs")
    public String listJobs(@ModelAttribute("productOrder") ProductOrder productOrder, Model model) {

        Iterable<ProductOrder> products = this.productOrderRepository.findAll();
        model.addAttribute("products", products);
        return "showJobs";
    }

}
