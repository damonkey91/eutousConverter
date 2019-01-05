package com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrx.exchangeandusatoeuconverter.Helpers.GetDrawable;
import com.example.mrx.exchangeandusatoeuconverter.Interfaces.IUpdate;
import com.example.mrx.exchangeandusatoeuconverter.Objects.CurrencyName;
import com.example.mrx.exchangeandusatoeuconverter.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SpinnerAdapter extends ArrayAdapter<CurrencyName> implements IUpdate {

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<CurrencyName> objects) {
        super(context, resource, objects);
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
        CurrencyName currencyName = getItem(position);
        ((TextView)view.findViewById(R.id.spinnerFirstText)).setText(currencyName.getShortName());
        ((ImageView) view.findViewById(R.id.spinnerFlagImage)).setImageDrawable(GetDrawable.getDrawable(currencyName.getShortName()));

        return view;
    }

    @Override
    public void update(ArrayList<CurrencyName> list) {
        this.clear();
        this.addAll(list);
        notifyDataSetChanged();
    }
}
