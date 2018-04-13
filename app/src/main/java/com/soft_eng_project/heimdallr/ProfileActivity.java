package com.soft_eng_project.heimdallr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static Date currentTime;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dbManager = DatabaseManager.getDBManager(this);

        EditText userNameEditText = findViewById(R.id.userNameEditText);
        EditText firstNameEditText = findViewById(R.id.firstNameEditText);
        EditText lastNameEditText = findViewById(R.id.lastNameEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);

        userNameEditText.setText(dbManager.getUser().getUserName());
        firstNameEditText.setText(dbManager.getUser().getFirstName());
        lastNameEditText.setText(dbManager.getUser().getLastName());
        emailEditText.setText(dbManager.getUser().getEmail());

        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v)
            {
            editButton(v);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery)
        {
            Intent newActivity = new Intent(this, GalleryActivity.class);
            startActivity(newActivity);
        }
        else if (id == R.id.nav_stream)
        {
            Intent newActivity = new Intent(this, LivestreamActivity.class);
            startActivity(newActivity);
        }
        else if (id == R.id.nav_profile)
        {
            Intent newActivity = new Intent(this, ProfileActivity.class);
            startActivity(newActivity);
        }
        else if (id == R.id.nav_settings)
        {
            Intent newActivity = new Intent(this, SettingsActivity.class);
            startActivity(newActivity);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    protected void editButton(View v)
    {
        EditText userNameEditText = findViewById(R.id.userNameEditText);
        EditText firstNameEditText = findViewById(R.id.firstNameEditText);
        EditText lastNameEditText = findViewById(R.id.lastNameEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);

        SharedPreferences.Editor editor;
        dbManager.userFile = PreferenceManager.getDefaultSharedPreferences(this);
        editor = dbManager.userFile.edit();

        try
        {
            JSONObject user = dbManager.userArray.getJSONObject(dbManager.objectIndex);
            user.put("username", userNameEditText.getText().toString());
            user.put("first name", firstNameEditText.getText().toString());
            user.put("last name", lastNameEditText.getText().toString());
            user.put("email", emailEditText.getText().toString());

            editor.putString("Users", dbManager.userArray.toString());
            editor.commit();

            updateInfo(firstNameEditText.getText().toString(), lastNameEditText.getText().toString(), userNameEditText.getText().toString(), user.getString("password"), emailEditText.getText().toString());
        }
        catch(JSONException e)
        {

        }



        Snackbar.make(v, "Profile Edited", Snackbar.LENGTH_SHORT)
                .setAction("Action", null)
                .show();

    }



    //Creates new User object and saves it in database
    public void createUser(String firstName, String lastName, String userName, String password, String email)
    {
        dbManager.saveUser(new User(firstName, lastName, userName, password, email));
    }

    //Updates user information in database
    public void updateInfo(String firstName, String lastName, String userName, String password, String email)
    {
        User user = dbManager.getUser();
        user.updateInfo(firstName, lastName, userName, password, email);
        dbManager.updateUser(user);
    }

    public void updateTime() {}

    //Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT
    public long getCurrentTime()
    {
        return currentTime.getTime();
    }
}
