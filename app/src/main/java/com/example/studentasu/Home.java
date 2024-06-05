package com.example.studentasu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity
{
    Toolbar toolbar;
    WebView webview1;

    BottomNavigationView bottomNavigationView;

//    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.holo_red_dark));


        toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);

        webview1 = findViewById(R.id.webview);
        webview1.loadUrl("https://science.asu.edu.eg/ar/");

        bottomNavigationView = findViewById(R.id.bottomnav);

        bottomNavigationView.setSelectedItemId(R.id.Home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.guide)
            {
                startActivity(new Intent(Home.this, guide.class));
            }
            else if(item.getItemId() == R.id.Courses)
            {
                startActivity(new Intent(Home.this, Courses.class));
            }
            else if(item.getItemId() == R.id.GPA)
            {
                startActivity(new Intent(Home.this, calculateGPA.class));
            }
            else if(item.getItemId() == R.id.Profile)
            {
                startActivity(new Intent(Home.this, Profile.class));
            }
            return true;
        });
    }


}