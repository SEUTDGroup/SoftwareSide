package com.soft_eng_project.heimdallr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class GalleryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private DatabaseManager dbManager;
    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;
    ImageButton imageButton5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
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

        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);
        imageButton5 = findViewById(R.id.imageButton5);


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
        getMenuInflater().inflate(R.menu.gallery, menu);
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
        else if (id == R.id.nav_logOff)
        {
            Intent newActivity = new Intent(this, LoginActivity.class);
            startActivity(newActivity);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View view)
    {
        Intent newActivity = new Intent(this, PictureActivity.class);
        switch(view.getId())
        {
            case R.id.imageButton1:
                dbManager.currentImage = imageButton1;
                break;

            case R.id.imageButton2:
                dbManager.currentImage = imageButton2;
                break;

            case R.id.imageButton3:

                dbManager.currentImage = imageButton3;
                break;

            case R.id.imageButton4:
                dbManager.currentImage = imageButton4;
                break;

            case R.id.imageButton5:
                dbManager.currentImage = imageButton5;
                break;

            default:
                break;
        }
        startActivity(newActivity);
    }
}















