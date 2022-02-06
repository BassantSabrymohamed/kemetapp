package com.example.myapplication.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Modaldata;
import com.example.myapplication.data.model.ModelHotile;
import com.example.myapplication.ui.SplachActivity;
import com.example.myapplication.ui.main.fragmant.DarkModeFragment;
import com.example.myapplication.ui.main.fragmant.HomeFragment;
import com.example.myapplication.ui.main.fragmant.LanguageFragment;
import com.example.myapplication.ui.main.fragmant.LogoutFragment;
import com.example.myapplication.ui.main.fragmant.ProfileFragment;
import com.example.myapplication.ui.main.fragmant.SearchFragment;
import com.example.myapplication.ui.main.fragmant.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private ImageView Image;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //findViewbyid Menu
        Image=findViewById(R.id.image);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.nav_menu);
        toolbar=findViewById(R.id.toolbar);
        navigationView.setNavigationItemSelectedListener(this);





        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment());
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(HomeActivity.this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close);



        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment());
            navigationView.setCheckedItem(R.id.nav_home);
        }
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


    }



    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;

            case R.id.nav_setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SettingFragment()).commit();
                break;
            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SearchFragment()).commit();
                break;
            case R.id.nav_dark:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DarkModeFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
                break;
            case R.id.av_language:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LanguageFragment()).commit();
                break;
            case R.id.nav_logout:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LogoutFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);


        return false;
    }
}