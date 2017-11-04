package hello.Repositories;

/**
 * Created by angelakeller on 17.10.17.
 */

import hello.ProductOrders.ProductOrder;
import org.springframework.data.repository.CrudRepository;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {
}
