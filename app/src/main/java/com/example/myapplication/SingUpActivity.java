package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SingUpActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText signupName, signupEmail, signupPass;
    private Button Signup;
    private FirebaseAuth firebaseAuth;
    private Spinner spinner;
  private   FirebaseFirestore firestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        //findview
        signupName = findViewById(R.id.Name);
        signupEmail = findViewById(R.id.Email);
        signupPass = findViewById(R.id.Pass);
        progressBar = findViewById(R.id.Progress);
        spinner = findViewById(R.id.spnner);
        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
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
        String name = signupName.getText().toString().trim();
        String email = signupEmail.getText().toString().trim();
        String password = signupPass.getText().toString().trim();
        if (name.isEmpty()) {
            Toast.makeText(SingUpActivity.this, "Please enter ur name", Toast.LENGTH_SHORT).show();
            signupName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            Toast.makeText(SingUpActivity.this, "Please add ur email", Toast.LENGTH_SHORT).show();
            signupEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(SingUpActivity.this, "Please add valid email", Toast.LENGTH_SHORT).show();
            signupEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(SingUpActivity.this, "Please add ur password", Toast.LENGTH_SHORT).show();
            signupPass.requestFocus();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(SingUpActivity.this, " password should be 6char", Toast.LENGTH_SHORT).show();
            signupPass.requestFocus();
            return;
        }

        register(email, password ,name);
        sendData(email,password,name);
    }





    private void register(String email, String password ,String name) {
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            ModelSaveData modelSaveData=new ModelSaveData(SingUpActivity.this);
                            modelSaveData.savData(name,email,true);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(SingUpActivity.this, "User Created Successfuly", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SingUpActivity.this, LoginActivity.class));
                        }
                        //handel error
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(SingUpActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });


    }
    private void sendData(String email, String password, String name) {
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
        String format = s.format(new Date());


        // Create a new user
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("na", spinner.getSelectedItem());
        map.put("email",email);
        map.put("password",password);
        map.put("timestamp",format);
// Add a new document with a generated ID
        firestore.collection("User")
                .document("1")
                .set(map)

        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SingUpActivity.this, "on Success", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        Toast.makeText(SingUpActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });


    }


    public void login(View view) {
        startActivity(new Intent(SingUpActivity.this, LoginActivity.class));
    }
}