package com.example.mrx.exchangeandusatoeuconverter.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPreferenceHelper {

    private static final String MY_SHARED_PREFERENCE_KEY = "theNameOfMySharedPreference";
    private SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context){
        sharedPreferences = context.getSharedPreferences(MY_SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
    }

    public String getStringFromSharedPreferences(String key){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, null);

        return json;
    }

    public void saveStringToSharedPreferences(String json, String key){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, json);
            editor.commit();
    }
}
