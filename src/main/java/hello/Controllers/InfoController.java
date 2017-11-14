package hello.Controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class InfoController {

    @RequestMapping("/info/user")
    @ResponseBody
    public Map<String, Object> infoUser(){
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("currentUser", SecurityContextHolder.getContext().getAuthentication().getName());

        SimpleDateFormat formatTanggal = new SimpleDateFormat("dd MM yyyy");
        info.put("jamLogin", formatTanggal.format(new Date()));

        return info;

    }

}
