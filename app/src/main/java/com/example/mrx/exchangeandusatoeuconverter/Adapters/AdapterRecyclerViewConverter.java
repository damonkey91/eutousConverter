package com.example.mrx.exchangeandusatoeuconverter.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ICallbackRecyclerAdapter;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Measurment;
import com.example.mrx.exchangeandusatoeuconverter.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecyclerViewConverter extends RecyclerView.Adapter<AdapterRecyclerViewConverter.MyViewHolder> {

    private ICallbackRecyclerAdapter iCallback;
    private ArrayList<Measurment> measurments;

    public AdapterRecyclerViewConverter(ArrayList<Measurment> measurments, ICallbackRecyclerAdapter iCallback){
        this.measurments = measurments;
        this.iCallback = iCallback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_converter_list_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.measurmentTitleTV.setText(measurments.get(position).getName());
        //holder.measurmentImageIV.setImageDrawable();
        holder.sideColorIV.setBackgroundColor(Color.BLUE);
    }

    @Override
    public int getItemCount() {
        return measurments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView measurmentTitleTV;
        private ImageView sideColorIV;
        private ImageView measurmentImageIV;

        public MyViewHolder(View itemView) {
            super(itemView);
            measurmentTitleTV = itemView.findViewById(R.id.textViewTitle);
            sideColorIV = itemView.findViewById(R.id.imageViewSideColor);
            measurmentImageIV = itemView.findViewById(R.id.imageViewConverterType);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iCallback.callback(getAdapterPosition());
        }
    }
}


