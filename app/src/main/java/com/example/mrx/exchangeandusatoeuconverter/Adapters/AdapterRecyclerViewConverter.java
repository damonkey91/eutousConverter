package com.example.mrx.exchangeandusatoeuconverter.Adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrx.exchangeandusatoeuconverter.Helpers.Constants;
import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ICallbackRecyclerAdapter;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Measurment;
import com.example.mrx.exchangeandusatoeuconverter.R;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecyclerViewConverter extends RecyclerView.Adapter<AdapterRecyclerViewConverter.MyViewHolder> {

    private ICallbackRecyclerAdapter iCallback;
    private ArrayList<Measurment> measurments;
    private ArrayList<Integer> colorList;
    private HashMap<String, Drawable> drawableList;

    public AdapterRecyclerViewConverter(ArrayList<Measurment> measurments, ICallbackRecyclerAdapter iCallback, ArrayList<Integer> colorList, HashMap<String, Drawable> drawableList){
        this.measurments = measurments;
        this.iCallback = iCallback;
        this.colorList = colorList;
        this.drawableList = drawableList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_converter_list_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Measurment measurment = measurments.get(position);
        holder.measurmentTitleTV.setText(measurment.getName());
        holder.measurmentTitleTV.setTextColor(colorList.get(position));
        holder.measurmentImageIV.setImageDrawable(getColeredDrawable(position, measurment.getConstant()));
        holder.sideColorIV.setBackgroundColor(colorList.get(position));
    }

    @Override
    public int getItemCount() {
        return measurments.size();
    }

    private Drawable getColeredDrawable(int position, String measureConstant){
        Drawable drawable = drawableList.containsKey(measureConstant) ? drawableList.get(measureConstant) : drawableList.get(Constants.NO_DRAWABLE);
        drawable.setColorFilter(new PorterDuffColorFilter(colorList.get(position), PorterDuff.Mode.SRC_IN));
        return drawable;
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


