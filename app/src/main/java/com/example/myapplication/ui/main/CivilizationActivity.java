package com.example.myapplication.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.model.ModelCivilization;
import com.example.myapplication.data.model.ModelDriver;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class CivilizationActivity extends AppCompatActivity implements CivilizationAdapter.OnClick  {
  private RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;
    private ProgressBar progressBar;
    private CivilizationAdapter adapter;
    private List<ModelCivilization> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civilization);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.Progress);
        firebaseFirestore = FirebaseFirestore.getInstance();

      boolean isDark=  getSharedPreferences("theme",MODE_PRIVATE)

                .getBoolean("themeSelected",false);

        if (isDark){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager((CivilizationActivity.this)));
        list=new ArrayList<>();
        adapter=new CivilizationAdapter(CivilizationActivity.this,list,this);
        recyclerView.setAdapter(adapter);
        getData();



    }




    @Override
    public void onItemClick(String pos) {
        if(pos.equals("0"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();

        }
        if(pos.equals("1"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();
        }
        if(pos.equals("2"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();
        }
        if(pos.equals("3"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();
        }
    }

    private void getData(){
        progressBar.setVisibility(View.VISIBLE);
        firebaseFirestore.collection("Civilization")
                .orderBy("time", Query.Direction.DESCENDING)
                .addSnapshotListener((value, error) -> {

                    if (error==null){
                        if (value==null){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(CivilizationActivity.this,"value is null",Toast.LENGTH_SHORT).show();

                        }else {
                            for (DocumentChange documentChange: value.getDocumentChanges()){
                                ModelCivilization model=documentChange.getDocument().toObject(ModelCivilization.class);
                                list.add(model);
                                adapter.notifyDataSetChanged();

                            }
                            progressBar.setVisibility(View.GONE);
                        }

                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(CivilizationActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                });
}}