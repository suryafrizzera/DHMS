package com.example.dhms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dhms.view.doctor.doctor_login;
import com.example.dhms.view.patient.patient_login;

public class MainActivity extends AppCompatActivity {
    Button doctor,patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doctor = findViewById(R.id.doctor_button);
        patient = findViewById(R.id.patient_button);

        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, doctor_login.class));
            }
        });

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, patient_login.class));
            }
        });
    }
}