package com.example.mrx.exchangeandusatoeuconverter.Helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonConverter {

    public static <T> T convertFromJson(String json){
        Type type = new TypeToken<T>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }


    public static <T> String convertToJson(T convert){
        Gson gson = new Gson();
        String json = gson.toJson(convert);
        return json;
    }
}
