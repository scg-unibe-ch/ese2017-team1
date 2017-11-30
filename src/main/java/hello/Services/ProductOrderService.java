package hello.Services;

import hello.ProductOrders.ProductOrder;
import hello.Repositories.ProductOrderRepository;
import hello.Tour.Tour;
import hello.Trucks.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("productOrderService")
public class ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private TourService tourService;


    public Iterable<ProductOrder> listAllProductOrders(){ return this.productOrderRepository.findAll(); }

    public ArrayList<ProductOrder> listNotAccNoTourProductOrders() {
        ArrayList<ProductOrder> products = new ArrayList<>();

        // Checks if a productOrder is already "akzeptiert" and assigned to a tour if not it's added to products
        for(ProductOrder productOrder : listAllProductOrders()){
            if(productOrder.getAccOrRej().equalsIgnoreCase("akzeptiert")) {}
            else {
                if(productOrder.getTour() == null){
                    products.add(productOrder);
                }
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
}
