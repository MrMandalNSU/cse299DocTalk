package com.example.doctalk;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorClass extends AppCompatActivity {

    private Button ocrbutton;
    private Button profilebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_class);



                ocrbutton = (Button) findViewById(R.id.ocr_btnDoc); //ocr button
                profilebutton = (Button) findViewById(R.id.profile_btnDoc);



                ocrbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(com.example.doctalk.DoctorClass.this,text_recognition.class);
                        startActivity( intent1);
                        finish();

                    }
                });

                profilebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent3 = new Intent(com.example.doctalk.DoctorClass.this,Profile.class);
                        startActivity( intent3);
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



