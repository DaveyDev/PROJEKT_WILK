package com.example.projekt_wilk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekt_wilk.adapter.ForumTopicAdapter;

import java.util.ArrayList;

public class ForumFragment extends Fragment {
    private RecyclerView recyclerView;
    private ForumTopicAdapter adapter;
    private String chatroomId;
    private ArrayList<ForumTopic> forumTopicsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        recyclerView = view.findViewById(R.id.forumRecycler);
        setupRecyclerView();


        return view;
    }


    private void setupRecyclerView() {
        // Assuming you have forum topics data stored in an ArrayList<ForumTopic> called forumTopicsList
        forumTopicsList = new ArrayList<>();
        // Add forum topics to the list if necessary

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Pass the forumTopicsList to the adapter constructor
        adapter = new ForumTopicAdapter(getContext(), forumTopicsList);
        recyclerView.setAdapter(adapter);
    }

    // Method to update the RecyclerView when data changes
    public void updateRecyclerView(ArrayList<ForumTopic> updatedList) {
        forumTopicsList.clear();
        forumTopicsList.addAll(updatedList);
        adapter.notifyDataSetChanged();
    }
}
