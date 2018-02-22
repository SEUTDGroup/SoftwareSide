package com.soft_eng_project.heimdallr;

import java.util.Date;

/**
 * Created by Yadiel on 2/22/2018.
 */

public class AccountInfoController {

    private static Date currentTime;
    private DatabaseManager dbManager;

    //AccountInfoController Constructor
    public AccountInfoController()
    {
        //Get DatabaseManager singleton instance
        dbManager = DatabaseManager.getDBManager();
    }

    //Creates new User object and saves it in database
    public void createUser(String firstName, String lastName, String userName, String password, String email)
    {
        dbManager.saveUser(new User(firstName, lastName, userName, password, email));
    }

    //Updates user information in database
    public void updateInfo(String firstName, String lastName, String userName, String password, String email)
    {
        User user = dbManager.getUser(userName);
        user.updateInfo(firstName, lastName, password, email);
        dbManager.updateUser(user);
    }

    public void updateTime() {}

    //Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT
    public long getCurrentTime()
    {
        return currentTime.getTime();
    }


}
