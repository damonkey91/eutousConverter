package com.example.mrx.exchangeandusatoeuconverter.Objects;

public class RequestResult {

    private String requestType;
    private Object list;
    private int listSize;

    public RequestResult(String requestType, Object list, int listSize) {
        this.requestType = requestType;
        this.list = list;
        this.listSize = listSize;
    }


    public String getRequestType() {
        return requestType;
    }

    public Object getList() {
        return list;
    }

    public int getListSize() {
        return listSize;
    }
}
