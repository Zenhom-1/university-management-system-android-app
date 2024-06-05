package com.example.studentasu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentasu.DB.DB;
import com.example.studentasu.DB.Student;

public class Profile extends AppCompatActivity {
    Button b, blog;
    TextView id_txt;
    TextView name_txt;
    TextView mobile_txt;
    TextView email_txt;
    TextView department_txt;
    TextView level_txt;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));

        setContentView(R.layout.activity_profile);
        b=findViewById(R.id.button);
        blog=findViewById(R.id.button2);

        db = new DB(this);
        Student student = db.getStudent();

        id_txt = findViewById(R.id.id_txt);
        name_txt = findViewById(R.id.name_txt);
        mobile_txt = findViewById(R.id.mobile_txt);
        email_txt = findViewById(R.id.email_txt);
        department_txt = findViewById(R.id.department_txt);
        level_txt = findViewById(R.id.level_txt);

        if(student != null) {
            Toast.makeText(this, "data fetch is successful", Toast.LENGTH_SHORT).show();
            id_txt.setText(String.valueOf(student.getId()));
            name_txt.setText(student.getUsername());
            mobile_txt.setText(student.getMobile());
            email_txt.setText(student.getEmail());
            department_txt.setText(student.getDepartment());
            level_txt.setText(String.valueOf(student.getLevel()));
        } else {
            Toast.makeText(this, "data fetch is failed", Toast.LENGTH_SHORT).show();
        }
        b.setOnClickListener(v -> {
           startActivity(new Intent(Profile.this, Update.class));
        });
        blog.setOnClickListener(v -> {
            db.logOut();
            SharedPreferences sharedPreferences = getSharedPreferences("user_login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("is_logged", false);
            editor.apply();
            startActivity(new Intent(Profile.this, Login.class));
        });
    }
}