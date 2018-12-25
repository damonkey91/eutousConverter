package com.example.mrx.exchangeandusatoeuconverter.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrx.exchangeandusatoeuconverter.R;

public class AdapterRecyclerViewConverter extends RecyclerView.Adapter<AdapterRecyclerViewConverter.MyViewHolder> {

    public AdapterRecyclerViewConverter(){

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_converter_list_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}


