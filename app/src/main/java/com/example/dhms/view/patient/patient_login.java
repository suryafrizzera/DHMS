package com.example.dhms.view.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dhms.R;
import com.example.dhms.view.doctor.doctor_login;
import com.example.dhms.view.doctor.doctor_profile;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class patient_login extends AppCompatActivity {
    EditText email,password;
    Button login;

    FirebaseAuth auth;
    ProgressBar progressBar;

    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
        signup=findViewById(R.id.signup_link);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.register_button);
        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        final String mail = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                login.setVisibility(View.INVISIBLE);
                auth.signInWithEmailAndPassword(mail,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressBar.setVisibility(View.INVISIBLE);
                        login.setVisibility(View.VISIBLE);
                        finish();
                        startActivity(new Intent(patient_login.this, doctor_profile.class));
                    }
                });
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(patient_login.this,patient_signup.class));
                            }
        });
    }
}