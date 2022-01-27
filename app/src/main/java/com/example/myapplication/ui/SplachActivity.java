package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.data.storage.ModelSaveData;
import com.example.myapplication.R;
import com.example.myapplication.ui.auth.SignUpActivity;
import com.example.myapplication.ui.main.HomeActivity;

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
                    startActivity(new Intent(SplachActivity.this, LoginActivity.class));
                    finish();

                }

            }
        },3000);
    }
}