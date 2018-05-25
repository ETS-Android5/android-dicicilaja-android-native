package com.dicicilaja.dicicilaja.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.dicicilaja.dicicilaja.Activity.LoginActivity;
import com.dicicilaja.dicicilaja.Activity.OnBoardingActivity;

/**
 * Created by ziterz on 12/9/2017.
 */

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "DicicilajaPref";
    private static final String IS_LOGIN = "IsLoggedIn";

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String user_id, String token, String role, String name, String photo_profile_url, String branch, String area, String zipcode){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString("user_id", user_id);
        editor.putString("axi_id", user_id);
        editor.putString("name", name);
        editor.putString("role", role);
        editor.putString("photo", photo_profile_url);
        editor.putString("branch", branch);
        editor.putString("area", area);
        editor.putString("zipcode", zipcode);
        editor.putString("token", token);
        editor.commit();
    }

    public String getUserId() {
        return pref.getString("user_id", null);
    }

    public String getAxiId() {
        return pref.getString("axi_id", null);
    }

    public String getToken() {
        return pref.getString("token", null);
    }

    public String getRole() {
        return pref.getString("role", null);
    }

    public String getName() {
        return pref.getString("name", null);
    }

    public String getPhoto() {
        return pref.getString("photo", null);
    }

    public String getBranch() {
        return pref.getString("branch", null);
    }

    public String getArea() {
        return pref.getString("area", null);
    }

    public String getZipCode() {
        return pref.getString("zipcode", null);
    }



    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        if( ! this.isLoggedIn() ){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }


    /**
     * Quick check for login
     * **/
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
