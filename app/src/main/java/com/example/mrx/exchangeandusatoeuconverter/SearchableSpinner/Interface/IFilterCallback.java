package com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.Interface;

import com.example.mrx.exchangeandusatoeuconverter.Objects.CurrencyName;

import java.util.List;

public interface IFilterCallback {
    public void filterResult(List<CurrencyName> filteredList);
}
