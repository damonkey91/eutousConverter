package com.example.mrx.exchangeandusatoeuconverter.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.example.mrx.exchangeandusatoeuconverter.Adapters.ArrayAdapterSpinner;
import com.example.mrx.exchangeandusatoeuconverter.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by mrx on 2018-08-12.
 */

public class ListAdapterCustom extends BaseAdapter {

    private ArrayList<ArrayList<String>> spinnerList;
    private Context context;
    private ArrayList<ArrayList<String>> choosenCountries;

    public ListAdapterCustom(Context context, ArrayList<ArrayList<String>> spinnerList, ArrayList<ArrayList<String>> choosenCountries){
        this.spinnerList = spinnerList;
        this.context = context;
        this.choosenCountries = choosenCountries;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView;
        if (convertView == null){
            rowView = LayoutInflater.from(context).inflate(R.layout.list_row_layout, parent,false);
        } else {
            rowView = convertView;
        }

        SearchableSpinner spinner = rowView.findViewById(R.id.searchableSpinner);
        EditText editText = rowView.findViewById(R.id.editTextRow);
        spinner.setAdapter(new ArrayAdapterSpinner(context, 0, spinnerList));
        //spinner.get

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            editText.setShowSoftInputOnFocus(false);
        }else {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }

        return rowView;
    }

}

//Todo: create settings to chose how many rows to show and save choosen countries.
// If amount of rows changes saved countries should not crash because of to many or to few.