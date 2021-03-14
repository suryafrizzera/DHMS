package com.example.dhms.view.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.dhms.R;

import java.util.HashMap;

public class doctor_registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] genders = {"Male","Female"};
    Spinner sp;
    String gender="None";
    EditText first_name,last_name,hospital,specialization,phone,mlicense,qualification;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);
        sp = findViewById(R.id.spinner_gender);
        first_name = findViewById(R.id.editTextTextPersonName);
        last_name = findViewById(R.id.editTextTextPersonName2);
        hospital = findViewById(R.id.hospital_edittext);
        specialization = findViewById(R.id.specialization_edittext);
        phone = findViewById(R.id.phone_edittext);
        mlicense = findViewById(R.id.M_License_edittext);
        qualification = findViewById(R.id.qualification_edittext);
        submit = findViewById(R.id.submit_button);


        sp.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_item,genders);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        sp.setAdapter(ad);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(first_name.getText().toString()) &&
                        !TextUtils.isEmpty(last_name.getText().toString()) &&
                        !TextUtils.isEmpty(phone.getText().toString()) &&
                        !TextUtils.isEmpty(hospital.getText().toString()) &&
                        !TextUtils.isEmpty(specialization.getText().toString()) &&
                        !TextUtils.isEmpty(mlicense.getText().toString()) &&
                        !TextUtils.isEmpty(qualification.getText().toString()) &&
                        !TextUtils.equals(gender,"None")
                ){
                    HashMap<String,Object> regdetails = new HashMap<>();
                }
            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        gender = genders[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}