package com.example.doctalk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
                    patientArrayList.add(patientHelperClass.getName().toString());
                }

                patientListView.setAdapter(patientArrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



       patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


              PatientHelperClass patientHelperClass = (PatientHelperClass) patientListView.getItemAtPosition(position);


                //fetch the details of the item

               databaseReference.child(String.valueOf(position+1)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //get details of the item
                            String name = dataSnapshot.child("name").getValue(String.class);
                            String age = dataSnapshot.child("age").getValue(String.class);
                            String location = dataSnapshot.child("location").getValue(String.class);
                            String phone = dataSnapshot.child("phone").getValue(String.class);
                            String addiSymptoms = dataSnapshot.child("addiSymptoms").getValue(String.class);

                            //open another acivity and pass these
                            Intent intent = new Intent(PatientListView.this, PatientProfile.class);
                            intent.putExtra("name", name);
                            intent.putExtra("age", age);
                            intent.putExtra("location", location);
                            intent.putExtra("phone", phone);
                            intent.putExtra("addiSymptoms", addiSymptoms);
                            startActivity(intent);





                   }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
               });


            }
        });







    }
}