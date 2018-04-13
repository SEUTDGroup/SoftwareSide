package com.soft_eng_project.heimdallr;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;

/**
 * Created by Yadiel on 2/22/2018.
 */

public class DatabaseManager {

    private static DatabaseManager dbManager;
    private User currentUser;
    public SharedPreferences userFile;
    public JSONArray userArray;
    public int objectIndex;



    //Default DatabaseManager constructor
    private DatabaseManager(Context context)
    {
        userFile = PreferenceManager.getDefaultSharedPreferences(context);
        try
        {
            userArray = new JSONArray(userFile.getString("Users", "[]"));
        }
        catch (Exception e)
        {
            userArray = new JSONArray();
        }

        objectIndex = 0;
    }

    //Get DatabaseManger at runtime
    public static DatabaseManager getDBManager(Context context)
    {
        if (dbManager == null)
        {
            dbManager = new DatabaseManager(context);
        }
        return dbManager;
    }

    //Prevent DatabaseManger instance from being cloned
    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    //Returns a User object from the database
    public User getUser()
    {
        return currentUser;
    }

    //Saves User object information in database
    public void saveUser(User user)
    {
        currentUser = user;
    }

    //Retrieves User information from database and updates it
    public void updateUser(User user)
    {
        currentUser = user;
    }

    //Checks if a username already exists in the database
    public boolean availableUser(String userName)
    {
        return getUser() == null;
    }

}
