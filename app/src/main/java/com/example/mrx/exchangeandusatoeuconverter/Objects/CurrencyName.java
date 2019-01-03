package com.example.mrx.exchangeandusatoeuconverter.Objects;

public class CurrencyName {
    private String shortName;
    private String fullName;

    public CurrencyName(String shortName, String fullName){
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }
}
