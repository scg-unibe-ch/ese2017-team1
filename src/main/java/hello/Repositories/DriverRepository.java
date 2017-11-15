package hello.Repositories;

import hello.Users.Driver.Driver;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by angelakeller on 04.11.17.
 */
public interface DriverRepository extends CrudRepository<Driver, Long> {
}
