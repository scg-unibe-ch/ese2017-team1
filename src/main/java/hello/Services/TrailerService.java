package hello.Services;

import hello.Repositories.TrailerRepository;
import hello.Repositories.VehicleRepository;
import hello.Trucks.Trailer;
import hello.Trucks.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service responsible for handling requests concerning trailers
 */
@Service("trailerService")
public class TrailerService {

    @Autowired
    private TrailerRepository trailerRepository;


    /**
     * @return Iterable of all Trailers in trailerRepository
     */
    public Iterable<Trailer> listAllTrailers(){ return this.trailerRepository.findAll(); }

    /**
     * returns an ArrayList of Trailers that are still available
     */
    public ArrayList<Trailer> listTrailers() {
        ArrayList<Trailer> trailers = new ArrayList<>();

        // Checks if a trailer is still available, if yes it's listed in trailers
        for (Trailer trailer : listAllTrailers()) {
            if (trailer.getFree() > 0) {
                trailers.add(trailer);
            }
        }
        return trailers;
    }

    /**
     * returns an ArrayList of Trailers that are already used (all Trailers of that type are assigned to a tour)
     */
    public ArrayList<Trailer> listUsedTrailers() {
        ArrayList<Trailer> usedTrailers = new ArrayList<>();

        // Checks if a trailer is still available, if not it's listed in usedTrailers
        for (Trailer trailer : listAllTrailers()) {
            if (trailer.getFree() <= 0) {
                usedTrailers.add(trailer);
            }
        }
        return usedTrailers;
    }

    /**
     * returns trailer with Id trailerId
     */
    public Trailer findTrailer(Long trailerId) { return trailerRepository.findOne(trailerId); }

    /**
     * saves trailer in trailerRepository
     */
    public void save(Trailer trailer) { trailerRepository.save(trailer); }
}
