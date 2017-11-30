package hello.Services;

import hello.Repositories.TrailerRepository;
import hello.Repositories.VehicleRepository;
import hello.Trucks.Trailer;
import hello.Trucks.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("trailerService")
public class TrailerService {

    @Autowired
    private TrailerRepository trailerRepository;


    public Iterable<Trailer> listAllTrailers(){ return this.trailerRepository.findAll(); }

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

    public Trailer findTrailer(Long trailerId) { return trailerRepository.findOne(trailerId); }

    public void save(Trailer trailer) { trailerRepository.save(trailer); }
}