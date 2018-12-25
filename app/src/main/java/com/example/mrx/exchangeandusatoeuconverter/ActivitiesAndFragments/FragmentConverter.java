package com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.mrx.exchangeandusatoeuconverter.Adapters.ArrayAdapterSpinner;
import com.example.mrx.exchangeandusatoeuconverter.ExchangeRequester;
import com.example.mrx.exchangeandusatoeuconverter.ExchangeRequesterInterface;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Cell;
import com.example.mrx.exchangeandusatoeuconverter.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by mrx on 2018-07-15.
 */

public class FragmentConverter extends Fragment implements View.OnFocusChangeListener, TextWatcher, ExchangeRequesterInterface {

    private final String LIST_KEY = "exchangeCountryList";
    private View view;
    private ArrayList<ArrayList<String>> currencyNameList;
    private ArrayList<ArrayList<String>> currencyValueList;

    private ArrayList<Cell> cells = new ArrayList<>();
    private EditText focusedEditText;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_converter_tab2, container, false);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        currencyNameList = getListFromSharedPreferences();

        if (currencyNameList == null) {
            ExchangeRequester requester = new ExchangeRequester(this);
            requester.execute(requester.ARRAY_STRING_LIST_OF_CURRENCYS);
            currencyNameList = new ArrayList<>();
        }

        createDynamicView();

        return view;
    }

    @Override
    public void gotRequestedList(ArrayList<ArrayList<String>> list) {
        if (list != null) {
            saveListToSharedPreferences(list);
            currencyNameList = list;
        }
    }

    private ArrayList<ArrayList<String>> getListFromSharedPreferences(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LIST_KEY, null);

        if (json != null) {
            Type type = new TypeToken<ArrayList<ArrayList<String>>>() {
            }.getType();

            return gson.fromJson(json, type);
        }

        return null;
    }

    private void saveListToSharedPreferences(ArrayList<ArrayList<String>> list){
        if (list != null) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(LIST_KEY, json);
            editor.commit();
        }
    }

    private void createDynamicView(){
        String textlist[] = new String[]{"1", "2", "333", "4444", "555555", "66666", "77777777777"};
        RelativeLayout relativeLayout = view.findViewById(R.id.relativlayout_fconverter);
        ArrayAdapterSpinner spinnerAdapter = new ArrayAdapterSpinner(getContext(), 0, currencyNameList);
        for (int i = 0; i <= 5; i++){
            int etID = 100 + i;
            int spinnerID = 200 + i;
            RelativeLayout.LayoutParams lpSpinner = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams lpEditText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            SearchableSpinner searchableSpinner = new SearchableSpinner(getContext());
            searchableSpinner.setAdapter(spinnerAdapter);
            searchableSpinner.setId(spinnerID);
            lpSpinner.setMargins(getMargin(), getMargin(), getMargin(), getMargin());
            lpSpinner.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lpSpinner.addRule(RelativeLayout.ALIGN_TOP, etID);
            lpSpinner.addRule(RelativeLayout.ALIGN_BOTTOM,etID);

            EditText editText = new EditText(getContext());
            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            editText.setOnFocusChangeListener(this);
            editText.setId(etID);
            lpEditText.setMargins(getMargin(), getMargin(), getMargin(), getMargin());
            lpEditText.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            lpEditText.addRule(RelativeLayout.END_OF, spinnerID);
            lpEditText.addRule(RelativeLayout.RIGHT_OF, spinnerID);
            lpEditText.addRule(RelativeLayout.BELOW, etID-1);

            editText.setText(textlist[i]);

            relativeLayout.addView(searchableSpinner,lpSpinner);
            relativeLayout.addView(editText, lpEditText);
            cells.add(new Cell(editText, searchableSpinner));
        }
    }

    private int getMargin(){
        int dpValue = 8;
        float d = getContext().getResources().getDisplayMetrics().density;
        int margin = (int)(dpValue * d);
        return margin;
    }

    private double calculateCurrency(){
        for (Cell cell : cells) {

        }
        return 0;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //Todo: När texten ändras ska en beräknare anropas som tar värdet ur den aktiva edittext och räknar om värdet till usd. Därefter berälnas alla andra edit texts.
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            focusedEditText = (EditText) v;
            focusedEditText.addTextChangedListener(this);
        } else {
            focusedEditText.removeTextChangedListener(this);
        }
    }
}

//Todo: spinner list som ser ut som en tableview och går att söka i
//Todo: customAdapter som kan ta emot ArrayList<ArrayList<String>>
//Todo: