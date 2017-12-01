package hello.Services;

import hello.Client.Client;
import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.ProductOrderRepository;
import hello.Tour.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("productOrderService")
public class ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private TourService tourService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;


    public Iterable<ProductOrder> listAllProductOrders(){ return this.productOrderRepository.findAll(); }

    public Iterable<ProductOrder> listAccProductOrders(){
        ArrayList<ProductOrder> products = new ArrayList<>();

        // Checks if a productOrder is already "akzeptiert" if yes it's added to products
        for(ProductOrder productOrder : listAllProductOrders()){
            if(productOrder.getAccOrRej().equalsIgnoreCase("akzeptiert")) {
                products.add(productOrder);
            }
        }
        return products;
    }

    public Iterable<ProductOrder> listNotAccProductOrders(){
        ArrayList<ProductOrder> products = new ArrayList<>();

        // Checks if a productOrder is already "akzeptiert" if not it's added to products
        for(ProductOrder productOrder : listAllProductOrders()){
            if(productOrder.getAccOrRej().equalsIgnoreCase("akzeptiert")) {}
            else {
                products.add(productOrder);
            }
        }
        return products;
    }

    public ArrayList<ProductOrder> listNotAccNoTourProductOrders() {
        ArrayList<ProductOrder> products = new ArrayList<>();

        // Checks if a productOrder is already assigned to a tour if not it's added to products
        for(ProductOrder productOrder : listNotAccProductOrders()){
            if(productOrder.getTour() == null){
                products.add(productOrder);
            }
        }
        return products;
    }

    public ArrayList<ProductOrder> listTourProductOrders(Long tourId) {
        Tour tour = tourService.findTour(tourId);
        ArrayList<ProductOrder> tourProducts = new ArrayList<>();

        // Checks if a productOrder is in this tour and if yes adds it to tourProducts
        for (ProductOrder productOrder : listAllProductOrders()){
            if(productOrder.getTour() == tour){
                tourProducts.add(productOrder);
            }
        }
        return tourProducts;
    }

    public ProductOrder findProductOrder(Long prodId) { return productOrderRepository.findOne(prodId); }

    public void save(ProductOrder product) { productOrderRepository.save(product); }

    /**
     * Sets ID of new ProductOrder equal to the highest already existing ID + 1
     * (should have done this in HTML File but it did not work)
     */
    public ProductOrder checkId(ProductOrder productOrder) {

        Long i = Long.valueOf(1);
        while(findProductOrder(i)!=null && findProductOrder(i) != productOrder){
            i++;
        }
        productOrder.setId(i);
        return productOrder;
    }

    public void assignClient(ProductOrder productOrder, Long clientId) {
        Client client = clientService.findClient(clientId);
        productOrder.setClient(client);
    }

    public void assignProduct(ProductOrder productOrder, Long productId) {
        Product product = productService.findProduct(productId);
        productOrder.setProduct(product);
        productOrder.setAccOrRej("keine Angabe");
        productOrder = checkId(productOrder);
        save(productOrder);
    }

    public void delete(ProductOrder prod) { this.productOrderRepository.delete(prod); }

    public ArrayList<String> addresses(Long tourId) {
        ArrayList<String> addresses = new ArrayList<>();

        for(ProductOrder productOrder : listAllProductOrders()){
            if(productOrder.getTour() != null){
                if(productOrder.getTour().getId() == tourId){
                    addresses.add(productOrder.getClient().getStreet() + "," + productOrder.getClient().getCity()
                            + "," + productOrder.getClient().getLand());
                }
            }
        }
        return addresses;
    }

    public void accOrRej(Long productOrderId, String accOrRej) {
        // find ProductOrder by ID from param productOrderId given to the method
        ProductOrder productOrder = findProductOrder(productOrderId);
        // sets status AccOrRej to the param accOrRej given to the method
        productOrder.setAccOrRej(accOrRej);
        save(productOrder);
    }
}
