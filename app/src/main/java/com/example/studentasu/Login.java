package com.example.studentasu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentasu.DB.DB;

import java.io.IOException;

public class Login extends AppCompatActivity {

    private EditText name_input;
    private EditText email_input;
    private EditText mobile_input;
    private EditText id_input;
    private EditText password_input;
    private Spinner department_input;
    private Spinner addcat;
    private Button submit_btn;

    public int level = 0;
    public String depp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        name_input = findViewById(R.id.username);
        email_input = findViewById(R.id.email);
        mobile_input = findViewById(R.id.mobile);
        id_input = findViewById(R.id.ID);
        password_input = findViewById(R.id.password);
        department_input = findViewById(R.id.department);
        submit_btn = findViewById(R.id.submit_btn);


        addcat=findViewById(R.id.addcat);
        String[] data = new String[]{"Select level", "First", "Second", "Third", "Fourth"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        addcat.setAdapter(adapter);
        addcat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categ = (String) parent.getItemAtPosition(position);
                switch (categ) {
                    case "First":
                        level = 1;
                        break;
                    case "Second":
                        level = 2;
                        break;
                    case "Third":
                        level = 3;
                        break;
                    case "Fourth":
                        level = 4;
                        break;
                    default:
                        level = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                level = 0;
            }
        });
        String[] datadep = new String[]{"Select Department", "CS"};

        ArrayAdapter<String> adapterdep = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datadep);
        department_input.setAdapter(adapterdep);

        department_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categ = (String) parent.getItemAtPosition(position);
                depp = categ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                depp = "";
            }
        });

        if(getSharedPreferences("user_login", MODE_PRIVATE).getBoolean("is_logged", false))
        {
            startActivity(new Intent(Login.this, Home.class));
        }
        submit_btn.setOnClickListener(v -> {
            long id;

            String username = name_input.getText().toString();
            String email = email_input.getText().toString();
            String password = password_input.getText().toString();
            String mobile = mobile_input.getText().toString();

            if(!id_input.getText().toString().isEmpty() && id_input.getText().length() == 14) {
                try {
                    id = Long.parseLong(id_input.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "ID is only numbers", Toast.LENGTH_SHORT).show();
                    return; // Exit the OnClickListener if the ID is not a number
                }
            } else {
                Toast.makeText(getApplicationContext(), "ID cannot be empty and Equals 14 number", Toast.LENGTH_SHORT).show();
                return; // Exit the OnClickListener if the ID is empty
            }

            SharedPreferences sh = getSharedPreferences("user_login", MODE_PRIVATE);
            boolean isLoggedIn = sh.getBoolean("is_logged", false);

            if(isInputValid(username, email, password, mobile, depp, level) && !isLoggedIn) {
                DB db = new DB(getApplicationContext());
                if(!db.addStudent(id, username, email, password, mobile, depp, level)) {
                    Toast.makeText(getApplicationContext(), "Login failed, please close the app and try again", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sh.edit();
                editor.putBoolean("is_logged", true);
                editor.apply();

                startActivity(new Intent(Login.this, Home.class));
            }
        });
    }

    public boolean isInputValid(String username, String email, String password, String mobile, String department, int level) {
        if (username.isEmpty()) {
            Toast.makeText(Login.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!email.contains("@") || email.split("\\.").length != 2) {
            Toast.makeText(Login.this, "Email is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.isEmpty() || password.length() < 8) {
            Toast.makeText(Login.this, "Password cannot be empty and more Than 8 char", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mobile.length() != 11 || (!mobile.startsWith("011") && !mobile.startsWith("010") && !mobile.startsWith("012") && !mobile.startsWith("015"))) {
            Toast.makeText(Login.this, "Mobile number is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (depp != "CS" ) {
            Toast.makeText(Login.this, "Department is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (level <= 0) {
            Toast.makeText(Login.this, "Level must be greater than 0", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}
