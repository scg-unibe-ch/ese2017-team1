package hello;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by angelakeller on 17.10.17.
 */
public interface ClientRepository extends CrudRepository<Client, Long> {
}
