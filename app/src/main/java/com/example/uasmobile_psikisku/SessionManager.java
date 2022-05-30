package com.example.uasmobile_psikisku;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.example.uasmobile_psikisku.model.login.LoginData;
import com.example.uasmobile_psikisku.model.update.UpdateData;

import java.util.HashMap;

public class SessionManager {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String IS_UPDATE_IN = "UpdateIN";
    public static final String USER_ID = "id";
    public static final String USERNAME = "username";
    public static final String Email = "Email";

    public SessionManager (Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(LoginData user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(USER_ID, user.getUserId());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(Email, user.getEmail());
        editor.commit();
    }
    public void createUpdateSession(UpdateData user){
        editor.putBoolean(IS_UPDATE_IN, true);
        editor.putString(USER_ID, user.getId());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(Email, user.getEmail());
        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(USER_ID, sharedPreferences.getString(USER_ID,null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME,null));
        user.put(Email, sharedPreferences.getString(Email,null));
        return user;
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

}
