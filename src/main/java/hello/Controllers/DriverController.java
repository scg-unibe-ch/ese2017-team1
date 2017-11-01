package hello.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by angelakeller on 01.11.17.
 */
@Controller
public class DriverController extends WebMvcConfigurerAdapter {

    @RequestMapping("/driver")
    public String driver() {
        return "driver";
    }


}
