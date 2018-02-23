package com.soft_eng_project.heimdallr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    String tag = "Heimdallr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent launchLogin = new Intent(this, LoginActivity.class);
        launchLogin.putExtra("Launch Successful", "Message");
        startActivity(launchLogin);
    }

    protected void onStart()
    {
        super.onStart();
        Log.d(tag, "This is onStart()");
    }

    protected void onPause()
    {
        super.onPause();
        Log.d(tag, "This is onPause()");
    }

    protected void onStop()
    {
        super.onStop();
        Log.d(tag, "This is onStop()");
    }

    protected void onResume()
    {
        super.onResume();
        Log.d(tag, "This is onResume()");
    }

    protected void onRestart()
    {
        super.onRestart();
        Log.d(tag, "This is onRestart()");
    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.d(tag, "This is onDestroy");
    }

}
