package com.example.mrx.exchangeandusatoeuconverter.Helpers;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.Toast;

import com.example.mrx.exchangeandusatoeuconverter.R;

public class ToastExtension {

    public static void showToast(Context context, String text, int duration){
        Toast toast = Toast.makeText(context, text, duration);
        View toastView = toast.getView();
        toastView.getBackground().setColorFilter(GetDrawable.getColorResource(R.color.toastBackground), PorterDuff.Mode.SRC_IN);
        toast.show();
   }


}
