package hello.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {


    /**
     * First page is shown
     * @return index template
     */
    @RequestMapping(value="/")
    public String index(){
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