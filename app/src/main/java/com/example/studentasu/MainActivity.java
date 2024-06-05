package com.example.studentasu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.studentasu.DB.DB;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static int SPLASH_TIMER = 2000;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);

        db = new DB(this);
        boolean isSuccessful = db.insertCoursesData();
        if(!isSuccessful) {
            Toast.makeText(this, "Insert data failed", Toast.LENGTH_SHORT).show();
        }
    }
}