package com.example.dhms.view.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dhms.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

public class doctor_signup extends AppCompatActivity {

    FirebaseAuth auth ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference doctor = db.collection("doctor");
    EditText username,email,password,confirm_password;
    Button signup;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
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

                    signup.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    final String mail = email.getText().toString().trim();
                    String password_text = confirm_password.getText().toString().trim();
                    final String username_text = username.getText().toString().trim();
                    auth.createUserWithEmailAndPassword(mail,password_text)
                  .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                      @Override
                      public void onSuccess(AuthResult authResult) {
                          Toast.makeText(doctor_signup.this,"Registered Successfully",Toast.LENGTH_SHORT)
                          .show();
                          progressBar.setVisibility(View.INVISIBLE);
                          signup.setVisibility(View.VISIBLE);
                          HashMap<String,Object> user = new HashMap<>();
                          user.put("username",username_text);
                          user.put("email",mail);
                          String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                          user.put("date created",mydate);
                          doctor.document(mail).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                              @Override
                              public void onSuccess(Void aVoid) {
                                  finish();
                                  startActivity(new Intent(doctor_signup.this, patient_registration.class));

                              }
                          }).addOnFailureListener(new OnFailureListener() {
                              @Override
                              public void onFailure(@NonNull Exception e) {

                                  Toast.makeText(doctor_signup.this,e.getMessage() + "Try again",Toast.LENGTH_SHORT)
                                          .show();
                              }
                          });
                      }
                  }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            signup.setVisibility(View.VISIBLE);
                            Toast.makeText(doctor_signup.this,e.getMessage(),Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
                }
            }
        });
    }
}