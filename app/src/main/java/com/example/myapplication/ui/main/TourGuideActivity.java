package com.example.myapplication.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.model.ModelTourGuide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class  TourGuideActivity extends AppCompatActivity {
 private RecyclerView recyclerView;
 private FirebaseFirestore firebaseFirestore;
 private ProgressBar progressBar;
 private TourGuideAdapter adapter;
 private List<ModelTourGuide>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_guide);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.Progress);
        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager((TourGuideActivity.this)));

        list=new ArrayList<>();
        adapter=new TourGuideAdapter(TourGuideActivity.this,list);
        recyclerView.setAdapter(adapter);
        getData();
    }
    private void getData(){
        progressBar.setVisibility(View.VISIBLE);
        firebaseFirestore.collection("TourGuide")
                .orderBy("time",Query.Direction.DESCENDING)
                .addSnapshotListener((value, error) -> {

                    if (error==null){
                        if (value==null){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(TourGuideActivity.this,"value is null",Toast.LENGTH_SHORT).show();

                        }else {
                            for (DocumentChange documentChange: value.getDocumentChanges()){
                                ModelTourGuide model=documentChange.getDocument().toObject(ModelTourGuide.class);
                                list.add(model);
                                adapter.notifyDataSetChanged();

                            }
                            progressBar.setVisibility(View.GONE);
                        }

                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(TourGuideActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                });
    }

}