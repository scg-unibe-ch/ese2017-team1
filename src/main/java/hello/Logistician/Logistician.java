package hello.Logistician;

import hello.Login.UserInterface;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by angelakeller on 01.11.17.
 */
@Entity
public class Logistician{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public Integer getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getEmail() {return email;}

    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setEmail(String email) {this.email = email;}
}
