package hello.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class MainController {


    /**
     * First page is shown
     * @return index template
     */
    @RequestMapping(value="/index")
    public String index(HttpServletRequest request){
        if (request.isUserInRole("LOGISTICIAN")) {
            return "logistician";
        }
        else if (request.isUserInRole("DRIVER")) {
            return "driver";
        }
        return "index";
    }


    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    // just to test whatever we like
    @RequestMapping("/bootStrap")
    public String bootStrap(){return "bootStrap";}
}