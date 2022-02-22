package com.example.myapplication.ui.main.fragmant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.myapplication.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.jetbrains.annotations.NotNull;

import static android.content.Context.MODE_PRIVATE;


public class DarkModeFragment extends Fragment {
private SwitchMaterial switchMaterial;


    public DarkModeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dark_mode, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable  Bundle savedInstanceState) {
     //   if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
          //  setTheme(R.style.Theme_Dark);
       // }else{
        //   setTheme(R.style.Theme_Light);
       // }


        super.onViewCreated(view, savedInstanceState);
       // switchMaterial=view.findViewById(R.id.bt_switch);
      // switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           // @Override
            //public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
               // if (isChecked){
                //    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

              //  }else {
                  //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

               // }
           // }
       // });

        switchMaterial= view.findViewById(R.id.bt_switch);

        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    saveTheme(true);
                }else {
                    saveTheme(false);
                }

            }

            private void saveTheme(boolean b) {
               getActivity(). getSharedPreferences("theme",MODE_PRIVATE)
                        .edit()
                        .putBoolean("themeSelected",b)
                        .apply();

                if (b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
            }
        });
    }

   // private void setTheme(int theme_light) {
   // }
}