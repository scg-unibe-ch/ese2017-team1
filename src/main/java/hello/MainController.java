package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("/logistician")
    public String logistician() {
        return "logistician";
    }


    @RequestMapping("/driver")
    public String driver() {
        return "driver";
    }

    /**
     * Displays the jobs that have to be done
     * @return template jobs
     */
    @RequestMapping("/jobs")
    public String jobs() {
        return "jobs";
    }


    // just to test whatever we like
    @RequestMapping("/bootStrap")
    public String bootStrap(){return "bootStrap";}
}