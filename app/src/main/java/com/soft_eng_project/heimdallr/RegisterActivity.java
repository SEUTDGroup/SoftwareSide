package com.soft_eng_project.heimdallr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Yadiel on 2/2/2018.
 */

public class RegisterActivity extends Activity
{
    private RegistrationController register;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getActionBar().setTitle(R.string.Register_Title);
        setContentView(R.layout.activity_register);

        Button submit_button = findViewById(R.id.RegisterButton);
        submit_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                registerUser(v);
            }
        });
    }

    protected void registerUser(View v)
    {
        //Attempt to login to account
        EditText userNameText = findViewById(R.id.RegisterUsernameText);
        EditText passwordText = findViewById(R.id.RegisterPasswordText);
        EditText repasswordText = findViewById(R.id.RegisterRePasswordText);
        EditText emailText = findViewById(R.id.RegisterEmailText);

        if(passwordText.getText().toString().equals(repasswordText.getText().toString()))
        {

            if(register.submit(
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
        }

        //If the login is unsuccessful display a snackbar message
        Snackbar.make(v, "Error: Account already exists, please log in", Snackbar.LENGTH_SHORT)
                .setAction("Action", null)
                .show();

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
