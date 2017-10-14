package hello;

import java.util.List;

/**
 * Defines DAO operations for the contact model.
 * @author www.codejava.net
 *
 */
public interface JobDAO {

    public void saveOrUpdate(Job job);
    public void delete(int contactId);
    public Job get(int contactId);
    public List<Job> list();
}

