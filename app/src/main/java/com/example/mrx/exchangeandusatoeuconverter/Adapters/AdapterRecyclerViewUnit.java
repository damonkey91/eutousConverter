package com.example.mrx.exchangeandusatoeuconverter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mrx.exchangeandusatoeuconverter.Helpers.MyDecimalFormat;
import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ICallbackEditTextTextChanged;
import com.example.mrx.exchangeandusatoeuconverter.Listeners.ListenerFocusedEditTextListener;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Unit;
import com.example.mrx.exchangeandusatoeuconverter.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecyclerViewUnit extends RecyclerView.Adapter<AdapterRecyclerViewUnit.MyViewHolder> {

    private ICallbackEditTextTextChanged callback;
    private ArrayList<String> convertedValues;
    private ArrayList<EditText> editTexts = new ArrayList<>();
    private ArrayList<Unit> units;

    public AdapterRecyclerViewUnit(ArrayList<Unit> units, ICallbackEditTextTextChanged callback){
        this.units = units;
        this.callback = callback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.unit_list_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Unit unit = units.get(position);
        holder.unitTitle.setText(unit.getUnitName());
        holder.unitFullName.setText(unit.getUnitFullName());
        holder.unitInput.setOnFocusChangeListener(new ListenerFocusedEditTextListener(position, callback));
        String value = convertedValues == null ? "0" : convertedValues.get(position);
        holder.unitInput.setText(value);
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
            editTexts.add(unitInput);
        }
    }

    public void updateAllEditTexts(ArrayList<String> texts, int position){
        convertedValues = texts;
        for (int i = 0; i < editTexts.size() ; i++) {
            if (position != i) {
                String text = texts.get(i);
                editTexts.get(i).setText(text);
            }
        }
    }
}
