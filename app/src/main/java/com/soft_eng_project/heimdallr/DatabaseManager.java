package com.soft_eng_project.heimdallr;

/**
 * Created by Yadiel on 2/22/2018.
 */

public class DatabaseManager {

    private static DatabaseManager dbManager;

    //Default DatabaseManager constructor
    private DatabaseManager(){}

    //Get DatabaseManger at runtime
    public static DatabaseManager getDBManager()
    {
        if (dbManager == null)
        {
            dbManager = new DatabaseManager();
        }
        return dbManager;
    }

    //Prevent DatabaseManger instance from being cloned
    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    //Returns a User object from the database
    public User getUser(String userName)
    {
        //do stuff with database
        return null;
    }

    //Saves User object information in database
    public void saveUser(User user)
    {
        //do stuff with database
    }

    //Retrieves User information from database and updates it
    public void updateUser(User user)
    {
        //do stuff with database
    }

    //Checks if a username already exists in the database
    public boolean availableUser(String userName)
    {
        return getUser(userName) == null;
    }

}
