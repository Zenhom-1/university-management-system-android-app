package com.example.studentasu;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentasu.DB.DB;

public class Update extends AppCompatActivity {
    Spinner addcat, dep;
    DB db;
    long id;
    int  level , level_send_update;
    String name, department , department_send , mobile;
    EditText e1, e2, e4;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));

        db = new DB(this);
        displayData();
        e1 = findViewById(R.id.STname);
        e2 = findViewById(R.id.idid);
        dep = findViewById(R.id.dep);
        e4 = findViewById(R.id.mobile);
        e1.setText(name);
        e2.setText(String.valueOf(id));
        //dep.setText(departmen);
        e4.setText(mobile);
        addcat = findViewById(R.id.Lev);
        String[] data = new String[]{"Select level", "First", "Second", "Third", "Fourth"};
        //System.out.println(addcat);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        addcat.setAdapter(adapter);
        addcat.setSelection(level);
        addcat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categ = (String) parent.getItemAtPosition(position);
                switch (categ) {
                    case "First":
                        level_send_update = 1;
                        break;
                    case "Second":
                        level_send_update = 2;
                        break;
                    case "Third":
                        level_send_update = 3;
                        break;
                    case "Fourth":
                        level_send_update = 4;
                        break;
                    default:
                        level_send_update = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                level_send_update = 0;
            }
        });

        String[] datadep = new String[]{"Select Department", "CS"};

        ArrayAdapter<String> adapterdep = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datadep);
        dep.setAdapter(adapterdep);
        dep.setSelection(1);
        dep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categ = (String) parent.getItemAtPosition(position);
                department_send = categ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                department_send = "";
            }
        });


        btn = findViewById(R.id.button1);
        btn.setOnClickListener(v -> {
            if(isInputValid(e2.getText().toString(),e1.getText().toString(),e4.getText().toString(),department_send,level_send_update))
            {
                db.updateDate(
                        Long.parseLong(e2.getText().toString()),
                        e1.getText().toString(),
                        e4.getText().toString(),
                        department_send,
                        level_send_update
                );
                Intent intent = new Intent(Update.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void displayData()
    {
        Cursor cursor = db.getStudentData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data Student", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                id = cursor.getLong(0);
                name = cursor.getString(1);
                department = cursor.getString(4);
                mobile = cursor.getString(2);
                level = cursor.getInt(5);
            }
        }
    }

    public boolean isInputValid(String id, String username, String mobile, String department, int level) {
        if(id.length() != 14 || id.isEmpty()) {
                Toast.makeText(Update.this, "ID cannot be empty and Equals 14 number", Toast.LENGTH_SHORT).show();
                return false;
        }
        if (username.isEmpty()) {
            Toast.makeText(Update.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mobile.length() != 11 || (!mobile.startsWith("011") && !mobile.startsWith("010") && !mobile.startsWith("012") && !mobile.startsWith("015"))) {
            Toast.makeText(Update.this, "Mobile number is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (department_send != "CS" ) {
            Toast.makeText(Update.this, "Department is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (level_send_update <= 0) {
            Toast.makeText(Update.this, "Level must be greater than 0", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}