package hello.Controllers;

import hello.Services.*;
import hello.ProductOrders.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Controller for the orderForm and the showJobs
 */
@Controller
public class ProductOrderController extends WebMvcConfigurerAdapter {

    @Autowired
    private ProductOrderService productOrderService;
    @Autowired
    private ProductService productService;


    @RequestMapping("/showJobs")
    public String listJobs(@ModelAttribute("productOrder") ProductOrder productOrder, Model model) {
        model.addAttribute("products", productOrderService.listNotAccProductOrders());
        model.addAttribute("accProducts", productOrderService.listAccProductOrders());
        return "showJobs";
    }

    @GetMapping("/orderForm")
    public String productForm(Model model) {

        ProductOrder productOrder = new ProductOrder();
        productOrder = productOrderService.checkId(productOrder);

        model.addAttribute("productOrder", productOrder);
        model.addAttribute("products", productService.listAllProducts());
        return "orderForm";
    }

    @PostMapping("/orderForm")
    public String productSubmit(@ModelAttribute("productOrder") ProductOrder productOrder, @RequestParam("id") Long clientId, @RequestParam("productId") Long productId) {
        productOrderService.assignClient(productOrder, clientId);
        productOrderService.assignProduct(productOrder, productId);

        return "addedProduct";
    }

    @RequestMapping("/deleteJobCheck/{prodId}")
    public String checkDeleteJob(@ModelAttribute("productOrder") ProductOrder productOrder, @PathVariable("prodId") Long prodId, Model model) {
        model.addAttribute("prod", productOrderService.findProductOrder(prodId));
        return "deleteJobCheck";
    }

    @RequestMapping("/deleteJob/{prodId}")
    public String deleteJob(@ModelAttribute("productOrder") ProductOrder productOrder, @PathVariable("prodId") Long prodId, Model model) {
        ProductOrder prod = productOrderService.findProductOrder(prodId);
        model.addAttribute("prod", prod);
        productOrderService.delete(prod);
        return "deleteJob";
    }

}
