package com.example.projekt_wilk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ChatFragment extends Fragment {

    RecyclerView recyclerView;
    public ChatFragment() {
    }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_forum, container, false);
            recyclerView = view.findViewById(R.id.forumRecycler);
            //setupRecyclerView();
            return view;
        }
}

