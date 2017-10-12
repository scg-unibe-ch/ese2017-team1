package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/form", method = RequestMethod.GET)
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
    }
}
