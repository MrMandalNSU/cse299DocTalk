package com.example.doctalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorList extends AppCompatActivity {


    ListView doctorListView;
    ArrayList<String> doctorArrayList;
    ArrayAdapter<String> doctorArrayAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        doctorListView = (ListView) findViewById(R.id.doctorListView);
        doctorArrayList = new ArrayList<>();
        doctorArrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_doctor_extra_list,R.id.doctorList,doctorArrayList);




        databaseReference.child("Users").orderByChild("userType").equalTo("Doctor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    String tempName = data.child("fullname").getValue(String.class);
                    doctorArrayList.add(tempName);

                    //with tempName you can access their usernames and you will only get the usernames with session 2011, so you can directly populate your listView from here and use it

                }
                doctorListView.setAdapter(doctorArrayAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}