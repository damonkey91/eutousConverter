package com.example.mrx.exchangeandusatoeuconverter.Helpers;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.mrx.exchangeandusatoeuconverter.R;

import androidx.core.content.ContextCompat;

public class GetDrawable extends Application {

    private static GetDrawable instance;

    public GetDrawable() {
        instance = this;
    }

    public static Drawable getDrawable(String name) {
        name = name.equals("TRY") ? name + "1": name;
        try {
            int resourceId = instance.getResources().getIdentifier(name.toLowerCase(), "drawable", instance.getPackageName());
            return ContextCompat.getDrawable(instance, resourceId);
        }catch (Resources.NotFoundException e){
            return ContextCompat.getDrawable(instance, R.mipmap.ic_launcher);
        }
    }
}
