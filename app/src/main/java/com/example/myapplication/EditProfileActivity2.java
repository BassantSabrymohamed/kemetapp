package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.ui.LoginActivity;
import com.example.myapplication.ui.auth.SignUpActivity;
import com.example.myapplication.ui.main.fragmant.ProfileFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Member;
import java.util.HashMap;

public class EditProfileActivity2 extends AppCompatActivity {
    private  static final String TAG="EditProfileActivity2";
    private static final String KEY_Name="name";
    private static final String KEY_Phone="phone";
    private static final String KEY_Nationality="nationality";
    private static final String KEY_Day="day";
    private static final String KEY_month="month";
    private static final String KEY_Year="year";
    private static final String KEY_Image="image";



    private ProgressBar progressBar;
    private EditText Name, Phone;
    private Button savedata;
    private RadioButton Male,Female;

    private ImageView image;
    private FirebaseAuth firebaseAuth;
    private Spinner spinner1,spinner2,spinner3,spinner4;
    private FirebaseFirestore firestore;
    int i=0;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile2);
        Name = findViewById(R.id.Name);
        Phone = findViewById(R.id.Phone);
        progressBar = findViewById(R.id.Progress);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        image=findViewById(R.id.edit);
        Male=findViewById(R.id.radio_Male);
        Female=findViewById(R.id.radio_Female);
        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
        //onclickl
        savedata = findViewById(R.id.Savedata);




    }
    public void Savedata (View v){
        String name=Name.getText().toString();
        String phone=Phone.getText().toString();
        String male=Male.getText().toString();
        String female=Female.getText().toString();
        // Create a new user
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("nationality", spinner1.getSelectedItem());
        map.put("day", spinner2.getSelectedItem());
        map.put("month", spinner3.getSelectedItem());
        map.put("year", spinner4.getSelectedItem());
        map.put("gender",male);
          map.put("gender",female);
        map.put("phone",phone);
        map.put("image","null");

        if (Male.isChecked()){

        }
        firestore.collection("profile").document("User").set(map)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EditProfileActivity2.this, "Save Data", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditProfileActivity2.this, ProfileFragment.class));

                    }
                })
                 .addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull @NotNull Exception e) {
                         Toast.makeText(EditProfileActivity2.this, "", Toast.LENGTH_SHORT).show();
                         Log.d(TAG,e.toString());
                     }
                 });
    }











    public void UserProfile(View view) {
        startActivity(new Intent(EditProfileActivity2.this, ProfileFragment.class));
    }
}