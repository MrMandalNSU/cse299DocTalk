package com.example.doctalk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class LoginDoc extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginbtn;
    TextView mPatientLogIn;
    ProgressBar mProgressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_doc);

        mEmail = findViewById(R.id.EmailDoc);
        mPassword = findViewById(R.id.passwordDoc);
        mProgressBar = findViewById(R.id.progressBar2Doc);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        mLoginbtn = findViewById(R.id.loginbtnDoc);
        mPatientLogIn = findViewById(R.id.patientLogIn);

        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;

                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password is too short ");
                    return;
                }


                mProgressBar.setVisibility(View.VISIBLE);

                // Authenticate User.

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        final String doctor = new String("Doctor");
                        if(task.isSuccessful())
                        {
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                @Override
                                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                    String userType = value.getString("userType").toString();
                                    if(!userType.equals(doctor)) {
                                        Toast.makeText(LoginDoc.this, "You are not a doctor! Go to Patient sign in page.", Toast.LENGTH_SHORT).show();
                                        mProgressBar.setVisibility(View.GONE);
                                    }
                                    else {
                                        Toast.makeText(LoginDoc.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(getApplicationContext(), MainActivityDoc.class));
                                    }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(LoginDoc.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });


        mPatientLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });



    }
}