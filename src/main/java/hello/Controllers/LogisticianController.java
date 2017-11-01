package hello.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by angelakeller on 01.11.17.
 */
@Controller
public class LogisticianController extends WebMvcConfigurerAdapter {

    @RequestMapping("/logistician")
    public String logistician() {
        return "logistician";
    }
}
