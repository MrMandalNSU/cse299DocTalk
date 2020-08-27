package com.example.doctalk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Patient_Info extends AppCompatActivity {

    EditText patientName, patientAge, patientAddress, patientNumber, patientAddSymptoms;
    Button patientInfoSubmitBtn;


    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__info);

        patientName = findViewById(R.id.patientname);
        patientAge = findViewById(R.id.patientage);
        patientAddress = findViewById(R.id.location);
        patientNumber = findViewById(R.id.phone);
        patientAddSymptoms = findViewById(R.id.additionalsymptoms);
        patientInfoSubmitBtn = findViewById(R.id.patientinfobtn);


        patientInfoSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Patient");

                String name = patientName.getText().toString();
                String age = patientAge.getText().toString();
                String location = patientAddress.getText().toString();
                String phone = patientNumber.getText().toString();
                String addiSymptoms = patientAddSymptoms.getText().toString();

                PatientHelperClass patientHelperClass = new PatientHelperClass(name, age, location, phone, addiSymptoms);

                reference.child(phone).setValue(patientHelperClass);

                Toast.makeText(Patient_Info.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

            }
        });


    }

}