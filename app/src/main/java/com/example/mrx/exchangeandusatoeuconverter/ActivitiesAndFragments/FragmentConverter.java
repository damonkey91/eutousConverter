package com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.mrx.exchangeandusatoeuconverter.Adapters.ArrayAdapterSpinner;
import com.example.mrx.exchangeandusatoeuconverter.Interfaces.IObserverUpdate;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Cell;
import com.example.mrx.exchangeandusatoeuconverter.R;
import com.example.mrx.exchangeandusatoeuconverter.ViewModels.ViewModelCurrency;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by mrx on 2018-07-15.
 */

public class FragmentConverter extends Fragment implements View.OnFocusChangeListener, TextWatcher, IObserverUpdate {

    private View view;
    private ViewModelCurrency viewModel;
    private ArrayList<Cell> cells = new ArrayList<>();
    private EditText focusedEditText;
    private ArrayAdapterSpinner spinnerAdapter;
    private ArrayList<ArrayList<String>> currencyValues;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_converter_tab2, container, false);
        viewModel = ViewModelProviders.of(getActivity()).get(ViewModelCurrency.class);
        createDynamicView();
        setupObservers();
        return view;
    }

    private void createDynamicView(){
        String textlist[] = new String[]{"1", "2", "333", "4444", "555555", "66666", "77777777777"};
        RelativeLayout relativeLayout = view.findViewById(R.id.relativlayout_fconverter);
        spinnerAdapter = new ArrayAdapterSpinner(getContext(), 0, new ArrayList<ArrayList<String>>());
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

    private void setupObservers() {
        viewModel.getCurrencyNameList().observe(this, createObserver(spinnerAdapter));
        viewModel.getCurrencyValueList().observe(this, createObserver(this));
    }

    private Observer createObserver(final IObserverUpdate target){
        final Observer<ArrayList<ArrayList<String>>> observer = new Observer<ArrayList<ArrayList<String>>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<ArrayList<String>> list) {
                target.update(list);
            }
        };
        return observer;
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

    @Override
    public void update(ArrayList<ArrayList<String>> list) {
        currencyValues = list;
    }
}
//Todo: första gången du startar appen så är spinners inte populerad
//Todo: Försök göra klart den befintliga
//Todo: gör en egen searchable spinner
//Todo: spinner list som ser ut som en tableview och går att söka i
//Todo: customAdapter som kan ta emot ArrayList<ArrayList<String>>
//Todo: