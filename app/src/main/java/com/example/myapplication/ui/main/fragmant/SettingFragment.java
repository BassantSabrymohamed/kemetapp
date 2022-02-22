package com.example.myapplication.ui.main.fragmant;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ChangePasswordActivity;
import com.example.myapplication.EditProfileActivity2;
import com.example.myapplication.R;
import com.example.myapplication.ui.SplachActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


public class SettingFragment extends Fragment {
    private  static final String TAG="EditProfileActivity2";
    private static final String KEY_Name="name";
    private static final String KEY_Phone="phone";

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private DocumentReference data=db.document("User/profile");

    private TextView Name;
    private TextView Phone;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    LinearLayout btn_language;
    ImageView save;
    private TextView selectedLang;
    private  String lang;



    public SettingFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Name = view.findViewById(R.id.Name);
        Phone = view.findViewById(R.id.phone);
        progressBar=view.findViewById(R.id.progress);
        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
        selectedLang=view.findViewById(R.id.selectedLang);

        String currentLang= Locale.getDefault().getDisplayLanguage();

        lang=getActivity().getSharedPreferences("language",MODE_PRIVATE)
                .getString("lang",currentLang);

        if (lang.equals("ar"))
            selectedLang.setText("عربي");
        else
            selectedLang.setText("English");


        view.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfileActivity2.class));
            }
        });
        view.findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
            }
        });
         view.findViewById(R.id.btn_language).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
              showAlertLanguage();
             }
         });
        loaddate();
    }

    private void showAlertLanguage() {
        AlertDialog alert= new AlertDialog.Builder(getContext()).create();
        alert.setCancelable(false);
        View view=LayoutInflater.from(getContext()).inflate(R.layout.chooselanguage,null);
        alert.setView(view);
        ImageView clear=view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });

        RadioButton radio_en=view.findViewById(R.id.radio_english);
        RadioButton radio_ar=view.findViewById(R.id.radio_arabic);
        if (lang.equals("ar")){
            radio_ar.setChecked(true);
            radio_en.setChecked(false);
        }else {
            radio_ar.setChecked(false);
            radio_en.setChecked(true);
        }




       view.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radio_en.isChecked())
                    saveLanguage("en");
                else if (radio_ar.isChecked())
                    saveLanguage("ar");

            }
        });
        alert.show();


    }
    void saveLanguage(String lang){
        getActivity().getSharedPreferences("language",MODE_PRIVATE)
                .edit()
                .putString("lang",lang)
                .apply();


        //language
        Locale l=new Locale(lang);
        Locale.setDefault(l);
        Configuration configuration=new Configuration();
        configuration.locale=l;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        Intent intent=new Intent(getActivity(), SplachActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void loaddate(){
        data.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String name=documentSnapshot.getString(KEY_Name);
                            String phone=documentSnapshot.getString(KEY_Phone);



                            Name.setText("Name :" +name);
                            Phone.setText("Phone :" +phone);




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

}