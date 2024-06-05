package com.example.studentasu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentasu.DB.DB;

import java.util.ArrayList;
import java.util.List;

public class AddCourses extends AppCompatActivity {
    private DB db;
    Spinner select_course_s1;
    RadioGroup group;
    Button addcoursebtn;
    String selectedCourseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        group = findViewById(R.id.radio_group);
        select_course_s1 = findViewById(R.id.select_course_s1);
        addcoursebtn = findViewById(R.id.addcoursebtn);
        db = new DB(this);

        int level = db.getStudentLevel();

        if(level == -1) {
            Toast.makeText(this, "Error in Student data: level", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Student level = " + level, Toast.LENGTH_SHORT).show();
        }

        String[] coursesSemester1 = db.getCoursesByStudentLevel(level, "one");
        String[] coursesSemester2 = db.getCoursesByStudentLevel(level, "two");

        ArrayAdapter<String> adapterSemester1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesSemester1);
        ArrayAdapter<String> adapterSemester2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesSemester2);

        select_course_s1.setAdapter(adapterSemester1);
        group.setOnCheckedChangeListener((group1, checkedId) -> {
            if(checkedId == R.id.radiosem1) {
                select_course_s1.setAdapter(adapterSemester1);
            } else {
                select_course_s1.setAdapter(adapterSemester2);
            }

        });

        select_course_s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   selectedCourseName = (String) parent.getItemAtPosition(position);
                   Toast.makeText(getApplicationContext(), "Course " + selectedCourseName + " is Selected", Toast.LENGTH_SHORT).show();
               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {
               }
           });

        addcoursebtn.setOnClickListener(v -> {
            boolean res = db.add_studentcourse(selectedCourseName);

            if(res) {
                Toast.makeText(getApplicationContext(), "Add success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Course Already Added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}