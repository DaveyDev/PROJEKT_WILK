package com.example.projekt_wilk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekt_wilk.R;
import com.example.projekt_wilk.model.ChatMessageModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class TopicRecyclerAdapter extends FirestoreRecyclerAdapter<ChatMessageModel, TopicRecyclerAdapter.ChatViewHolder> {

    Context context;

    public TopicRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatMessageModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public TopicRecyclerAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_forum_message, parent, false);
        return new TopicRecyclerAdapter.ChatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position, @NonNull ChatMessageModel model) {


        // holder.leftChatTv.setText(model.getMessage());
        // holder.rightChatTv.setText(model.getSenderId());
       // if(model.getSenderId().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
            //holder.leftChatLayout.setVisibility(View.GONE);
            //holder.rightChatLayout.setVisibility(View.VISIBLE);
            //holder.rightChatTv.setText(model.getMessage());

        //} else {
            holder.leftChatLayout.setVisibility(View.VISIBLE);
           // holder.rightChatLayout.setVisibility(View.GONE);
            holder.leftChatTv.setText(model.getMessage());
       // }

    }



    //@Override
    //public int getItemCount() {
    //    return chatMessageList.size();

   // }



    public static class ChatViewHolder extends RecyclerView.ViewHolder{

        LinearLayout leftChatLayout, rightChatLayout;
        TextView leftChatTv, rightChatTv;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            leftChatLayout = itemView.findViewById(R.id.left_chat_layout);
            //rightChatLayout = itemView.findViewById(R.id.right_chat_layout);
            leftChatTv = itemView.findViewById(R.id.left_chat_tv);
            //rightChatTv = itemView.findViewById(R.id.right_chat_tv);


        }
    }
}