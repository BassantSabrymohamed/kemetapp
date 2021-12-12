package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);

        ModelSaveData modaldata=new ModelSaveData(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(modaldata.isUserLogin())
                {
                    startActivity(new Intent(SplachActivity.this, HomeActivity.class));

                    finish();

                }
                else

                {
                    startActivity(new Intent(SplachActivity.this, SingUpActivity.class));

                }

            }
        },3000);
    }
}