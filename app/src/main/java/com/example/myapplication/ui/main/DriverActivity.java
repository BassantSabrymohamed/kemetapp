package com.example.myapplication.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.model.ModelDriver;
import com.example.myapplication.data.model.ModelTourGuide;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class DriverActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;
    private ProgressBar progressBar;
    private DriverAdapter adapter;
    private List<ModelDriver> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.Progress);
        firebaseFirestore = FirebaseFirestore.getInstance();

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        boolean isDark=  getSharedPreferences("theme",MODE_PRIVATE)

                .getBoolean("themeSelected",false);

        if (isDark){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager((DriverActivity.this)));
        list=new ArrayList<>();
        adapter=new DriverAdapter(DriverActivity.this,list);
        recyclerView.setAdapter(adapter);
        getData();

    }
    private void getData(){
        progressBar.setVisibility(View.VISIBLE);
        firebaseFirestore.collection("Lemosin")
                .orderBy("time", Query.Direction.DESCENDING)
                .addSnapshotListener((value, error) -> {

                    if (error==null){
                        if (value==null){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(DriverActivity.this,"value is null",Toast.LENGTH_SHORT).show();

                        }else {
                            for (DocumentChange documentChange: value.getDocumentChanges()){
                                ModelDriver model=documentChange.getDocument().toObject(ModelDriver.class);
                                list.add(model);
                                adapter.notifyDataSetChanged();

                            }
                            progressBar.setVisibility(View.GONE);
                        }

                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(DriverActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                });
    }
}