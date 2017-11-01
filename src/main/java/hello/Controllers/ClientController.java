package hello.Controllers;

import hello.Client.Client;
import hello.Client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This is the controller for the client
 */
@Controller
public class ClientController extends WebMvcConfigurerAdapter {

    @Autowired
    private ClientRepository clientRepository;
    private Client client;

    @RequestMapping(value="/selectClient")
    public String selectClient(){
        return "selectClient";
    }

    @RequestMapping(value="/searchClient")
    public String searchClient(){
        return "searchClient";
    }

    @GetMapping("/clientForm")
    public String clientForm(Model model) {
        model.addAttribute("client", new Client());
        return "clientForm";
    }

    @PostMapping("/clientForm")
    public String clientSubmit(@ModelAttribute("client") Client client) {
        this.clientRepository.save(client);
        return "addedClient";
    }



}
