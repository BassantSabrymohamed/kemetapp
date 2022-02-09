package com.example.myapplication.ui;

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

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.auth.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText Email,Pass;
    private Button login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //finview
        Email=findViewById(R.id.Email);
        Pass=findViewById(R.id.Pass);
        progressBar=findViewById(R.id.progress);
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
        String email=Email.getText().toString().trim();
        String password=Pass.getText().toString().trim();
        if(email.isEmpty()){
            Toast.makeText(LoginActivity.this, " add email", Toast.LENGTH_SHORT).show();
            Email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(LoginActivity.this, "Please add valid email", Toast.LENGTH_SHORT).show();
            Email.requestFocus();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(LoginActivity.this, "Please add ur password", Toast.LENGTH_SHORT).show();
            Pass.requestFocus();
            return;
        }
        if(password.length()<6){
            Toast.makeText(LoginActivity.this, " password should be 6char", Toast.LENGTH_SHORT).show();
            Pass.requestFocus();
            return;
        }

        register(email,password);
    }


    private void register(String email, String password) {
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()  {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "sing in Successfuly", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                        //handel error
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    public void forget(View view) {
        startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
    }

    public void signup(View view) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }
}
