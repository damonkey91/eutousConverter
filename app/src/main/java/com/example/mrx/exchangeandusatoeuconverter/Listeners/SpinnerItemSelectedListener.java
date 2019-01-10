package com.example.mrx.exchangeandusatoeuconverter.Listeners;

import android.view.View;
import android.widget.AdapterView;

import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ICallbackSpinnerItemSelected;

public class SpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {

    private int index;
    private ICallbackSpinnerItemSelected callback;

    public SpinnerItemSelectedListener(int index, ICallbackSpinnerItemSelected callback) {
        this.index = index;
        this.callback = callback;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        callback.spinnerItemSelected(index, position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
