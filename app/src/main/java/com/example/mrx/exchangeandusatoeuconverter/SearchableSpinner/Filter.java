package com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner;

import com.example.mrx.exchangeandusatoeuconverter.Objects.CurrencyName;
import com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.Interface.IFilterCallback;

import java.util.ArrayList;
import java.util.List;


public class Filter extends android.widget.Filter {

    private List<CurrencyName> list;
    private IFilterCallback callback;

    public Filter(List list, IFilterCallback callback){
        this.list = list;
        this.callback = callback;
    }

    @Override
    protected FilterResults performFiltering(CharSequence input) {
        String searchText = input.toString();
        List<CurrencyName> filteredList = new ArrayList<>();
        if (searchText.isEmpty()) {
            filteredList = list;
        } else {
            searchText = searchText.toLowerCase();
            for (CurrencyName currencyName : list) {
                if (currencyName.getShortName().toLowerCase().contains(searchText) || currencyName.getFullName().toLowerCase().contains(searchText)) {
                    filteredList.add(currencyName);
                }
            }
        }
        FilterResults filterResults = new FilterResults();
        filterResults.values = filteredList;
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        callback.filterResult((ArrayList<CurrencyName>) results.values);
    }
}
