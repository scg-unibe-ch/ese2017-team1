package hello.Repositories;

import hello.Users.Logistician.Logistician;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by angelakeller on 04.11.17.
 */
public interface LogisticianRepository extends CrudRepository<Logistician, Long> {
}
