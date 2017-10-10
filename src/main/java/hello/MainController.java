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

    @RequestMapping("/logistiker")
    public String logistiker() {
        return "logistiker";
    }

    @RequestMapping("/jobs")
    public String jobs() {
        return "jobs";
    }

    @RequestMapping("/newJob")
    public String newJob() {
        return "newJob";
    }

    @RequestMapping("/fahrer")
    public String fahrer() {
        return "fahrer";
    }

    // just to test whatever we like
    @RequestMapping("/bootStrap")
    public String bootStrap(){return "bootStrap";}


}