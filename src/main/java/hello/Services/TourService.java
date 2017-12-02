package hello.Services;

import hello.ProductOrders.ProductOrder;
import hello.Repositories.TourRepository;
import hello.Tour.Tour;
import hello.Trucks.Trailer;
import hello.Trucks.Vehicle;
import hello.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service responsible for handling requests concerning tours
 */
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


    /**
     * @return Iterable of all Tours in tourRepository
     */
    public Iterable<Tour> listAllTours(){return this.tourRepository.findAll();}

    /**
     * returns an ArrayList of Tours that are not yet finished
     */
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

    /**
     * returns an ArrayList of Tours where user is the Driver
     */
    public ArrayList<Tour> listDriverTours(User user){
        ArrayList<Tour> tours = new ArrayList<>();

        // Checks if a tour has driver as its driver
        for (Tour tour1 : listTours()) {
            if (tour1.getDriver().getId() == user.getId()) {
                tours.add(tour1);
            }
        }
        return tours;
    }

    /**
     * returns an ArrayList of Tours which are not complete, so they have no driver, no vehicle or no trailer assigned
     */
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

    /**
     * returns an ArrayList of Tours that are already finished
     */
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

    /**
     * saves tour in tourRepository
     */
    public void save(Tour tour) {this.tourRepository.save(tour);}

    /**
     * returns tour with Id tourId
     */
    public Tour findTour(Long tourId) { return this.tourRepository.findOne(tourId); }

    /**
     * updates number of free Palettes of tour
     */
    public void freePalettes(Tour tour){
        Integer palettes = 0;
        if(tour.getVehicle() != null){
            palettes += tour.getVehicle().getPalettesAmount();
        }
        if(tour.getTrailer() != null){
            palettes += tour.getTrailer().getPalettesAmount();
        }
        tour.setFreePalettes(palettes);
        save(tour);
    }

    /**
     * Assigns a Vehicle to a Tour
     * @param tourId Id of Tour that the Vehicle is assigned to
     * @param vehId Id of Vehicle that is assigned to a Tour
     */
    public void assignVehicle(Long tourId, Long vehId) {
        assert tourId != null && vehId != null;
        assert vehicleService.findVehicle(vehId).getFree() >= 1;

        Tour tour = findTour(tourId);
        Vehicle vehicle = vehicleService.findVehicle(vehId);

        // one more vehicle of that type is now used, so there's one less in vehicle.free
        vehicle.setFree(vehicle.getFree() - 1);
        tour.setVehicle(vehicle);

        freePalettes(tour);
        vehicleService.save(vehicle);
    }

    public void assignTrailer(Long tourId, Long trailerId) {
        assert tourId != null && trailerId != null;
        assert trailerService.findTrailer(trailerId).getFree() >= 1;

        Tour tour = findTour(tourId);
        Trailer trailer = trailerService.findTrailer(trailerId);

        // one more trailer of that type is now used, so there's one less in trailer.free
        trailer.setFree(trailer.getFree() - 1);
        tour.setTrailer(trailer);

        freePalettes(tour);
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

    public void cleanTour(Long tourId){
        for(ProductOrder product : productOrderService.listTourProductOrders(tourId)){
            product.setTour(null);
            productOrderService.save(product);
        }

        Tour tour = findTour(tourId);
        Trailer trailer = tour.getTrailer();
        trailer.setFree(trailer.getFree()+1);
        trailerService.save(trailer);
        Vehicle vehicle = tour.getVehicle();
        vehicle.setFree(vehicle.getFree()+1);
        vehicleService.save(vehicle);
    }

    public void deleteTour(Long tourId) {
        cleanTour(tourId);
        this.tourRepository.delete(findTour(tourId));
    }

    public void finishTour(Long tourId) {
        cleanTour(tourId);
        Tour tour = findTour(tourId);
        // sets tour status to finished and saves it
        tour.setFinished(1);
        this.tourRepository.save(tour);
    }
}
