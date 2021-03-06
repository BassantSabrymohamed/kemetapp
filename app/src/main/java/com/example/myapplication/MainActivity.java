package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.data.storage.ModelSaveData;
import com.example.myapplication.ui.SplachActivity;
import com.example.myapplication.ui.main.fragmant.DarkModeFragment;
import com.example.myapplication.ui.main.fragmant.HomeFragment;
import com.example.myapplication.ui.main.fragmant.ProfileFragment;
import com.example.myapplication.ui.main.fragmant.SettingFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private TextView title;
    private SwitchMaterial switchMaterial;
    LinearLayout av_language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        drawerLayout=findViewById(R.id.drawer);
        title=findViewById(R.id.title);
        navigationView=findViewById(R.id.nav_menu);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

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
            case R.id.nav_dark:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DarkModeFragment()).commit();
                break;
            case R.id.nav_profile:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
                break;
            case R.id.nav_logout:
               logout();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    private void logout() {
        ModelSaveData modaldata=new ModelSaveData(this);
        modaldata.deleteData();
        firebaseAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {
        Intent intent=new Intent(getApplicationContext(), SplachActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        SearchView searchView= (SearchView) menu.findItem(R.id.nav_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "Submit clicked", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, "text changed", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(MainActivity.this, "Search fininshed", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}