package com.dicicilaja.app.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.dicicilaja.app.Activity.LoginActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

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
    public void createLoginSession(String user_id, String nomor_axi_id, String profile_id, String user_id_onesignal, String token, String role, String name, String photo_profile_url, String branch, String branchId, String area, String zipcode,
                                   String firebase_token,
                                   String phone,
                                   String email){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString("user_id", user_id);
        editor.putString("nomor_axi_id", nomor_axi_id);
        editor.putString("profile_id", profile_id);
        editor.putString("user_id_onesignal", user_id_onesignal);
        editor.putString("name", name);
        editor.putString("role", role);
        editor.putString("photo", photo_profile_url);
        editor.putString("branch", branch);
        editor.putString("branch_id", branchId);
        editor.putString("area", area);
        editor.putString("zipcode", zipcode);
        editor.putString("token", token);
        editor.putString("firebase_token", firebase_token);
        editor.putString("phone", phone);
        editor.putString("email", email);
        editor.commit();
    }

    public void editLoginSession(String name, String zipcode){
        editor.putString("name", name);
        editor.putString("zipcode", zipcode);
        editor.commit();
    }

    public void editLoginSessionCustomer(String name){
        editor.putString("name", name);
        editor.apply();
    }

    public String getUserIdOneSignal() {
        return pref.getString("user_id_onesignal", null);
    }

    public String getUserId() {
        return pref.getString("user_id", null);
    }

    public String getNomorAxiId() {
        return pref.getString("nomor_axi_id", null);
    }

    public String getProfileId() {
        return pref.getString("profile_id", null);
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

    public String getBranchId() {
        return pref.getString("branch_id", null);
    }

    public String getArea() {
        return pref.getString("area", null);
    }

    public String getZipCode() {
        return pref.getString("zipcode", null);
    }

    public String getFirebaseToken() {
        return pref.getString("firebase_token", null);
    }

    public String getPhone() { return pref.getString("phone", null); }

    public String getEmail() { return pref.getString("email", null); }

    public void setPhone(String val) {
        editor.putString("phone", val);
        editor.commit();
    }

    public void setEmail( String val ) {
        editor.putString("email", val);
        editor.commit();
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
            i.setFlags(FLAG_ACTIVITY_NEW_TASK);
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
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        _context.startActivity(i);
    }


    /**
     * Quick check for login
     * **/
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
