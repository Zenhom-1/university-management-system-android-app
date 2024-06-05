package com.example.studentasu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class Courses extends AppCompatActivity {

    WebView webview1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));


        webview1 = findViewById(R.id.webview);
        webview1.loadUrl("https://science.asu.edu.eg/ar/events");
    }

    public void add(View v)
    {
        startActivity(new Intent(Courses.this, AddCourses.class));
    }

    public void display(View v)
    {
        startActivity(new Intent(Courses.this, displayCourses.class));
    }
}