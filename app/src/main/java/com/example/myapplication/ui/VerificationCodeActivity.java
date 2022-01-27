package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.myapplication.R;

public class VerificationCodeActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText verificationcode;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        verificationcode=findViewById(R.id.Verification);
        progressBar=findViewById(R.id.Progress);
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerificationCodeActivity.this, NewPasswordActivity.class));
            }
        });

    }
}