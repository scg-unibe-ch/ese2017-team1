package hello.Services;

import hello.ProductOrders.ProductOrder;
import hello.Repositories.TourRepository;
import hello.Tour.Tour;
import hello.Trucks.Trailer;
import hello.Trucks.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("tourService")
public class TourService {

    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private TrailerService trailerService;
    @Autowired
    private ProductOrderService productOrderService;


    public Iterable<Tour> listAllTours(){return this.tourRepository.findAll();}

    public ArrayList<Tour> listTours(){
        ArrayList<Tour> tours = new ArrayList<>();

        // Checks if a tour is complete and if yes, it's assigned to tours
        for (Tour tour1 : listAllTours()) {
            if (tour1.getFinished() == null) {
                if (tour1.getDriver() != null && tour1.getVehicle() != null && tour1.getTrailer() != null) {
                    tours.add(tour1);
                }
            }
        }
        return tours;
    }

    public ArrayList<Tour> listIncompTours(){
        ArrayList<Tour> incompTours = new ArrayList<>();

        // Checks if a tour is complete if not it assigns it to incompTours
        for (Tour tour1 : listAllTours()) {
            if (tour1.getFinished() == null) {
                if (tour1.getDriver() == null || tour1.getVehicle() == null || tour1.getTrailer() == null) {
                    incompTours.add(tour1);
                }
            }
        }
        return incompTours;
    }

    public ArrayList<Tour> listFinishedTours(){
        ArrayList<Tour> tours = new ArrayList<>();

        // Checks if a tour is finished and adds the finished tours to "tours"
        for (Tour tour1 : listAllTours()) {
            if (tour1.getFinished() != null) {
                tours.add(tour1);
            }
        }
        return tours;
    }

    public void save(Tour tour) {this.tourRepository.save(tour);}

    public Tour findTour(Long tourId) { return this.tourRepository.findOne(tourId); }


    public void assignVehicle(Long tourId, Long vehId) {
        Tour tour = findTour(tourId);
        Vehicle vehicle = vehicleService.findVehicle(vehId);
        vehicle.setFree(vehicle.getFree() - 1);
        tour.setVehicle(vehicle);

        Integer palettes = tour.getVehicle().getPalettesAmount() + tour.getTrailer().getPalettesAmount();
        tour.setFreePalettes(palettes);

        save(tour);
        vehicleService.save(vehicle);
    }

    public void assignTrailer(Long tourId, Long trailerId) {
        Tour tour = findTour(tourId);
        Trailer trailer = trailerService.findTrailer(trailerId);
        trailer.setFree(trailer.getFree() - 1);
        tour.setTrailer(trailer);

        Integer palettes = tour.getVehicle().getPalettesAmount() + tour.getTrailer().getPalettesAmount();
        tour.setFreePalettes(palettes);

        save(tour);
        trailerService.save(trailer);
    }

    public void addProduct(Long tourId, Long prodId, Long add) {
        Tour tour = findTour(tourId);
        ProductOrder product = productOrderService.findProductOrder(prodId);
        if(add==1){
            if(product.getTour()==null){
                Integer free = tour.getFreePalettes()-(product.getProduct().getPalettes()*Integer.parseInt(product.getAmount()));
                if(free >= 0){
                    product.setTour(tour);
                    tour.setFreePalettes(free);
                }
            }
        }
        if(add==(-1)){
            if(product.getTour()!=null){
                if(product.getTour().getId()==tourId){
                    product.setTour(null);
                    tour.setFreePalettes(tour.getFreePalettes()+(product.getProduct().getPalettes()*Integer.parseInt(product.getAmount())));
                }
            }
        }
        productOrderService.save(product);
        save(tour);
    }
}
