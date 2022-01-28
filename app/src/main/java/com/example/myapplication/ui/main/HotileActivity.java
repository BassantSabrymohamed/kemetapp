package com.example.myapplication.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Modaldata;
import com.example.myapplication.data.model.ModelHotile;

import java.util.ArrayList;

public class HotileActivity extends AppCompatActivity implements HotileAdapter.OnClick {
    private RecyclerView recyclerView;
    private HotileAdapter hotileAdapter;
    private ModelHotile modelHotile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotile);
        recyclerView = findViewById(R.id.recyclerView);
        ArrayList<ModelHotile>list=new ArrayList<>();
        ModelHotile h1=new ModelHotile("Semiramis",R.drawable.k);
        list.add(h1);
        ModelHotile h2=new ModelHotile("Pyrimeds Hotile",R.drawable.w);
        list.add(h2);
        ModelHotile h3=new ModelHotile("Hotile Cairo",R.drawable.r);
        list.add(h3);
        ModelHotile h4=new ModelHotile("Hotile Star",R.drawable.j);
        list.add(h4);
        ModelHotile h5=new ModelHotile("Hotile Star",R.drawable.b);
        list.add(h5);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        hotileAdapter = new HotileAdapter(this, list, this);
        recyclerView.setAdapter(hotileAdapter);
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
        if(pos.equals("4"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();
        }

    }
}