package hello;
import java.util.List;

public interface jobService {

    List<Job> listAllJobs();

    ProductOrder getProductOrderId(Integer id);
}
