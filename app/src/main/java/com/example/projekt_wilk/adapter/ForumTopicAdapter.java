package com.example.projekt_wilk.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekt_wilk.ForumTopic;
import com.example.projekt_wilk.ForumTopicActivity;
import com.example.projekt_wilk.R;

import java.util.ArrayList;

public class ForumTopicAdapter extends RecyclerView.Adapter<ForumTopicAdapter.ForumTopicViewHolder> {
    private Context context;
    private ArrayList<ForumTopic> forumTopicsList;
    private String chatroomId;

    public ForumTopicAdapter(Context context, ArrayList<ForumTopic> forumTopicsList) {
        this.context = context;
        this.forumTopicsList = forumTopicsList;
        this.chatroomId = chatroomId;
    }

    @NonNull
    @Override
    public ForumTopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your view and return a new ViewHolder instance
        View view = LayoutInflater.from(context).inflate(R.layout.layout_topics, parent, false);
        return new ForumTopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumTopicViewHolder holder, int position) {
        // Bind data to your ViewHolder
        ForumTopic forumTopic = forumTopicsList.get(position);
        holder.bind(forumTopic);

        holder.itemView.setOnClickListener(v -> {

            ForumTopic clickedTopic = forumTopicsList.get(position);
            String chatroomId = clickedTopic.getChatroomId();
            Intent intent = new Intent(context, ForumTopicActivity.class);

            intent.putExtra("chatroomId", chatroomId);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        // Return the size of your data list
        return forumTopicsList.size();
    }

    public class ForumTopicViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;

        public ForumTopicViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tvTopicName);
            descriptionTextView = itemView.findViewById(R.id.tvDate);
        }

        public void bind(ForumTopic forumTopic) {
            // Bind data to your views here
            titleTextView.setText(forumTopic.getTitle());
            descriptionTextView.setText(forumTopic.getDescription());
        }
    }
}
