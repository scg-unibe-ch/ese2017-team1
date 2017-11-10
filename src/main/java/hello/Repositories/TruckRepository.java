package hello.Repositories;

import hello.Trucks.Truck;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by angelakeller on 10.11.17.
 */
public interface TruckRepository extends CrudRepository<Truck, Long> {
}
