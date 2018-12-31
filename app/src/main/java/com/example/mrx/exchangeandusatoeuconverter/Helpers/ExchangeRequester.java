package com.example.mrx.exchangeandusatoeuconverter.Helpers;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ExchangeRequesterInterface;
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
import java.util.Arrays;
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
        //Todo: jason converting ska flyttas till classen jsonConverter och den ska ta generisk variabel
        ArrayList<ArrayList<String>> list = convertJson(response, strings);

        return new RequestResult(strings[2], list);
    }

    @Override
    public void onPostExecute(RequestResult resultObject) {
        super.onPostExecute(resultObject);

        if (resultObject.getList() != null){
            callingClass.gotRequestedList(resultObject.getList(), resultObject.getReguestType());
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
        Log.i("Response", response.toString());
        return  response;
    }

    private ArrayList<ArrayList<String>> convertJson(String jsonString, String[] strings){
        ArrayList<ArrayList<String>> list = null;
        if (jsonString != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                boolean sucess = Boolean.parseBoolean(jsonObject.getString("success"));
                if (sucess){
                    list = new ArrayList<>();
                    JSONObject object = jsonObject.getJSONObject(strings[1]);

                    Iterator x = object.keys();

                    while (x.hasNext()){
                        String key = (String) x.next();
                        list.add(new ArrayList<String>(Arrays.asList(key, object.getString(key))));
                    }
                    Log.i("", object.toString());

                }
/*
                JSONArray jsonArray = new JSONArray(jsonString);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                value = "" + jsonObject.getDouble("price_usd");
                */
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return list;
    }
}
