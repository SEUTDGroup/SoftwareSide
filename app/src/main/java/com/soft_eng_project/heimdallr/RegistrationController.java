package com.soft_eng_project.heimdallr;

/**
 * Created by Yadiel on 2/22/2018.
 */

public class RegistrationController {

    private DatabaseManager dbManager;

    //RegistrationController constructor
    public RegistrationController()
    {
        //Get DatabaseManager singleton instance
        dbManager = DatabaseManager.getDBManager();
    }

    //Verifies the new User information
    public boolean verifyCredentials(User user)
    {
        if(dbManager.availableUser(user.getUserName()))
        {
            //Add more input validation here for password and email
            dbManager.saveUser(user);
            return true;
        }
        return false;
    }

    //Returns true if new User information is valid
    public boolean submit(String firstName, String lastName, String userName, String password, String email)
    {
        User user = new User(firstName, lastName, userName, password, email);
        if(verifyCredentials(user))
            return true;
        return false;
    }

}
