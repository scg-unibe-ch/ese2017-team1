package hello.Repositories;

import hello.Trucks.TractorMachine;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by angelakeller on 10.11.17.
 */
public interface TruckRepository extends CrudRepository<TractorMachine, Long> {
}
