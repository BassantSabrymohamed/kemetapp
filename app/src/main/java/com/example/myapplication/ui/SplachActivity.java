package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.MainActivity;
import com.example.myapplication.RegisterActivity;
import com.example.myapplication.data.storage.ModelSaveData;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class SplachActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        firebaseAuth=FirebaseAuth.getInstance();


        String currentLang= Locale.getDefault().getDisplayLanguage();

         String lang=getSharedPreferences("language",MODE_PRIVATE)
                 .getString("lang",currentLang);
        //language
        Locale l=new Locale(lang);
        Locale.setDefault(l);
        Configuration configuration=new Configuration();
        configuration.locale=l;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentUser= firebaseAuth.getCurrentUser();
                ModelSaveData modaldata=new ModelSaveData(SplachActivity.this);
                if(currentUser!= null&& modaldata.isUserLogin())
                {
                    startActivity(new Intent(SplachActivity.this, MainActivity.class));

                    finish();

                }
                else

                {
                    startActivity(new Intent(SplachActivity.this, RegisterActivity.class));
                    finish();

                }

            }
        },3000);
    }
}