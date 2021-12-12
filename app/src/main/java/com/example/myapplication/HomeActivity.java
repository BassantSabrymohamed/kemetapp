package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements Adapter.OnClick {
    RecyclerView recyclerView;
    Adapter adapter;
    Modaldata modeldata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.rec);

        ArrayList<Modaldata> list = new ArrayList<>();
      Modaldata m1 = new Modaldata("Civilization",R.drawable.m);
        list.add(m1);
        Modaldata m2 = new Modaldata("Hotiel",R.drawable.b);
        list.add(m2);
       Modaldata m3 = new Modaldata("Lemosin",R.drawable.a);
        list.add(m3);
       Modaldata m4 = new Modaldata("Tourisist Guide",R.drawable.s);
        list.add(m4);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, list, this);
        recyclerView.setAdapter(adapter);

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
    }
}
