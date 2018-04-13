package com.soft_eng_project.heimdallr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yadiel on 2/2/2018.
 */

public class LoginActivity extends Activity
{

    private DatabaseManager dbManager;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
      //  getActionBar().setTitle(R.string.Login_Title);
        setContentView(R.layout.activity_login);


        Button submit_button = findViewById(R.id.loginSubmitButton);
        submit_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                submitInfo(v);
            }
        });

        TextView register = findViewById(R.id.LoginRegisterClickableText);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewAccount();
            }
        });

        dbManager = DatabaseManager.getDBManager(this);
    }

    protected void registerNewAccount()
    {
        Intent launchGallery = new Intent(this, RegisterActivity.class);
        launchGallery.putExtra("Register Account", "Message");
        startActivity(launchGallery);
    }


    protected void submitInfo(View v)
    {
        //Attempt to login to account
        EditText userNameText = findViewById(R.id.loginUsernameText);
        EditText passwordText = findViewById(R.id.loginPasswordText);
        User u;

        //For Debugging and Demo
        boolean logged_in = false;

        for(int i = 0; i < dbManager.userArray.length(); i++)
        {
            try
            {
                JSONObject user = dbManager.userArray.getJSONObject(i);
                if (userNameText.getText().toString().equals(user.getString("username")) && passwordText.getText().toString().equals(user.getString("password")))
                {
                    logged_in = true;
                    u = new User(user.getString("first name"), user.getString("last name"), user.getString("username"), user.getString("password"), user.getString("email"));
                    dbManager.saveUser(u);
                    dbManager.objectIndex = i;
                    break;
                }
            }
            catch (JSONException e)
            {

            }
        }



/*
        if(userNameText.getText().toString().equals("SEGROUP") && passwordText.getText().toString().equals("12345"))
            logged_in = true;
*/


       /* boolean logged_in = login.submit(userNameText.getText().toString(),
                                        passwordText.getText().toString()); */

        //If logging is sucessful transition to GalleryActivity
        if(logged_in)
        {
            Intent launchGallery = new Intent(this, GalleryActivity.class);
            launchGallery.putExtra("Login Successful", "Message");
            startActivity(launchGallery);
        }

        //If the login is unsuccessful display a snackbar message
        Snackbar.make(v, "Error: Username or Password is incorrect", Snackbar.LENGTH_SHORT)
                .setAction("Action", null)
                .show();

    }

    //Returns true if password verification succeeded and false otherwise
    public boolean submit(String firstName, String lastName, String userName, String password, String email)
    {
        User user = dbManager.getUser();
        return user != null && user.verifyPassword(password);
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
