package com.example.mrx.exchangeandusatoeuconverter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrx on 2018-08-06.
 */

public class ArrayAdapterSpinner extends ArrayAdapter<ArrayList<String>> {

    public ArrayAdapterSpinner(@NonNull Context context, int resource, @NonNull List<ArrayList<String>> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item_layout, parent,false);
        } else {
            view = convertView;
        }

        ((TextView)view.findViewById(R.id.spinnerFirstText)).setText(getItem(position).get(0));
        return view;
    }
/*
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_dropdown_layout, parent,false);
        } else {
            view = convertView;
        }

        ((TextView)view.findViewById(R.id.spinnerFirstText)).setText(getItem(position).get(0));
        ((TextView)view.findViewById(R.id.spinnerSecondText)).setText(getItem(position).get(1));

        return view;
    }
*/


}
