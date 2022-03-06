package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.myapplication.ui.main.fragmant.ProfileFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class EditProfileActivity2 extends AppCompatActivity {
    private static final String TAG = "EditProfileActivity2";
    private static final String KEY_Name = "name";
    private static final String KEY_Phone = "phone";
    private static final String KEY_Nationality = "nationality";
    private static final String KEY_Gender = " gender";
    private static final String KEY_Day = "day";
    private static final String KEY_Month = "month";
    private static final String KEY_Year = "year";
    private static final String KEY_Image = "image";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final DocumentReference data = db.document("User/profile");

    private ProgressBar progressBar;
    private EditText Name, Phone;
    private Button savedata;
    private RadioButton Male, Female;

    private ImageView image;
    private FirebaseAuth firebaseAuth;
    private Spinner spinner1, spinner2, spinner3, spinner4;
    private FirebaseFirestore firestore;

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
        image = findViewById(R.id.edit);
        Male = findViewById(R.id.radio_Male);
        Female = findViewById(R.id.radio_Female);
        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        //onclickl
        savedata = findViewById(R.id.Savedata);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        boolean isDark = getSharedPreferences("theme", MODE_PRIVATE)

                .getBoolean("themeSelected", false);

        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }


    }


    public void Savedata(View v) {
        String name = Name.getText().toString();
        String phone = Phone.getText().toString();
        String male = Male.getText().toString();
        String female = Female.getText().toString();


        // Create a new user
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("nationality", spinner1.getSelectedItem());
        map.put("day", spinner2.getSelectedItem());
        map.put("month", spinner3.getSelectedItem());
        map.put("year", spinner4.getSelectedItem());
        map.put("gender", male);
        map.put("gender", female);
        map.put("phone", phone);
        map.put("image", "null");


        data.set(map)
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
                        Log.d(TAG, e.toString());
                    }
                });

    }

    public void UpdateUser(View v) {

        String name = Name.getText().toString();
        String phone = Phone.getText().toString();
        String Nationality = spinner1.getSelectedItem().toString();
        String Day = spinner2.getSelectedItem().toString();
        String Month = spinner3.getSelectedItem().toString();
        String Year = spinner4.getSelectedItem().toString();


        // HashMap<String, Object> map = new HashMap<>();
        // map.put(KEY_Name, name);
        // map.put(KEY_Phone,phone);
        //  map.put(KEY_Nationality, Nationality);
        // map.put(KEY_Day,Day);
        // map.put(KEY_Month,Month);
        // map.put(KEY_Year,Year);

        //data.set(map, SetOptions.merge());
        data.update(KEY_Name, name);
        data.update(KEY_Phone, phone);
        data.update(KEY_Nationality, Nationality);
        data.update(KEY_Day, Day);
        data.update(KEY_Month, Month);
        data.update(KEY_Year, Year);

        updateData();
        Toast.makeText(this, "updatedata", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditProfileActivity2.this  , ProfileFragment.class));


    }


    public void deletUser(View view) {

        // HashMap<String, Object> map = new HashMap<>();
        // map.put(KEY_Name, name);
        // map.put(KEY_Phone,phone);
        //  map.put(KEY_Nationality, Nationality);
        // map.put(KEY_Day,Day);
        // map.put(KEY_Month,Month);
        // map.put(KEY_Year,Year);

        // data.update(KEY_Name,name);
        // data.update(KEY_Phone,phone);
        // data.update(KEY_Nationality,Nationality);
        // data.update(KEY_Day,Day);
        //data.update(KEY_Month,Month);
        // data.update(KEY_Year,Year);

        data.update(KEY_Name, FieldValue.delete());
        data.update(KEY_Phone, FieldValue.delete());
        data.update(KEY_Nationality, FieldValue.delete());
        data.update(KEY_Day, FieldValue.delete());
        data.update(KEY_Month, FieldValue.delete());
        data.update(KEY_Year, FieldValue.delete());
    }


    public void Delete(View view) {
        startActivity(new Intent(EditProfileActivity2.this, ProfileFragment.class));
        data.delete();
    }


      public void   updateData(){
          data.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
              @Override
              public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                  if (e != null) {
                      Toast.makeText(EditProfileActivity2.this, "Error while loading !", Toast.LENGTH_SHORT).show();
                      Log.d(TAG, e.toString());
                      return;
                  }
                  if (documentSnapshot.exists()) {
                      String name = documentSnapshot.getString(KEY_Name);
                      String phone = documentSnapshot.getString(KEY_Phone);
                      // String Nationality = documentSnapshot.getString(KEY_Nationality);
                      //  String gender = documentSnapshot.getString(KEY_Gender);
                      //   String day = documentSnapshot.getString(KEY_Day);
                      //   String month = documentSnapshot.getString(KEY_Month);
                      //   String year = documentSnapshot.getString(KEY_Year);


                      Name.setText(name);
                      Phone.setText(phone);
                  } else {
                      Name.setText(" ");
                      Phone.setText(" ");

                  }
              }
          });
        }


}