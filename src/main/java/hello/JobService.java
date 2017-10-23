package hello;
import java.util.List;

public interface JobService {

    List<Job> listAllJobs();

    ProductOrder getProductOrderId(Integer id);
}
