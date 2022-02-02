package com.example.myapplication.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.data.model.Modaldata;
import com.example.myapplication.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.OnClick {
    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    Modaldata modeldata;
    private Context context;

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
        homeAdapter = new HomeAdapter(this, list, this);
        recyclerView.setAdapter(homeAdapter);

    }


    @Override
    public void onItemClick(String pos) {
        if(pos.equals("0"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();
            startActivity(new Intent(HomeActivity.this, CivilizationActivity.class));
        }
        if(pos.equals("1"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();
            startActivity(new Intent(HomeActivity.this, HotileActivity.class));
        }
        if(pos.equals("2"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();
            startActivity(new Intent(HomeActivity.this, DriverActivity.class));
        }
        if(pos.equals("3"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();
            startActivity(new Intent(HomeActivity.this, TourGuideActivity.class));
        }
    }



}
