package com.example.doctalk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Patient_Info extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__info);



        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Enter Your Symptoms");
        list.add("Fever");
        list.add("Cough");
        list.add("Headache");
        list.add("Runny Nose");
        list.add("Sore Throat");
        list.add("Muscle Pain");
        list.add("Fatigue");
        list.add("Diarrhea");
        list.add("Increased thirst");
        list.add("Frequent urination");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







    }

}