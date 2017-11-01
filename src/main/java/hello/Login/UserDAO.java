package hello.Login;

import java.util.List;

/**
 * Created by angelakeller on 26.10.17.
 */

public interface UserDAO {

    public UserInfo findUserInfo(String username);

    public List<String> getUserRoles(String username);

}
