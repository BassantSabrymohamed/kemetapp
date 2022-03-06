package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.auth.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private  static final String TAG="LoginActivity";
    private ProgressBar progressBar;
    private EditText Email,Pass;
    private Button login;
    private FirebaseAuth firebaseAuth;
    private LinearLayout linearLayoutgoogle;
    private LinearLayout linearLayoutface;
    private AuthCredential credential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //finview
        Email=findViewById(R.id.Email);
        Pass=findViewById(R.id.Pass);
        progressBar=findViewById(R.id.progress);
        linearLayoutgoogle=findViewById(R.id.btn_google);
        linearLayoutface=findViewById(R.id.btn_face);
        //firebase
        firebaseAuth=FirebaseAuth.getInstance();
       //darkmode
        boolean isDark=  getSharedPreferences("theme",MODE_PRIVATE)

                .getBoolean("themeSelected",false);

        if (isDark){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }


      //button back
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //onclickl
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationdata();
            }
        });
      linearLayoutgoogle.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              google();
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
                            finish();
                        }
                        //handel error
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }
    private void google() {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

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
