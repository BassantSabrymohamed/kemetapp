package com.example.myapplication.ui.main.fragmant;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.EditProfileActivity2;
import com.example.myapplication.R;
import com.example.myapplication.ui.LoginActivity;
import com.example.myapplication.ui.auth.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.SetOptions;

import org.jetbrains.annotations.NotNull;

import java.time.MonthDay;
import java.time.Year;
import java.util.HashMap;


public class ProfileFragment extends Fragment {

    private  static final String TAG="EditProfileActivity2";
    // SharedPreferences key,value
    private static final String KEY_Name="name";
    private static final String KEY_Phone="phone";
    private static final String KEY_Nationality="nationality";
    private static final String KEY_Gender=" gender";
    private static final String KEY_Day="day";
    private static final String KEY_Month="month";
    private static final String KEY_Year="year";
    private static final String KEY_Image="image";

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private DocumentReference data=db.document("User/profile");




    private ImageView Image,Image1;
    private TextView Name;
    private TextView Phone;
    private TextView nationality;
    private TextView Gender;
    private TextView Day;
    private TextView Month;
    private TextView Yaer;

    private ProgressBar progressBar;
    private FirebaseFirestore firestore;
    private FirebaseAuth firebaseAuth;



    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable  Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        //findviewbyid xml:
        Image=view.findViewById(R.id.edit);
        Name = view.findViewById(R.id.Name);
        Phone = view.findViewById(R.id.phone);
       nationality = view.findViewById(R.id.nationality);
       Gender=view.findViewById(R.id.gender);
        Day =view. findViewById(R.id.day);
       Month = view.findViewById(R.id.month);
        Yaer = view.findViewById(R.id.year);
        Image1=view.findViewById(R.id.imageView);

        //findviewbyid firebase:
        progressBar =view. findViewById(R.id.Progress);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();

        //onclick back
        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfileActivity2.class));


            }
        });

       //method loaddata:
       loaddate();


    }
    public void loaddate(){
        data.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String name=documentSnapshot.getString(KEY_Name);
                            String phone=documentSnapshot.getString(KEY_Phone);
                            String Nationality=documentSnapshot.getString(KEY_Nationality);
                            String gender=documentSnapshot.getString(KEY_Gender);
                            String day=documentSnapshot.getString(KEY_Day);
                            String month=documentSnapshot.getString(KEY_Month);
                            String year=documentSnapshot.getString(KEY_Year);


                            Name.setText("Name :" +name);
                            Phone.setText("Phone :" +phone);
                            nationality.setText("Nationality :" +Nationality);
                            Gender.setText("Gender :"+ gender);
                            Day.setText("Day :" +day);
                            Month.setText("Month :" +month);
                            Yaer.setText("Year :" +year);



                        }else {
                            Toast.makeText(getActivity(), "Document does not exists", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,e.toString());

                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        loaddate();
    }
}