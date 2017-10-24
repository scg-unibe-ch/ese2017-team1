package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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

    /*@RequestMapping(value="/jobs")
    public String jobs(){
        return "jobs";
    }*/

    /*@RequestMapping(value="/jobList")
    public String listJobs(){
        return "jobList";
    }*/

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
    /*@GetMapping("/form")
    public String jobForm(Model model) {
        model.addAttribute("job", new Job());
        return "form";

    }*/

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
    public String productForm(@RequestParam Long id, Model model) {
        ProductOrder order = new ProductOrder();
        //order.setClient(this.clientRepository.findOne(id));
        model.addAttribute("productOrder", productOrder);
        return "orderForm";

    }


    @PostMapping("/orderForm")
    public String productSubmit(@ModelAttribute("productOrder") ProductOrder productOrder) {
        this.productOrderRepository.save(productOrder);
        return "addedProduct";
    }



    /*public void process(
            final HttpServletRequest request, final HttpServletResponse response,
            final ServletContext servletContext) throws IOException {

        final TemplateEngine templateEngine = new TemplateEngine();

        products = (List<ProductOrder>) productOrderRepository.findAll();

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("prods", products);

        templateEngine.process("showJobs", ctx, response.getWriter());

    }*/

    /*public void process(
            final HttpServletRequest request, final HttpServletResponse response,
            final ServletContext servletContext, final ITemplateEngine templateEngine)
            throws Exception {

        products = (List<ProductOrder>) productOrderRepository.findAll();

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("prods", products);

        templateEngine.process("product/list", ctx, response.getWriter());

    }*/

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


    /**
     * Trying stuff out for displaying data from database
     * @param model
     * @return
     */
    /*@RequestMapping("/catalogue")
    public String catalogue( Model model) {

        products = (List<ProductOrder>) productOrderRepository.findAll();


        model.addAttribute("listJobs", products.get(1)); //the model object "carries" objects from the controller to the view

        return "listJobs";
    }*/







    /**
     * Method to save Job. Still have to look up stuff.
     * http://www.codejava.net/frameworks/spring/spring-mvc-with-jdbctemplate-example
     */
    /*
    @RequestMapping(value = "/orderForm", method = RequestMethod.POST)
    public ModelAndView saveJob(@ModelAttribute Job job) {
        jobDAO.saveOrUpdate(job);
        return new ModelAndView("redirect:/addedProduct");
    }*/



    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/form")
    public String showForm(Job job) {
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid Job job, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/addJob";
    }*/

    /*@RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("form", "job", new Job());
    }

    @RequestMapping(value = "/addJob", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("job")Job job,
        BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("id", job.getId());
        model.addAttribute("client", job.getClient());
        model.addAttribute("product", job.getProduct());
        model.addAttribute("address", job.getAddress());
        model.addAttribute("email", job.getEmail());
        return "addJob";
    }*/
}
