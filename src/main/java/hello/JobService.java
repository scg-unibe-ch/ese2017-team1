package hello;
import java.util.List;

/**
 * This class was created to save a job. At the moment we are not using it.
 * Instead we are using the ProductOrder classes and the FormController for it.
 */

public interface JobService {

    List<ProductOrder> listAllJobs();

    ProductOrder getProductOrderId(Integer id);
}
