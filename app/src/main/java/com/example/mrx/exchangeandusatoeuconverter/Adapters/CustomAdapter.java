package com.example.mrx.exchangeandusatoeuconverter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mrx.exchangeandusatoeuconverter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrx on 2018-08-01.
 */

public class CustomAdapter extends BaseAdapter {

    private ArrayList<ArrayList<String>> currencyNameList;
    private LayoutInflater inflater;
    private Context context;
    private List<View> cellRows = new ArrayList<>();

    CustomAdapter(Context context, ArrayList<ArrayList<String>> list) {
    currencyNameList = list;
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.context = context;
    }

    @Override
    public int getCount() {
        return currencyNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return currencyNameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        if (convertView == null){
            view = inflater.inflate(R.layout.spinner_item_layout, parent,false);
        } else {
            view = convertView;
        }

        ((TextView)view.findViewById(R.id.spinnerFirstText)).setText(currencyNameList.get(position).get(0));
        ((TextView)view.findViewById(R.id.spinnerSecondText)).setText(currencyNameList.get(position).get(1));

        cellRows.add(view);
        return view;
    }
/*
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null){
            view = inflater.inflate(R.layout.spinner_dropdown_layout,parent, false);
        }else {
            view = convertView;
        }

        ListView list = view.findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.spinner_item_layout, cellRows);
        list.setAdapter(adapter);

        return view;
    }
    */

}
