package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapplication.ui.LoginActivity;
import com.example.myapplication.ui.auth.SignUpActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void signin(View view) {
        startActivity(new Intent(RegisterActivity.this, SignUpActivity.class));
    }

    public void login(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }
}