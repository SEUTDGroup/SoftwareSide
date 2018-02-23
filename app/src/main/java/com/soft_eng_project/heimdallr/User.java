package com.soft_eng_project.heimdallr;

/**
 * Created by Yadiel on 2/22/2018.
 */

public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    //Default constructor
    public User() {}

    //User constructor
    public User(String firstName, String lastName, String userName, String password, String email)
    {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password  = password;
        this.email = email;
    }

    //Verify if password belongs to User
    public boolean verifyPassword(String password)
    {
        return getPassword().equals(password);
    }

    //Updates user information
    public void updateInfo(String firstName, String lastName, String password, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password  = password;
        this.email = email;
    }

    //Getter methods
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getUserName(){return userName;}
    public String getPassword(){return password;}
    public String getEmail(){return email;}


}
