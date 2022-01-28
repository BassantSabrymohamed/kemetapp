package com.example.myapplication.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.model.ModelCivilization;

import java.util.ArrayList;

public class CivilizationActivity extends AppCompatActivity implements CivilizationAdapter.OnClick  {
  private RecyclerView recyclerView;
  private ModelCivilization modelCivilization;
  private CivilizationAdapter civilizationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civilization);
        recyclerView = findViewById(R.id.recyclerView);
        ArrayList<ModelCivilization>list=new ArrayList<>();
        ModelCivilization c1=new ModelCivilization("Pyramids",R.drawable.ba);
        list.add(c1);
        ModelCivilization c2=new ModelCivilization("Karnak Temple",R.drawable.t);
        list.add(c2);
        ModelCivilization c3=new ModelCivilization("Egyption Museum",R.drawable.h);
        list.add(c3);
        ModelCivilization c4=new ModelCivilization("Abu Simble Temple",R.drawable.g);
        list.add(c4);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        civilizationAdapter = new CivilizationAdapter(this, list, this);
        recyclerView.setAdapter(civilizationAdapter);

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
}