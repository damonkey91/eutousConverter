package com.example.mrx.exchangeandusatoeuconverter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import static android.webkit.WebViewDatabase.getInstance;

import org.json.JSONArray;
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

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by mrx on 2018-07-16.
 */

public class ExchangeRequester extends AsyncTask<String, Void, ArrayList<ArrayList<String>>> {
    private final String API_KEY = "3423a5771f530cf0ca1c86a7981671c4";
    public final String[] ARRAY_STRING_ALL_CURRENCY_VALUES ={
            "http://www.apilayer.net/api/live?access_key=3423a5771f530cf0ca1c86a7981671c4&format=1", "quotes"};
    public final String[] ARRAY_STRING_LIST_OF_CURRENCYS = {
            "http://www.apilayer.net/api/list?access_key=3423a5771f530cf0ca1c86a7981671c4", "currencies"};

    private ExchangeRequesterInterface callingClass;
/*
    public void requestCurrencyList() {
        sendRequest(ARRAY_STRING_LIST_OF_CURRENCYS);
    }

    public void requestCurrencyValue() {
        sendRequest(ARRAY_STRING_ALL_CURRENCY_VALUES);
    }
*/

    public ExchangeRequester(ExchangeRequesterInterface callingClass) {
        this.callingClass = callingClass;
    }

    @Override
    public ArrayList<ArrayList<String>> doInBackground(String... strings) {

        String response = getJson(strings);
        ArrayList<ArrayList<String>> list = convertJson(response, strings);

        return list;
    }

    @Override
    public void onPostExecute(ArrayList<ArrayList<String>> list) {
        super.onPostExecute(list);

        if (list != null){
            callingClass.gotRequestedList(list);
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
        System.out.print(response);
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
