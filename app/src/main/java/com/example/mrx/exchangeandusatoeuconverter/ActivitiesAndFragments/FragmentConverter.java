package com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.mrx.exchangeandusatoeuconverter.Adapters.ArrayAdapterSpinner;
import com.example.mrx.exchangeandusatoeuconverter.Adapters.ListAdapterCustom;
import com.example.mrx.exchangeandusatoeuconverter.ExchangeRequester;
import com.example.mrx.exchangeandusatoeuconverter.ExchangeRequesterInterface;
import com.example.mrx.exchangeandusatoeuconverter.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mrx on 2018-07-15.
 */

public class FragmentConverter extends Fragment implements View.OnClickListener, View.OnFocusChangeListener, ExchangeRequesterInterface {

    private ListAdapterCustom adapterListViewTest;

    private final String LIST_KEY = "exchangeCountryList";
    private View view;
    //private ListView listView;
    private ArrayList<ArrayList<String>> currencyNameList;
    private ArrayList<ArrayList<String>> currencyValueList;
    private List<Button> buttons = new ArrayList<>();
    private List<Integer> buttonIds = Arrays.asList(
            R.id.buttonNr0,
            R.id.buttonNr1,
            R.id.buttonNr2,
            R.id.buttonNr3,
            R.id.buttonNr4,
            R.id.buttonNr5,
            R.id.buttonNr6,
            R.id.buttonNr7,
            R.id.buttonNr8,
            R.id.buttonNr9,
            R.id.buttonNrComa,
            R.id.buttonNrDelete);

    private EditText focusedEditText;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_converter_tab2, container, false);

        /*
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
*/
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        currencyNameList = getListFromSharedPreferences();

        if (currencyNameList == null) {
            ExchangeRequester requester = new ExchangeRequester(this);
            requester.execute(requester.ARRAY_STRING_LIST_OF_CURRENCYS);
            currencyNameList = new ArrayList<>();
        } else {
            setupListView(view);
            //setupSpinners(view);
        }
/*
        SearchableSpinner spinnerTest = view.findViewById(R.id.searchableSpinnerTest);
        spinnerTest.setAdapter(new ArrayAdapterSpinner(getContext(), 0, currencyNameList));
*/
        //focusedEditText = view.findViewById(R.id.editText1);

        //setupEditText(view);
        getButtons(view);
        createDynamicView();

        return view;
    }
/*
    private void setupEditText(View view){
         List<Integer> editTextsId = Arrays.asList(
                R.id.editText1,
                R.id.editText2,
                R.id.editText3,
                R.id.editText4,
                R.id.editText5);
        for (Integer id : editTextsId) {
            view.findViewById(id).setOnFocusChangeListener(this);
        }
    }
    */

    private void getButtons(View view){

        for (Integer id: buttonIds) {
            Button button = view.findViewById(id);
            button.setOnClickListener(this);
            buttons.add(button);

        }
    }

    private void setupListView(View view){
        //listView = view.findViewById(R.id.listViewExchange);
        adapterListViewTest = new ListAdapterCustom(getContext(), currencyNameList, null);
        //listView.setAdapter(adapterListViewTest);
    }
/*
    private void setupSpinners(View view){
        List<Integer> spinnerIds = Arrays.asList(R.id.spinner1, R.id.spinner2, R.id.spinner3, R.id.spinner4, R.id.spinner5);
        ArrayAdapterSpinner adapter = new ArrayAdapterSpinner(getContext(), R.layout.spinner_item_layout, currencyNameList);
        //new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
SearchableSpinner searchableSpinner = view.findViewById(R.id.searchableSpinner1);
ArrayAdapterSpinner arrayAdapterSpinner = new ArrayAdapterSpinner(getContext(), R.layout.spinner_item_layout, currencyNameList);
searchableSpinner.setTitle("Countries");

searchableSpinner.setAdapter(arrayAdapterSpinner);
        for (Integer id : spinnerIds) {
            Spinner spinner = view.findViewById(id);
            spinner.setAdapter(adapter);
        }
    }
*/
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonNr0:
            case R.id.buttonNr1:
            case R.id.buttonNr2:
            case R.id.buttonNr3:
            case R.id.buttonNr4:
            case R.id.buttonNr5:
            case R.id.buttonNr6:
            case R.id.buttonNr7:
            case R.id.buttonNr8:
            case R.id.buttonNr9:
                Button button = v.findViewById(v.getId());
                //addchar(button.getText().toString());
                break;
            case R.id.buttonNrDelete:
                break;
            case R.id.buttonNrComa:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            focusedEditText = (EditText) v;
        }
    }

    private void addchar(String c){
        String s = focusedEditText.getText().toString();
        s = s+c;
        focusedEditText.setText(s);
    }

    @Override
    public void gotRequestedList(ArrayList<ArrayList<String>> list) {
        if (list != null) {
            saveListToSharedPreferences(list);
            currencyNameList = list;
            setupListView(view);
            //setupSpinners(view);
            /*
            String[] ss = new String[]{"hej", "tjena"};
            adapter.addAll(ss)*/
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

        for (int i = 0; i <= 5; i++){
            int etID = 100 + i;
            int spinnerID = 200 + i;
            RelativeLayout.LayoutParams lpSpinner = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams lpEditText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            SearchableSpinner searchableSpinner = new SearchableSpinner(getContext());
            searchableSpinner.setId(spinnerID);
            lpSpinner.setMargins(getMargin(), getMargin(), getMargin(), getMargin());
            lpSpinner.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lpSpinner.addRule(RelativeLayout.ALIGN_TOP, etID);
            lpSpinner.addRule(RelativeLayout.ALIGN_BOTTOM,etID);

            EditText editText = new EditText(getContext());
            editText.setId(etID);
            lpEditText.setMargins(getMargin(), getMargin(), getMargin(), getMargin());
            lpEditText.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            lpEditText.addRule(RelativeLayout.END_OF, spinnerID);
            lpEditText.addRule(RelativeLayout.RIGHT_OF, spinnerID);
            lpEditText.addRule(RelativeLayout.BELOW, etID-1);

            editText.setText(textlist[i]);

            relativeLayout.addView(searchableSpinner,lpSpinner);
            relativeLayout.addView(editText, lpEditText);

        }

    }

    private int getMargin(){
        int dpValue = 8;
        float d = getContext().getResources().getDisplayMetrics().density;
        int margin = (int)(dpValue * d);
        return margin;
    }
}

//Todo: spinner list som ser ut som en tableview och går att söka i
//Todo: customAdapter som kan ta emot ArrayList<ArrayList<String>>
//Todo: