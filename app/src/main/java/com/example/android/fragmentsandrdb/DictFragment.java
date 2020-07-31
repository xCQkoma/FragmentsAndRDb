package com.example.android.fragmentsandrdb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DictFragment extends Fragment {

    private WordViewModel mWordViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dict, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView=view.findViewById(R.id.recyclerview);
        final WordListAdapter adapter=new WordListAdapter(getActivity()); //Contextがないので
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mWordViewModel = new ViewModelProvider(getActivity()).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(getViewLifecycleOwner(),
                new Observer<List<Word>>(){
                    @Override
                    public void onChanged(@Nullable final List<Word> words) {
                        adapter.setWords(words);
                    }
                });


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DictFragment.this)
                        .navigate(R.id.action_DictFragment_to_InputFragment);
            }
        });
    }
}