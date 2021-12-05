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

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText registerName, registerNationality, registerEmail, registerPass;
    private Button Signup;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //finfview
        registerName = findViewById(R.id.registerName);
        registerNationality = findViewById(R.id.registerNationality);
        registerEmail = findViewById(R.id.registerEmail);
        registerPass = findViewById(R.id.registerPass);
        progressBar = findViewById(R.id.registerPtogress);
        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        //onclickl
        Signup = findViewById(R.id.Signup);


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationdata();
            }
        });


    }

    private void validationdata() {
        String name = registerName.getText().toString().trim();
        String nationality = registerNationality.getText().toString().trim();
        String email = registerEmail.getText().toString().trim();
        String password = registerPass.getText().toString().trim();
        if (name.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter ur name", Toast.LENGTH_SHORT).show();
            registerName.requestFocus();
            return;
        }
        if (nationality.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter ur Nationality", Toast.LENGTH_SHORT).show();
            registerNationality.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please add ur email", Toast.LENGTH_SHORT).show();
            registerEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(RegisterActivity.this, "Please add valid email", Toast.LENGTH_SHORT).show();
            registerEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please add ur password", Toast.LENGTH_SHORT).show();
            registerPass.requestFocus();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(RegisterActivity.this, " password should be 6char", Toast.LENGTH_SHORT).show();
            registerPass.requestFocus();
            return;
        }

        register(email, password);
    }


    private void register(String email, String password) {
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "User Created Successfuly", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                        //handel error
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }







}