package com.soft_eng_project.heimdallr;

/**
 * Created by Yadiel on 2/22/2018.
 */

public class LoginController {

    private DatabaseManager dbManager;

    //LoginController constructor
    public LoginController()
    {
        //Get DatabaseManager singleton instance
        dbManager = DatabaseManager.getDBManager();
    }

    //Returns true if password verification succeeded and false otherwise
    public boolean submit(String userName, String password)
    {
        User user = dbManager.getUser(userName);
        return user != null && user.verifyPassword(password);
    }

}
