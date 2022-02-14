package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.MainActivity;
import com.example.myapplication.data.storage.ModelSaveData;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplachActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        firebaseAuth=FirebaseAuth.getInstance();



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
                    startActivity(new Intent(SplachActivity.this, LoginActivity.class));
                    finish();

                }

            }
        },3000);
    }
}