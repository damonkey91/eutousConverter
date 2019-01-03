package com.example.mrx.exchangeandusatoeuconverter.Helpers;

import android.os.AsyncTask;

import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ExchangeRequesterInterface;
import com.example.mrx.exchangeandusatoeuconverter.Objects.CurrencyName;
import com.example.mrx.exchangeandusatoeuconverter.Objects.CurrencyValues;
import com.example.mrx.exchangeandusatoeuconverter.Objects.RequestResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by mrx on 2018-07-16.
 */

public class ExchangeRequester extends AsyncTask<String, Void, RequestResult> {

    private final String API_KEY = "3423a5771f530cf0ca1c86a7981671c4";
    public final String[] ARRAY_STRING_ALL_CURRENCY_VALUES ={
            "http://www.apilayer.net/api/live?access_key=3423a5771f530cf0ca1c86a7981671c4&format=1", "quotes", Constants.CURRENCY_VALUES_KEY};
    public final String[] ARRAY_STRING_LIST_OF_CURRENCYS = {
            "http://www.apilayer.net/api/list?access_key=3423a5771f530cf0ca1c86a7981671c4", "currencies",  Constants.CURRENCY_NAMES_KEY};

    private ExchangeRequesterInterface callingClass;
    private int listSize = 0;

    public ExchangeRequester(ExchangeRequesterInterface callingClass) {
        this.callingClass = callingClass;
    }

    public void requestCurrencyNameList() {
        this.execute(ARRAY_STRING_LIST_OF_CURRENCYS);
    }

    public void requestCurrencyValueList() {
        this.execute(ARRAY_STRING_ALL_CURRENCY_VALUES);
    }

    @Override
    public RequestResult doInBackground(String... strings) {

        String response = getJson(strings);
        Object list = convertJson(response, strings);
        return new RequestResult(strings[2], list, listSize);
    }

    @Override
    public void onPostExecute(RequestResult resultObject) {
        super.onPostExecute(resultObject);

        if (resultObject.getList() != null){
            callingClass.gotRequestedList(resultObject);
        }
    }

    private String getJson(String[] strings){
        String response = null;
        URL url;
        try {
            url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(5000);
            urlConnection.connect();

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String input;
            StringBuilder stringBuilder = new StringBuilder();
            while ((input = bufferedReader.readLine()) != null) {
                stringBuilder.append(input);
            }
            bufferedReader.close();
            urlConnection.disconnect();
            response = stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  response;
    }

    private Object convertJson(String jsonString, String[] strings){
        Object list = null;
        if (jsonString != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                boolean sucess = Boolean.parseBoolean(jsonObject.getString("success"));
                if (sucess){
                    JSONObject object = jsonObject.getJSONObject(strings[1]);
                    Iterator<String> iterator = object.keys();
                    if (Constants.CURRENCY_VALUES_KEY.equals(strings[2])){
                        list = createCurrencyValue(iterator, object);
                    } else if (Constants.CURRENCY_NAMES_KEY.equals(strings[2])) {
                        list = createCurrencyName(iterator, object);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    private Object createCurrencyName(Iterator<String> iterator, JSONObject jsonObject){
        ArrayList<CurrencyName> list = new ArrayList<>();
        while (iterator.hasNext()){
            String key = iterator.next();
            try {
                list.add(new CurrencyName(key, jsonObject.getString(key)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        listSize = list.size();
        return list;
    }

    private Object createCurrencyValue(Iterator<String> iterator, JSONObject jsonObject){
        HashMap<String, Double> hashMap = new HashMap<>();
        while (iterator.hasNext()){
            String key = iterator.next();
            String modifiedKey = key.replaceFirst("USD", "");
            try {
                hashMap.put(modifiedKey, jsonObject.getDouble(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        listSize = hashMap.size();
        return new CurrencyValues(hashMap);
    }
}
