package com.example.dhms.view.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhms.R;

public class doctor_signup extends AppCompatActivity {

    EditText username,email,password,confirm_password;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);
        username=findViewById(R.id.editTextusername);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        confirm_password = findViewById(R.id.editTextconfirmTextPassword);
        signup = findViewById(R.id.register_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.equals(password.getText().toString().trim(),confirm_password.getText().toString().trim())){
                    Toast.makeText(doctor_signup.this, "Enter the same password in confirm password",Toast.LENGTH_SHORT).show();
                }
                else{
                    String mail = email.getText().toString().trim();
                    String password_text = confirm_password.getText().toString().trim();
                    String username_text = username.getText().toString().trim();

                }
            }
        });
    }
}