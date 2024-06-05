package com.example.studentasu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Display;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.studentasu.DB.Course;
import com.example.studentasu.DB.DB;

import java.util.List;

public class displayCourses extends AppCompatActivity {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_courses);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));

        DB db = new DB(this);
        List<Course> list = db.getCoursesByStudent();
        if(list == null || list.isEmpty())
        {
            Toast.makeText(this, "The List is null", Toast.LENGTH_SHORT).show();
        }
        else
        {
            RecyclerView recyclerView = findViewById(R.id.recycleview);
            DiplayAdabter diplayAdabter = new DiplayAdabter(this , list);
            recyclerView.setAdapter(diplayAdabter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}