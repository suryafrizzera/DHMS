package com.example.dhms.view.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dhms.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firestore.v1.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class doctor_registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseAuth auth ;
    FirebaseUser user ;
    FirebaseFirestore db ;
    ProgressBar progressBar;


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
        progressBar = findViewById(R.id.progressBar);
        specialization = findViewById(R.id.specialization_edittext);
        phone = findViewById(R.id.phone_edittext);
        mlicense = findViewById(R.id.M_License_edittext);
        qualification = findViewById(R.id.qualification_edittext);
        submit = findViewById(R.id.submit_button);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();





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
                    progressBar.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.INVISIBLE);
                    String name = first_name.getText().toString().trim() + " " + last_name.getText().toString().trim();
                    String phoneno = phone.getText().toString().trim();
                    String hosp = hospital.getText().toString().trim();
                    String specialize = specialization.getText().toString().trim();
                    String mlicecseno = mlicense.getText().toString().trim();
                    String qualify = qualification.getText().toString().trim();




                    HashMap<String,Object> regdetails = new HashMap<>();
                    regdetails.put("name",name);
                    regdetails.put("phone Number",phoneno);
                    regdetails.put("Gender",gender);
                    regdetails.put("Hospital",hosp);
                    regdetails.put("Specialization",specialize);
                    regdetails.put("Medical License",mlicecseno);
                    regdetails.put("Qualification",qualify);

                    db.collection("doctor").document(Objects.requireNonNull(user.getEmail())).set(regdetails, SetOptions.merge())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            progressBar.setVisibility(View.INVISIBLE);
                            submit.setVisibility(View.VISIBLE);
                            finish();
                            startActivity(new Intent(doctor_registration.this,doctor_profile.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(doctor_registration.this,e.getMessage()
                            ,Toast.LENGTH_SHORT).show();
                        }
                    });





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