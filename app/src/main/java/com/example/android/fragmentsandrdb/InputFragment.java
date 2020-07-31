package com.example.android.fragmentsandrdb;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class InputFragment extends Fragment {
    private EditText mEditWordView;

    OnInputWrittenListener mCallback;

    public interface OnInputWrittenListener{
        public void onWordInputListener(String param);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
          mCallback=(OnInputWrittenListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditWordView=view.findViewById(R.id.edit_word);

        view.findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditWordView.getText())){
                    Toast.makeText(getActivity(), R.string.empty_not_saved, Toast.LENGTH_SHORT).show();
                }else{
                    String word=mEditWordView.getText().toString();
                    mCallback.onWordInputListener(word);
                }
                NavHostFragment.findNavController(InputFragment.this)
                        .navigate(R.id.action_InputFragment_to_DictFragment);
            }
        });
    }
}