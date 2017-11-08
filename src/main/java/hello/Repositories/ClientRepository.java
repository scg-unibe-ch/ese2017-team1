package hello.Repositories;

import hello.Client.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by angelakeller on 17.10.17.
 */
public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByName(String name);
}
