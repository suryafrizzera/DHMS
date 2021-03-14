package com.example.dhms.view.patient;

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
import com.example.dhms.view.doctor.doctor_profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Objects;

public class patient_registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseAuth auth ;
    FirebaseUser user ;
    FirebaseFirestore db ;
    ProgressBar progressBar;


    String[] genders = {"Male","Female"};
    Spinner sp;
    String gender="None";
    EditText first_name,last_name,existing_health,phone,dob;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        sp = findViewById(R.id.spinner_gender);
        first_name = findViewById(R.id.editTextTextPersonName);
        last_name = findViewById(R.id.editTextTextPersonName2);
        progressBar = findViewById(R.id.progressBar);
        phone = findViewById(R.id.phone_edittext);
        existing_health = findViewById(R.id.Existing_health_edittext);
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
                        !TextUtils.isEmpty(existing_health.getText().toString()) &&
                        !TextUtils.equals(gender,"None")
                ){
                    progressBar.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.INVISIBLE);
                    String name = first_name.getText().toString().trim() + " " + last_name.getText().toString().trim();
                    String phoneno = phone.getText().toString().trim();
                    String health= existing_health.getText().toString().trim();
                    String dob_txt = dob.getText().toString().trim();




                    HashMap<String,Object> regdetails = new HashMap<>();
                    regdetails.put("name",name);
                    regdetails.put("phone Number",phoneno);
                    regdetails.put("Gender",gender);
                    regdetails.put("DOB",dob_txt);
                    regdetails.put("Existing issues",health);

                    db.collection("patient").document(Objects.requireNonNull(user.getEmail())).set(regdetails, SetOptions.merge())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    submit.setVisibility(View.VISIBLE);
                                    finish();
                                    startActivity(new Intent(patient_registration.this, doctor_profile.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(patient_registration.this,e.getMessage()
                                    ,Toast.LENGTH_SHORT).show();
                        }
                    });





                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}