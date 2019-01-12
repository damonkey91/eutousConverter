package com.example.mrx.exchangeandusatoeuconverter.Listeners;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ICallbackEditTextTextChanged;

public class ListenerFocusedEditTextListener implements View.OnFocusChangeListener, TextWatcher {

    private ICallbackEditTextTextChanged callback;
    private int position;

    public ListenerFocusedEditTextListener(int position, ICallbackEditTextTextChanged callback){
        this.position = position;
        this.callback = callback;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            ((EditText) v).addTextChangedListener(this);
        }else{
            ((EditText) v).removeTextChangedListener(this);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String text = s.toString();
        double input = 0;
        if (!text.isEmpty() && !text.matches("\\bE\\b")){
            input = Double.parseDouble(text);
        }
        callback.callbackTextChanged(position, input);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
