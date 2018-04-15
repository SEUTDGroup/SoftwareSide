package com.soft_eng_project.heimdallr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

public class PictureActivity extends AppCompatActivity
{
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_picture);



        dbManager = DatabaseManager.getDBManager(this);
        ImageView currentImage = findViewById(R.id.currentImage);
        currentImage.setImageDrawable(dbManager.currentImage.getDrawable());

    }


}
