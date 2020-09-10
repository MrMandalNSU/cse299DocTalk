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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient_Info extends AppCompatActivity {

    EditText patientName, patientAge, patientAddress, patientNumber, patientAddSymptoms,patientGender;
    Button patientInfoSubmitBtn;

    public int position;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__info);

        patientName = findViewById(R.id.patientname);
        patientAge = findViewById(R.id.patientage);
        patientAddress = findViewById(R.id.location);
        patientNumber = findViewById(R.id.phone);
        patientAddSymptoms = findViewById(R.id.additionalsymptoms);
        patientGender = findViewById(R.id.patientgender);
        patientInfoSubmitBtn = findViewById(R.id.patientinfobtn);
        spinner= findViewById(R.id.spinner);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference fDatabaseRoot = database.getReference();

        final List<String> doctorNameList = new ArrayList<String>();
        final ArrayAdapter<String> doctorAdapter = new ArrayAdapter<String>(Patient_Info.this, android.R.layout.simple_spinner_item,doctorNameList );
        doctorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fDatabaseRoot.child("Doctors").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array


                for (DataSnapshot addressSnapshot: dataSnapshot.getChildren()) {
                    String doctorname = addressSnapshot.child("fullname").getValue(String.class);
                    if (doctorname!=null){
                        doctorNameList.add(doctorname);
                    }
                }



                spinner.setAdapter(doctorAdapter);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





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
                String gender = patientGender.getText().toString();
                String doctorName = spinner.getSelectedItem().toString();

                PatientHelperClass patientHelperClass = new PatientHelperClass(name, age, location, phone, addiSymptoms,gender,doctorName);

                reference.child(phone).setValue(patientHelperClass);


                Toast.makeText(Patient_Info.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

            }
        });


    }

}