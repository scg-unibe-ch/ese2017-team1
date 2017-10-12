package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;


@Controller
public class WebController extends WebMvcConfigurerAdapter {

    @Autowired
    private JobRepository jobRepository;
    private Job job;

    @RequestMapping(value="/")
    public String index(){
        return "index";
    }

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

    /**
     * Some information taken from the Spring Guide "Handling Form Submission"
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


    @GetMapping("/form")
    public String jobForm(Model model) {
        model.addAttribute("job", new Job());
        return "form";
    }

    @PostMapping("/form")
    public String jobSubmit(@ModelAttribute Job job) {
        return "newJob";
    }



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
