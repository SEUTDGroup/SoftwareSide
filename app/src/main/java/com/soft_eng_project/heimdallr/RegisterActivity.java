package com.soft_eng_project.heimdallr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yadiel on 2/2/2018.
 */

public class RegisterActivity extends Activity
{
    private DatabaseManager dbManager;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //getActionBar().setTitle(R.string.Register_Title);
        setContentView(R.layout.activity_register);

        Button submit_button = findViewById(R.id.RegisterButton);
        submit_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                registerUser(v);
            }
        });

        dbManager = DatabaseManager.getDBManager(this);
    }

    protected void registerUser(View v)
    {
        //Attempt to login to account
        EditText firstNameText = findViewById(R.id.RegisterFirstNameText);
        EditText lastNameText = findViewById(R.id.RegisterLastNameText);
        EditText userNameText = findViewById(R.id.RegisterUsernameText);
        EditText passwordText = findViewById(R.id.RegisterPasswordText);
        EditText repasswordText = findViewById(R.id.RegisterRePasswordText);
        EditText emailText = findViewById(R.id.RegisterEmailText);

        SharedPreferences.Editor editor;
        dbManager.userFile = PreferenceManager.getDefaultSharedPreferences(this);
        editor = dbManager.userFile.edit();

/*
        if(userNameText.getText().toString().equals("SEGROUP"))
        {
            Snackbar.make(v, "Error: Account already exists, please log in", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null)
                    .show();
        }
*/
        if(passwordText.getText().toString().equals(repasswordText.getText().toString()))
        {
            JSONObject user = new JSONObject();


            try
            {
                user.put("username", userNameText.getText().toString());
                user.put("first name", firstNameText.getText().toString());
                user.put("last name", lastNameText.getText().toString());
                user.put("password", passwordText.getText().toString());
                user.put("email", emailText.getText().toString());

                dbManager.userArray.put(user);
                editor.putString("Users", dbManager.userArray.toString());
                editor.commit();
            }
            catch (JSONException e)
            {

            }



            //For Debugging and Demo
            Intent newActivity = new Intent(this, LoginActivity.class);
            newActivity.putExtra("Login Successful", "Message");
            startActivity(newActivity);




            /*
            if(submit(
                    "",
                    "",
                    userNameText.getText().toString(),
                    passwordText.getText().toString(),
                    emailText.getText().toString()
                    ))
            {
                Intent launchGallery = new Intent(this, GalleryActivity.class);
                launchGallery.putExtra("Login Successful", "Message");
                startActivity(launchGallery);
            }
           */
        }
        else
        {

            //If the login is unsuccessful display a snackbar message
            Snackbar.make(v, "Error: Password fields do not match", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null)
                    .show();
        }
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
        return verifyCredentials(user);
    }

    protected void onStart()
    {
        super.onStart();
    }

    protected void onResume()
    {
        super.onResume();
    }

    protected void onPause()
    {
        super.onPause();
    }

    protected void onStop()
    {
        super.onStop();
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }

}
