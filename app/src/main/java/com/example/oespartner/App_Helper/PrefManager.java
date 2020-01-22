package com.example.oespartner.App_Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    // Shared preferences file name
    private static final String PREF_NAME = "InsectDefence";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String DEVICE_TOKEN = "NA";
    private static final String LOGIN_REMEMBER = "IsLogin";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHONE= "phone";
    private static final String KEY_ROLE= "role";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String MacAddress = "NoMac";

    @SuppressLint("CommitPrefEdits")
    public PrefManager(Context context) {
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void clearSharedPreference(Context context) {
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.clear();
        editor.apply();
    }

    public void setLoginRemember(String isFirstTime) {
        editor.putString(LOGIN_REMEMBER, isFirstTime);
        editor.apply();
    }



    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.apply();
    }

    public String getIsLogin() {
        return pref.getString(LOGIN_REMEMBER, LOGIN_REMEMBER);
    }

    public String getDeviceToken() {
        return pref.getString(DEVICE_TOKEN, DEVICE_TOKEN);
    }

    public void setDeviceToken(String deviceToken) {
        editor.putString(DEVICE_TOKEN, deviceToken);
        editor.apply();
    }

    public void setKeyEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }
    public void setKeyRole(String role) {
        editor.putString(KEY_ROLE, role);
        editor.apply();
    }
    public void setKeyPhone(String phone) {
        editor.putString(KEY_PHONE, phone);
        editor.apply();
    }

    public String getKeyEmail() {
        return pref.getString(KEY_EMAIL, KEY_EMAIL);
    }

    public String getKeyRole() {
        return pref.getString(KEY_ROLE, KEY_ROLE);
    }

    public String getKeyPhone() {
        return pref.getString(KEY_PHONE, KEY_PHONE);
    }
}