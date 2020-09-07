package com.example.doctalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class PatientClass extends AppCompatActivity {

    private Button ocrbutton;
    private Button patientsymptoms;
    private Button profilebutton;
    private Button doctorList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_class);





        ocrbutton = (Button) findViewById(R.id.ocr_btn); //ocr button
        patientsymptoms = (Button) findViewById(R.id.symptoms_Btn);
        profilebutton = (Button) findViewById(R.id.profile_btn);
        doctorList = (Button) findViewById(R.id.doc_List);



        ocrbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PatientClass.this,text_recognition.class);
                startActivity( intent1);
                finish();

            }
        });


        patientsymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(PatientClass.this,Patient_Info.class);
                startActivity( intent2);
                finish();

            }
        });

        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(PatientClass.this,Profile.class);
                startActivity( intent3);
                finish();
            }
        });

        doctorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(com.example.doctalk.PatientClass.this,DoctorList.class);
                startActivity( intent5);
                finish();
            }
        });

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut(); //logout user
        startActivity(new Intent(getApplicationContext(),Login.class)); //after logout sending them to login activity again.
        finish();
    }

}

