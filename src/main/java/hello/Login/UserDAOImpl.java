package hello.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by angelakeller on 26.10.17.
 */
/*
public class UserDAOImpl implements UserDAO {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public UserInfo findUserInfo(String username) {
        String query = "select * from user where username = :username";

        try{
            UserInfo userinfo = namedParameterJdbcTemplate.queryForObject(query, getSqlParameterByModel(username, ""), new UserInfoMapper());
            return userinfo;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    private SqlParameterSource getSqlParameterByModel(String username, String password) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", username);
        parameterSource.addValue("password", password);

        return parameterSource;
    }

    private static final class UserInfoMapper implements RowMapper<UserInfo> {

        public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

            String username = rs.getString("username");
            String passowrd = rs.getString("password");

            return new UserInfo(username, passowrd);
        }
    }

    @Override
    public List<String> getUserRoles(String username) {
        return null;
    }
}*/