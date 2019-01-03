package com.example.mrx.exchangeandusatoeuconverter.Objects;

import android.widget.EditText;

import com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.SearchableSpinner;

public class Cell {

    private int position;
    private EditText editText;
    private SearchableSpinner searchableSpinner;

    public Cell(EditText editText, SearchableSpinner searchableSpinner, int position) {
        this.editText = editText;
        this.searchableSpinner = searchableSpinner;
        this.position = position;
    }


    public EditText getEditText() {
        return editText;
    }

    public SearchableSpinner getSearchableSpinner() {
        return searchableSpinner;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getSpinnerKey(){
        CurrencyName selectedItem = (CurrencyName) getSearchableSpinner().getSelectedItem();
        return selectedItem.getShortName();
    }

    public void setEditText(String text){
        editText.setText(text);
    }
}
