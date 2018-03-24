package com.soft_eng_project.heimdallr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.*;

/**
 * Created by Yadiel on 2/2/2018.
 */

public class LoginActivity extends Activity
{

    private DatabaseManager dbManager = DatabaseManager.getDBManager();

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

        //For Debugging and Demo
        boolean logged_in = false;
        if(userNameText.getText().toString().equals("SEGROUP") && passwordText.getText().toString().equals("12345"))
            logged_in = true;

       /* boolean logged_in = login.submit(userNameText.getText().toString(),
                                        passwordText.getText().toString()); */

        //If loging is sucessful transition to GalleryActivity
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
    public boolean submit(String userName, String password)
    {
        User user = dbManager.getUser(userName);
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
