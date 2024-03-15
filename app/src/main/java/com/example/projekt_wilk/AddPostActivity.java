package com.example.projekt_wilk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projekt_wilk.model.ChatroomModel;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;


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
