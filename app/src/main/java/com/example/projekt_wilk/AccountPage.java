package com.example.projekt_wilk;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AccountPage extends AppCompatActivity {

    private FirebaseFirestore db;
    FirebaseUser user;
    FirebaseAuth auth;
    Button logout, saveAccountData;
    ImageButton backButton;
    EditText emailText, usernameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountpage);




        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        logout = findViewById(R.id.logout);
        backButton = findViewById(R.id.backButton);
        emailText = findViewById(R.id.emailEditText);
        usernameText = findViewById(R.id.nameEditText);
        saveAccountData = findViewById(R.id.saveAccountData);


        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else {
            emailText.setText(user.getEmail());
            setAccountData();
            //loadProfilePicture();

        }

        saveAccountData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAccountData();
            }
        });



    }


    private void setAccountData(){ //do stuff

        db.collection("UserData").document(user.getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot != null && documentSnapshot.exists()){
                        usernameText.setText(documentSnapshot.getString("username"));


                    }
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AccountPage.this, "Err: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
  

    private void updateAccountData(){ //updates Account Data (like name, surname etc.)

        DocumentReference documentReference = db.collection("UserData").document(user.getEmail());

        Map<String, Object> updates = new HashMap<>();
        updates.put("username", usernameText.getText().toString());





        documentReference.update(updates).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                //Toast.makeText(AccountPage.this, "data changed.", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AccountPage.this, "Err", Toast.LENGTH_SHORT).show();
            }
        });

    }

}




