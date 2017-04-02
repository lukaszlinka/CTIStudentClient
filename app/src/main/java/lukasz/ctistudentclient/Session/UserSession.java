package lukasz.ctistudentclient.Session;

import java.util.Date;

/**
 * Created by tukan on 12.02.2017.
 */

public class UserSession {

    private static UserSession instance;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String number;
    private String email;
    private Date birthday;

    private UserSession()
    {
//        this.firstName = "";
//        this.lastName = "";
//        this.email = "";
//        this.city = "";
//        this.street = "";
//        this.number = "";
//        this.birthday = new Date();
    }

    public static UserSession Instance()
    {
        if (instance == null)
        {
            instance = new UserSession();
        }
        return instance;
    }

    public static void ClearUser()
    {
        if (instance != null)
        {
            instance = new UserSession();
        }
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
