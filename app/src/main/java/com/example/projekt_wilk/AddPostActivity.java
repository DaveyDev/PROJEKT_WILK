package com.example.projekt_wilk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projekt_wilk.model.ChatMessageModel;
import com.example.projekt_wilk.model.ChatroomModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;


public class AddPostActivity extends AppCompatActivity {
    ImageButton backBtn, addTopicBtn;
    ChatroomModel chatroomModel;
    String chatroomId, topic;
    Intent intent;
    EditText titleTxt, descriptionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);

        backBtn = findViewById(R.id.appbackbtn);
        addTopicBtn = findViewById(R.id.addtopicbtn);
        titleTxt = findViewById(R.id.chatBox2);
        descriptionTxt = findViewById(R.id.chatBox3);

        intent = getIntent();





        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        addTopicBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                chatroomId = titleTxt.getText().toString();
                CreateChatroomModel();
                Log.d("title", chatroomId);
                String firstPostMessage = descriptionTxt.getText().toString();
                setFirstPost(firstPostMessage);
            }
        });



    }
    void setFirstPost(String message){


        chatroomModel.setLastMessageTimestamp(Timestamp.now());
        chatroomModel.setLastMessageSenderId(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        FirebaseUtil.getChatroomReference(chatroomId).set(chatroomModel);


        ChatMessageModel chatMessageModel = new ChatMessageModel(message, FirebaseAuth.getInstance().getCurrentUser().getEmail(), Timestamp.now());
        FirebaseUtil.getChatroomMessageReference(chatroomId).add(chatMessageModel)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

    }
    void CreateChatroomModel() {

        if (chatroomId != null && !chatroomId.isEmpty()) {
            // Ensure chatroomId is not null or empty
            chatroomModel = new ChatroomModel(
                    chatroomId,
                    FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                    Timestamp.now(),
                    "",
                    chatroomId
            );
            FirebaseUtil.getChatroomReference(chatroomId).set(chatroomModel);
        } else {
            // Handle invalid chatroomId
            // Log an error or show a toast indicating the issue
            // not gonna hande that stuff. it just have to work
        }
    }
}
