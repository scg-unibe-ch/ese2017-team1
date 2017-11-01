package hello.Controllers;

import hello.Job.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by angelakeller on 01.11.17.
 */
@Controller
public class JobController extends WebMvcConfigurerAdapter {

    private JobService jobService;

    @RequestMapping("/jobs")
    public String listProductOrders(Model model){
        model.addAttribute("listJobs", jobService.listAllJobs());
        return "listJobs";
    }



}
