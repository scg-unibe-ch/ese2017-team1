package hello.Repositories;

import hello.Product.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by angelakeller on 05.11.17.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {


}
