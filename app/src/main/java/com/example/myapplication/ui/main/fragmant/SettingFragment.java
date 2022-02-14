package com.example.myapplication.ui.main.fragmant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;


public class SettingFragment extends Fragment {
     private TextView emailuser;
     private TextView userphone;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;



    public SettingFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailuser=view.findViewById(R.id.emailuser);
        userphone=view.findViewById(R.id.userphone);
        progressBar=view.findViewById(R.id.progress);
        //firebase
        firebaseAuth= FirebaseAuth.getInstance();

    }
}