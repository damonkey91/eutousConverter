package com.example.mrx.exchangeandusatoeuconverter.ViewModels;

import android.app.Application;

import com.example.mrx.exchangeandusatoeuconverter.Helpers.Constants;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.ExchangeRequester;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.JsonConverter;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.SharedPreferenceHelper;
import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ExchangeRequesterInterface;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ViewModelCurrency extends AndroidViewModel implements ExchangeRequesterInterface {

    private ExchangeRequester exchangeRequester;
    private SharedPreferenceHelper sharedPreferenceHelper;
    //Dessa två arrays ska vara liveData
    private MutableLiveData<ArrayList<ArrayList<String>>> currencyNameList;
    private MutableLiveData<ArrayList<ArrayList<String>>> currencyValueList;

    public ViewModelCurrency(@NonNull Application application) {
        super(application);
        currencyNameList = new MutableLiveData<ArrayList<ArrayList<String>>>();
        currencyValueList = new MutableLiveData<ArrayList<ArrayList<String>>>();
        exchangeRequester = new ExchangeRequester(this);
        sharedPreferenceHelper = new SharedPreferenceHelper(application);
    }

    public MutableLiveData<ArrayList<ArrayList<String>>> getCurrencyValueList() {
        String json = sharedPreferenceHelper.getStringFromSharedPreferences(Constants.CURRENCY_VALUES_KEY);
        if (json != null)
            currencyValueList.setValue(JsonConverter.<ArrayList<ArrayList<String>>>convertFromJson(json));
        requestCurrencyValues();
        return currencyValueList;
    }

    public MutableLiveData<ArrayList<ArrayList<String>>> getCurrencyNameList() {
        String json = sharedPreferenceHelper.getStringFromSharedPreferences(Constants.CURRENCY_NAMES_KEY);
        if (json == null) {
            requestCurrencyNames();
        }else{
            currencyNameList.setValue(JsonConverter.<ArrayList<ArrayList<String>>>convertFromJson(json));
        }
        return currencyNameList;
    }

    private void requestCurrencyValues() {
        String time = sharedPreferenceHelper.getStringFromSharedPreferences(Constants.TIMESTAMP_KEY);
        if (time == null || timeMoreThenSevenDays(time) )
            new ExchangeRequester(this).requestCurrencyValueList();
    }

    private void requestCurrencyNames() {
        exchangeRequester.requestCurrencyNameList();
    }

    @Override
    public void gotRequestedList(ArrayList<ArrayList<String>> list, String requestType) {
        if (list.size() > 4) {
            String json = JsonConverter.<ArrayList<ArrayList<String>>>convertToJson(list);
            sharedPreferenceHelper.saveStringToSharedPreferences(json, requestType);
        }

        switch (requestType){
            case Constants.CURRENCY_NAMES_KEY:
                currencyNameList.setValue(list);
                break;
            case Constants.CURRENCY_VALUES_KEY:
                sharedPreferenceHelper.saveStringToSharedPreferences(""+ new Date().getTime(), Constants.TIMESTAMP_KEY);
                currencyValueList.setValue(list);
                break;
        }
    }

    private boolean timeMoreThenSevenDays(String time){
        final long sevenDays = 604800000;
        long timeNow = new Date().getTime();
        long oldTime = Long.parseLong(time);
        return timeNow - oldTime >= sevenDays;
    }
}
