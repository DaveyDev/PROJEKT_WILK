package com.example.projekt_wilk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekt_wilk.adapter.ForumTopicAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ImageButton accountButton, addPostBtn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference forumTopicsRef = db.collection("ForumData");

    ForumTopicAdapter adapter;
    RecyclerView recyclerView;
    String chatroomId;
    ArrayList<ForumTopic> forumTopicsList = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountButton = findViewById(R.id.accountButton);
        addPostBtn = findViewById(R.id.addPostBtn);
        recyclerView = findViewById(R.id.topicList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




// Create an ArrayList to store forum topics


// Assuming you have a Firestore reference for your forum topics





// Create a Firestore snapshot listener to retrieve data
        forumTopicsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("MainActivity", "Error fetching forum topics", error);
                    // Handle error
                    return;
                }

                if (value != null && !value.isEmpty()) {
                    // Clear existing data
                    forumTopicsList.clear();

                    // Parse the snapshot and add forum topics to the list
                    for (QueryDocumentSnapshot document : value) {
                        // Assuming ForumTopic class has constructor that takes topic and description as parameters
                        ForumTopic forumTopic = new ForumTopic(
                                document.getString("topic"),
                                document.getString("description"),
                                document.getString("chatroomId")
                        );
                        forumTopicsList.add(forumTopic);
                    }

                    // Notify the RecyclerView adapter that the data has changed
                    adapter.notifyDataSetChanged();
                } else {
                    // Handle empty snapshot (no documents found)
                    // For example, display a message to the user or perform alternative actions
                    Log.d("MainActivity", "No forum topics found");
                }
            }
        });


// Set up RecyclerView with an adapter
        RecyclerView recyclerView = findViewById(R.id.topicList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

// Initialize the adapter with forumTopicsList
        adapter = new ForumTopicAdapter(this, forumTopicsList);
        recyclerView.setAdapter(adapter);


        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountPage.class);
                startActivity(intent);
                finish();
            }
        });
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddPostActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



}