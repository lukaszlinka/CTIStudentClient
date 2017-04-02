package lukasz.ctistudentclient.Models;

/**
 * Created by tukan on 08.01.2017.
 */

public class UserModel {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String street;
    private String number;

    @Override public String toString(){
        return (login + " " + firstName + " " + lastName + " " + email + " " + city + " " + street + " " + number);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
