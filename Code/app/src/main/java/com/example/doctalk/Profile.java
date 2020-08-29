package com.example.doctalk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity {
    TextView fullName, email, phone, userType;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullName = findViewById(R.id.profileUserName);
        email = findViewById(R.id.profileUserEmail);
        phone = findViewById(R.id.profileUserPhone);
        userType = findViewById(R.id.profileUserType);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                fullName.setText(value.getString("fName"));
                email.setText(value.getString("email"));
                phone.setText(value.getString("phone"));
                userType.setText(value.getString("userType"));
            }
        });
    }
}