package hello.Login;


import java.util.Set;

/**
 * Created by angelakeller on 05.11.17.
 */
public interface UserInterface {

    public int getId();
    public void setId(int id);

    public String getPassword();
    public void setPassword(String password);

    public String getName();
    public void setName(String name);

    public String getLastName();
    public void setLastName(String lastName);

    public String getEmail();
    public void setEmail(String email);

    public Set<Role> getRoles();
    public void setRoles(Set<Role> roles);
}
