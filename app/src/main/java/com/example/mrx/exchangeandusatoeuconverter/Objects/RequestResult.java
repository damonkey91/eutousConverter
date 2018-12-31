package com.example.mrx.exchangeandusatoeuconverter.Objects;

import java.util.ArrayList;

public class RequestResult {

    private String reguestType;
    private ArrayList<ArrayList<String>> list;

    public RequestResult(String reguestType, ArrayList<ArrayList<String>> list) {
        this.reguestType = reguestType;
        this.list = list;
    }


    public String getReguestType() {
        return reguestType;
    }

    public ArrayList<ArrayList<String>> getList() {
        return list;
    }
}
