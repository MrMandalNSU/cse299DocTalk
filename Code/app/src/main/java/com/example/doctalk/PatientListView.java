package com.example.doctalk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientListView extends AppCompatActivity {


    ListView patientListView;
    ArrayList<String> patientArrayList;
    ArrayAdapter<String> patientArrayAdapter;
    DatabaseReference databaseReference;
    PatientHelperClass patientHelperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list_view);

        patientHelperClass = new PatientHelperClass();
        databaseReference= FirebaseDatabase.getInstance().getReference("Patient");
        patientListView = (ListView) findViewById(R.id.patientListView);
        patientArrayList = new ArrayList<>();
        patientArrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_patient_list,R.id.patientList,patientArrayList);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    patientHelperClass = ds.getValue(PatientHelperClass.class);
                    patientArrayList.add(patientHelperClass.getName().toString() + "    " +patientHelperClass.getAddiSymptoms());
                }

                patientListView.setAdapter(patientArrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }
}