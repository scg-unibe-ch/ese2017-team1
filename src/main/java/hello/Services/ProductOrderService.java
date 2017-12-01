package hello.Services;

import hello.Client.Client;
import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.ProductOrderRepository;
import hello.Tour.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service responsible for handling requests concerning productOrders
 */
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


    /**
     * @return Iterable of all ProductOrders in productOrderRepository
     */
    public Iterable<ProductOrder> listAllProductOrders(){ return this.productOrderRepository.findAll(); }

    /**
     * @return Iterable of all accepted ProductOrders
     */
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

    /**
     * @return Iterable of all not accepted ProductOrders
     */
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

    /**
     * @return ArrayList of all ProductOrders that are not accepted and not assigned to a tour yet
     */
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

    /**
     * @param tourId Id of tour we are looking at
     * @return ArrayList of all ProductOrders in that tour
     */
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

    /**
     * returns productOrder with Id prodId
     */
    public ProductOrder findProductOrder(Long prodId) { return productOrderRepository.findOne(prodId); }

    /**
     * saves productOrder product in productOrderRepository
     */
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

    /**
     * sets the Client with Id clientId as client of the ProductOrder productOrder
     */
    public void assignClient(ProductOrder productOrder, Long clientId) {
        Client client = clientService.findClient(clientId);
        productOrder.setClient(client);
    }

    /**
     * sets the Product with Id productId as the product of productOrder
     */
    public void assignProduct(ProductOrder productOrder, Long productId) {
        Product product = productService.findProduct(productId);
        productOrder.setProduct(product);
        productOrder.setAccOrRej("keine Angabe");
        productOrder = checkId(productOrder);
        save(productOrder);
    }

    /**
     * deletes prod in productOrderRepository
     */
    public void delete(ProductOrder prod) { this.productOrderRepository.delete(prod); }

    /**
     * Gets the addresses of the clients in the productOrders of one Tour with Id tourId an makes an ArrayList of
     * Strings out of it. Used to display the map that shows where the driver has to go to in that tour.
     * @param tourId Tour that needs to be displayed
     */
    public ArrayList<String> addresses(Long tourId) {
        ArrayList<String> addresses = new ArrayList<>();

        for(ProductOrder productOrder : listTourProductOrders(tourId)){
            addresses.add(productOrder.getClient().getStreet() + "," + productOrder.getClient().getCity()
                     + "," + productOrder.getClient().getLand());
        }
        return addresses;
    }

    /**
     * Sets the ProductOrder with Id productOrderId to accepted or rejected
     * @param accOrRej String: Either "accepted" or "rejected"
     */
    public void accOrRej(Long productOrderId, String accOrRej) {
        // find ProductOrder by ID from param productOrderId given to the method
        ProductOrder productOrder = findProductOrder(productOrderId);
        // sets status AccOrRej to the param accOrRej given to the method
        productOrder.setAccOrRej(accOrRej);
        save(productOrder);
    }
}
