package com.example.mrx.exchangeandusatoeuconverter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mrx.exchangeandusatoeuconverter.Objects.CurrencyName;
import com.example.mrx.exchangeandusatoeuconverter.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by mrx on 2018-08-06.
 */

public class ArrayAdapterSpinner extends ArrayAdapter<CurrencyName> {
    public ArrayAdapterSpinner(@NonNull Context context, int resource, @NonNull List<CurrencyName> objects) {
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

        ((TextView)view.findViewById(R.id.spinnerFirstText)).setText(getItem(position).getShortName());
        return view;
    }

    public void update(ArrayList<CurrencyName> list) {
        this.clear();
        this.addAll(list);
    }

}
