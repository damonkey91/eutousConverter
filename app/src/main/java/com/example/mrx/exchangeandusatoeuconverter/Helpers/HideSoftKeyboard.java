package com.example.mrx.exchangeandusatoeuconverter.Helpers;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class HideSoftKeyboard {

    public static void hide(View view){
        InputMethodManager imm = (InputMethodManager) GetDrawable.instance.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
