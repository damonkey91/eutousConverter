package com.example.mrx.exchangeandusatoeuconverter.Helpers;


import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonConverter {

    public static <T> T convertFromJson(String json, Type type){
        Gson gson = new Gson();
        T t = gson.fromJson(json, type);
        return t;
    }

    public static <T> String convertToJson(T convert){
        Gson gson = new Gson();
        String json = gson.toJson(convert);
        return json;
    }
}
