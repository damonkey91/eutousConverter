package com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.mrx.exchangeandusatoeuconverter.Interfaces.IUpdate;
import com.example.mrx.exchangeandusatoeuconverter.Objects.CurrencyName;
import com.example.mrx.exchangeandusatoeuconverter.R;
import com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.Filter;
import com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.Interface.IFilterCallback;
import com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.Interface.IItemClickCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable, IFilterCallback, IUpdate {

    private List<CurrencyName> list;
    private List<CurrencyName> filteredList;
    private IItemClickCallback callback;

    public RecyclerViewAdapter(ArrayList<CurrencyName> list){
        this.list = list;
        filteredList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.searchable_list_dialog_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CurrencyName currencyName = filteredList.get(position);
        holder.title.setText(currencyName.getShortName());
        holder.description.setText(currencyName.getFullName());
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter(list, this);
    }

    @Override
    public void filterResult(List<CurrencyName> list) {
        filteredList = list;
        notifyDataSetChanged();
    }

    @Override
    public void update(ArrayList<CurrencyName> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title_currency_dialog);
            this.description = itemView.findViewById(R.id.description_dialog);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            callback.itemClicked(getAdapterPosition());
        }
    }

    public void setCallback(IItemClickCallback callback){
        this.callback = callback;
    }

}
