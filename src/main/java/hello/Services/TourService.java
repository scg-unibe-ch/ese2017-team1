package hello.Services;

import hello.Repositories.TourRepository;
import hello.Tour.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("tourService")
public class TourService {

    @Autowired
    private TourRepository tourRepository;


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


}
