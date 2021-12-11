package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
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
        adapter = new Adapter(this, list);
        recyclerView.setAdapter(adapter);

    }

}
