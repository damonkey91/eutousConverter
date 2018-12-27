package com.example.mrx.exchangeandusatoeuconverter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mrx.exchangeandusatoeuconverter.Objects.Unit;
import com.example.mrx.exchangeandusatoeuconverter.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecyclerViewUnit extends RecyclerView.Adapter<AdapterRecyclerViewUnit.MyViewHolder> {
    private ArrayList<Unit> units;

    public AdapterRecyclerViewUnit(ArrayList<Unit> units){
        this.units = units;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.unit_list_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.unitTitle.setText(units.get(position).getUnitName());
        holder.unitFullName.setText(units.get(position).getUnitFullName());
        //holder.unitInput.setText();
    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView unitTitle;
        private TextView unitFullName;
        private EditText unitInput;

        public MyViewHolder(View itemView) {
            super(itemView);
            unitTitle = itemView.findViewById(R.id.unitTitle);
            unitFullName = itemView.findViewById(R.id.unitFullName);
            unitInput = itemView.findViewById(R.id.unitInput);
        }
    }
}
