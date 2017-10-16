package hello;

import java.util.List;

/**
 * Defines DAO operations for the contact model.
 * @author www.codejava.net
 *
 */
public interface JobDAO {

    public void saveOrUpdate(Job job);
    public void delete(int jobId);
    public Job get(int jobId);
    public List<Job> list();
}

