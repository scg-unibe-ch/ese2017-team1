package hello.Login;

/**
 * Created by angelakeller on 26.10.17.
 */
public class User {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private int phone;

    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getFirstname() {return firstname;}
    public String getLastname() {return lastname;}
    public String getEmail() {return email;}
    public String getAddress() {return address;}
    public int getPhone() {return phone;}

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
}
