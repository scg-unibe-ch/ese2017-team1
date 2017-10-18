package hello;

import java.util.List;
import javax.sql.DataSource;

/**
 * Defines DAO (Data Access Object) operations for the job model.
 *
 *
 */
public interface JobDAO {

    public void saveOrUpdate(Job job);
    public void delete(int jobId);
    public Job get(int jobId);
    public List<Job> list();
}

