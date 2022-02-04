package com.example.myapplication.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Modaldata;
import com.example.myapplication.data.model.ModelHotile;
import com.example.myapplication.ui.SplachActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.OnClick {
    private RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;
    private ProgressBar progressBar;
    private HomeAdapter adapter;
    private List<Modaldata> list;
    private FragmentContainerView nav;
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.Progress);
        firebaseFirestore = FirebaseFirestore.getInstance();
        nav=findViewById(R.id.nav_host_fragment);
        title=findViewById(R.id.home);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        navController= Navigation.findNavController(this,R.id.nav_host_fragment);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        title.setText("Home");
                        navController.navigate(R.id.homeFragment);
                        return true;
                    case  R.id.nave_favorite:
                        title.setText("Favorite");
                        navController.navigate(R.id.favoriteFragment);
                        return  true;
                    case  R.id.nav_search:
                        title.setText("Search");
                        navController.navigate(R.id.searchFragment);
                        return true;
                    case  R.id.nav_profile:
                        title.setText("Profile");
                        navController.navigate(R.id.profileFragment);
                        return true;
                }
                return false;
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager((HomeActivity.this)));
        list=new ArrayList<>();
        adapter=new HomeAdapter(HomeActivity.this,list,this);
        recyclerView.setAdapter(adapter);
        getData();

    }
    @Override
    public void onItemClick(String pos) {
        if(pos.equals("0"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();
            startActivity(new Intent(HomeActivity.this, TourGuideActivity.class));
        }
        if(pos.equals("1"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();
            startActivity(new Intent(HomeActivity.this, CivilizationActivity.class));
        }
        if(pos.equals("2"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();
            startActivity(new Intent(HomeActivity.this, HotileActivity.class));
        }
        if(pos.equals("3"))
        {
            Toast.makeText(this,pos,Toast.LENGTH_LONG).show();
            startActivity(new Intent(HomeActivity.this, DriverActivity.class));
        }


    }


    private void getData(){
        progressBar.setVisibility(View.VISIBLE);
        firebaseFirestore.collection("Home")
                .orderBy("time", Query.Direction.DESCENDING)
                .addSnapshotListener((value, error) -> {

                    if (error==null){
                        if (value==null){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(HomeActivity.this,"value is null",Toast.LENGTH_SHORT).show();

                        }else {
                            for (DocumentChange documentChange: value.getDocumentChanges()){
                                Modaldata model=documentChange.getDocument().toObject(Modaldata.class);
                                list.add(model);
                                adapter.notifyDataSetChanged();

                            }
                            progressBar.setVisibility(View.GONE);
                        }

                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(HomeActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                });
}


}