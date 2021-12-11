package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText registerEmail;
    private Button login;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        //finview
        registerEmail=findViewById(R.id.registerEmail);
        progressBar=findViewById(R.id.registerPtogress);
        //firebase
        firebaseAuth=FirebaseAuth.getInstance();
        //onclickl
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationdata();
            }
        });
    }
    private void  validationdata(){
        String email=registerEmail.getText().toString().trim();
        if(email.isEmpty()){
            Toast.makeText(ForgetPasswordActivity.this, "Please add ur email", Toast.LENGTH_SHORT).show();
            registerEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(ForgetPasswordActivity.this, "Please add valid email", Toast.LENGTH_SHORT).show();
            registerEmail.requestFocus();
            return;
        }



        forgetpassword(email);
    }


    private void forgetpassword (String email) {
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                     if (task.isSuccessful()){
                         Toast.makeText(ForgetPasswordActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(ForgetPasswordActivity.this, VerificationCodeActivity.class));
                     }
                     else{
                         Toast.makeText(ForgetPasswordActivity.this, "try again something wrong happened!", Toast.LENGTH_SHORT).show();

                     }
                    }
                });


    }}
