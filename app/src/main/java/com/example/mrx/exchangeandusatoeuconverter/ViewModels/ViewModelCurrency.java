package com.example.mrx.exchangeandusatoeuconverter.ViewModels;

import android.app.Application;

import com.example.mrx.exchangeandusatoeuconverter.Helpers.Constants;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.ExchangeRequester;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.JsonConverter;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.SharedPreferenceHelper;
import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ExchangeRequesterInterface;
import com.example.mrx.exchangeandusatoeuconverter.Objects.CurrencyName;
import com.example.mrx.exchangeandusatoeuconverter.Objects.CurrencyValues;
import com.example.mrx.exchangeandusatoeuconverter.Objects.RequestResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ViewModelCurrency extends AndroidViewModel implements ExchangeRequesterInterface {

    private SharedPreferenceHelper sharedPreferenceHelper;
    //Dessa två arrays ska vara liveData
    private MutableLiveData<ArrayList<CurrencyName>> currencyNameList;
    private MutableLiveData<CurrencyValues> currencyValueList;

    public ViewModelCurrency(@NonNull Application application) {
        super(application);
        currencyNameList = new MutableLiveData<>();
        currencyValueList = new MutableLiveData<>();
        sharedPreferenceHelper = new SharedPreferenceHelper(application);
    }

    public MutableLiveData<CurrencyValues> getCurrencyValueList() {
        String json = sharedPreferenceHelper.getStringFromSharedPreferences(Constants.CURRENCY_VALUES_KEY);
        String time = sharedPreferenceHelper.getStringFromSharedPreferences(Constants.TIMESTAMP_KEY);
        if (json == null || time == null || timeMoreThenSevenDays(time)) {
            requestCurrencyValues();
        } else {
            Type type = new TypeToken<CurrencyValues>() {}.getType();
            CurrencyValues list = JsonConverter.<CurrencyValues>convertFromJson(json, type);
            currencyValueList.setValue(list);
        }
        return currencyValueList;
    }

    public MutableLiveData<ArrayList<CurrencyName>> getCurrencyNameList() {
        String json = sharedPreferenceHelper.getStringFromSharedPreferences(Constants.CURRENCY_NAMES_KEY);
        if (json == null) {
            requestCurrencyNames();
        }else{
            Type type = new TypeToken<ArrayList<CurrencyName>>() {}.getType();
            currencyNameList.setValue(JsonConverter.<ArrayList<CurrencyName>>convertFromJson(json, type));
        }
        return currencyNameList;
    }

    private void requestCurrencyValues() {
        new ExchangeRequester(this).requestCurrencyValueList();
    }

    private void requestCurrencyNames() {
        new ExchangeRequester(this).requestCurrencyNameList();
    }

    @Override
    public void gotRequestedList(RequestResult requestResult) {
        String requestKey = requestResult.getRequestType();
        Object list = requestResult.getList();
        if(list != null){
            if (requestResult.getListSize() > 4) {
                if (requestResult.getRequestType() == Constants.CURRENCY_NAMES_KEY) {
                    String json = JsonConverter.convertToJson((ArrayList<CurrencyName>) requestResult.getList());
                    sharedPreferenceHelper.saveStringToSharedPreferences(json, requestResult.getRequestType());
                } else {
                    String json = JsonConverter.convertToJson((CurrencyValues) requestResult.getList());
                    sharedPreferenceHelper.saveStringToSharedPreferences(json, requestResult.getRequestType());
                }


            }

            switch (requestKey){
                case Constants.CURRENCY_NAMES_KEY:
                    currencyNameList.setValue((ArrayList<CurrencyName>) list);
                    break;
                case Constants.CURRENCY_VALUES_KEY:
                    sharedPreferenceHelper.saveStringToSharedPreferences(""+ new Date().getTime(), Constants.TIMESTAMP_KEY);
                    currencyValueList.setValue((CurrencyValues) list);
                    break;
            }
        }

    }

    private boolean timeMoreThenSevenDays(String time){
        final long sevenDays = 604800000;
        long timeNow = new Date().getTime();
        long oldTime = Long.parseLong(time);
        return timeNow - oldTime >= sevenDays;
    }
}
