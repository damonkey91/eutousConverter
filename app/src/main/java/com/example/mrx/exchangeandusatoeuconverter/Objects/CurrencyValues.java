package com.example.mrx.exchangeandusatoeuconverter.Objects;

import java.util.HashMap;

public class CurrencyValues {

    private HashMap<String, Double> hashMapValues;

    public CurrencyValues(HashMap<String, Double> hashMapValues){

        this.hashMapValues = hashMapValues;
    }

    public boolean contains(String key){
        return hashMapValues.containsKey(key);
    }

    public boolean isEmpty(){
        return hashMapValues.isEmpty();
    }

    public double getValueFor(String key){
        return hashMapValues.get(key);
    }
}
