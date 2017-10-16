package hello;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * An implementation of the ContactDAO interface.
 * @author www.codejava.net
 *
 */
public class JobDAOImpl implements JobDAO {

    private JdbcTemplate jdbcTemplate;

    public JobDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveOrUpdate(Job job) {
    }

    @Override
    public void delete(int jobId) {
        String sql = "DELETE FROM job WHERE job_id=?";
        jdbcTemplate.update(sql, jobId);
    }

    @Override
    public List<Job> list() {
        String sql = "SELECT * FROM job";
        List<Job> listJob = jdbcTemplate.query(sql, new RowMapper<Job>() {

            @Override
            public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
                Job aJob = new Job();
                aJob.setId(rs.getInt("contact_id"));
                aJob.setClient(rs.getString("client"));
                aJob.setProduct(rs.getString("product"));
                aJob.setAddress(rs.getString("address"));
                aJob.setEmail(rs.getString("email"));

                return aJob;
            }

        });

        return listJob;
    }

    @Override
    public Job get(int jobId) {
        String sql = "SELECT * FROM contact WHERE contact_id=" + jobId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Job>() {

            @Override
            public Job extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Job job = new Job();
                    job.setId(rs.getInt("contact_id"));
                    job.setClient(rs.getString("client"));
                    job.setProduct(rs.getString("product"));
                    job.setAddress(rs.getString("address"));
                    job.setEmail(rs.getString("email"));

                    return job;
                }

                return null;
            }

        });
    }

}