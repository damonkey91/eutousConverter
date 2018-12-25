package com.example.mrx.exchangeandusatoeuconverter.Objects;

import android.widget.EditText;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class Cell {

    private EditText editText;
    private SearchableSpinner searchableSpinner;

    public Cell(EditText editText, SearchableSpinner searchableSpinner) {
        this.editText = editText;
        this.searchableSpinner = searchableSpinner;
    }


}
