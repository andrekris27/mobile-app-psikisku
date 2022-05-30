package com.example.uasmobile_psikisku;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.uasmobile_psikisku.model.login.LoginData;

import java.util.HashMap;

public class SessionManager2 {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isID";
    public static final String USER_ID = "user_id";


    public SessionManager2 (Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(LoginData user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(USER_ID, user.getUserId());

        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(USER_ID, sharedPreferences.getString(USER_ID,null));

        return user;
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isID (){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

}

