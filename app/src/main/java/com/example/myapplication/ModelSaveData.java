package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class ModelSaveData {
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    Context context ;

    private static final String file_name ="Bassant" ;
    private static final String key_email ="email" ;
    private static final String key_name ="name" ;
    private static final String isLogin = "status" ;

    public  ModelSaveData(Context  context)
    {
        sharedPreferences=context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void savData(String name , String email, boolean status)
    {
        editor.putString(key_email ,email);
        editor.putString(key_name ,name);
        editor.putBoolean(isLogin ,status);
        editor.apply();
        editor.commit();


    }
    public HashMap<String ,String> loadData()
    {
       HashMap<String ,String> user= new HashMap<>();
       user.put(key_email,sharedPreferences.getString(key_email,null));
        user.put(key_name,sharedPreferences.getString(key_name,null));

        return  user ;


    }

    public boolean isUserLogin()
    {
        return sharedPreferences.getBoolean(isLogin,false);
    }



}
