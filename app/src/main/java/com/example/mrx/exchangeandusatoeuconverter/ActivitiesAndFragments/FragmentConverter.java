package com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mrx.exchangeandusatoeuconverter.Helpers.GetDrawable;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.MyDecimalFormat;
import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ICallbackSpinnerItemSelected;
import com.example.mrx.exchangeandusatoeuconverter.Listeners.SpinnerItemSelectedListener;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Cell;
import com.example.mrx.exchangeandusatoeuconverter.Objects.CurrencyName;
import com.example.mrx.exchangeandusatoeuconverter.Objects.CurrencyValues;
import com.example.mrx.exchangeandusatoeuconverter.R;
import com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.Adapters.RecyclerViewAdapter;
import com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.Adapters.SpinnerAdapter;
import com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.SearchableSpinner;
import com.example.mrx.exchangeandusatoeuconverter.ViewModels.ViewModelCurrency;

import java.text.DecimalFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import static com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments.SettingsActivity.AMOUNT_OF_CURRENCIES_KEY;
import static com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments.SettingsActivity.SHARED_PREFS_KEY;

/**
 * Created by mrx on 2018-07-15.
 */

public class FragmentConverter extends Fragment implements View.OnFocusChangeListener, TextWatcher, ICallbackSpinnerItemSelected {

    private View view;
    private ViewModelCurrency viewModel;
    private ArrayList<Cell> cells = new ArrayList<>();
    private Cell focusedCell;
    private CurrencyValues currencyValues;
    private SpinnerAdapter spinnerAdapter;
    private ArrayList<RecyclerViewAdapter> adapterList = new ArrayList<>();
    private int amountOfCurrencies;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_converter_tab2, container, false);
        viewModel = ViewModelProviders.of(getActivity()).get(ViewModelCurrency.class);
        createDynamicView();
        setupObservers();
        setFocus();
        return view;
    }

    private void setFocus() {
        focusedCell = cells.get(0);
        EditText editText = cells.get(0).getEditText();
        editText.setText("1");
        editText.requestFocus();
    }

    private void createDynamicView(){
        RelativeLayout relativeLayout = view.findViewById(R.id.relativlayout_fconverter);
        relativeLayout.removeAllViews();
        spinnerAdapter = new SpinnerAdapter(getContext(), 0, new ArrayList<CurrencyName>());
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        amountOfCurrencies = Integer.parseInt(sharedPreferences.getString(AMOUNT_OF_CURRENCIES_KEY, "5"));

        for (int i = 0; i < amountOfCurrencies; i++){
            int etID = 100 + i;
            int spinnerID = 200 + i;
            RelativeLayout.LayoutParams lpSpinner = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams lpEditText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams lpline = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, convertToDP(1));

            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<CurrencyName>());

            SearchableSpinner searchableSpinner = new SearchableSpinner(getContext());
            searchableSpinner.setAdapters(spinnerAdapter, recyclerViewAdapter);
            searchableSpinner.setId(spinnerID);
            searchableSpinner.setSelection(viewModel.getChoosenCurrency(i));
            searchableSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener(i, this));
            lpSpinner.setMargins(convertToDP(8), convertToDP(8), convertToDP(8), convertToDP(8));
            lpSpinner.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lpSpinner.addRule(RelativeLayout.BELOW, spinnerID-1);

            adapterList.add(recyclerViewAdapter);

            EditText editText = new EditText(getContext());
            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            editText.setOnFocusChangeListener(this);
            editText.setId(etID);
            editText.setBackgroundResource(R.drawable.edittext_background_square);
            lpEditText.setMargins(convertToDP(8), 0, convertToDP(8), 0);
            lpEditText.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            lpEditText.addRule(RelativeLayout.END_OF, spinnerID);
            lpEditText.addRule(RelativeLayout.RIGHT_OF, spinnerID);
            lpEditText.addRule(RelativeLayout.ALIGN_BOTTOM, spinnerID);
            lpEditText.addRule(RelativeLayout.ALIGN_TOP, spinnerID);
            editText.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
            editText.setTextColor(GetDrawable.getColorResource(R.color.colorText));

            LinearLayout line = new LinearLayout(getContext());
            line.setBackgroundColor(GetDrawable.getColorResource(R.color.colorDividerLine));
            lpline.addRule(RelativeLayout.BELOW, spinnerID);
            lpline.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            lpline.addRule(RelativeLayout.ALIGN_PARENT_LEFT);


            InputFilter[] filterArray = new InputFilter[1];
            filterArray[0] = new InputFilter.LengthFilter(20);
            editText.setFilters(filterArray);

            relativeLayout.addView(searchableSpinner,lpSpinner);
            relativeLayout.addView(editText, lpEditText);
            relativeLayout.addView(line, lpline);
            cells.add(new Cell(editText, searchableSpinner, i));
        }
    }

    private int convertToDP(int dpValue){
        float d = getContext().getResources().getDisplayMetrics().density;
        int margin = (int)(dpValue * d);
        return margin;
    }

    private void setupObservers() {
        viewModel.getCurrencyNameList().observe(this, new Observer<ArrayList<CurrencyName>>() {
            @Override
            public void onChanged(ArrayList<CurrencyName> currencyNames) {
                spinnerAdapter.update(currencyNames);
                for (RecyclerViewAdapter adapter : adapterList) {
                    adapter.update(currencyNames);
                }
            }
        });
        final FragmentConverter target = this;
        viewModel.getCurrencyValueList().observe(this, new Observer<CurrencyValues>() {
            @Override
            public void onChanged(CurrencyValues currencyValues) {
                target.currencyValues = currencyValues;
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //When text changed, the usd value for the focused editText is calculated and then the rest editText are calculated.
        calculateCurrencys();
    }

    private void calculateCurrencys() {
        String currencyKey = focusedCell.getSpinnerKey();
        if (currencyValues.contains(currencyKey)){
            double usd = calculateUsdForFocusedCell();
            for (Cell cell : cells) {
                calculateCurrencyForCell(cell, usd);
            }
        } else {
            toastNoCurrencyValue(currencyKey);
        }
    }

    private double calculateUsdForFocusedCell(){
        String text = focusedCell.getEditText().getText().toString();
        double inputValue = MyDecimalFormat.ParseStringToDouble(text);
        double focusedValue = currencyValues.getValueFor(focusedCell.getSpinnerKey());
        double usd = inputValue / focusedValue;
        return usd;
    }

    private double calculateCurrencyForCell(Cell cell, double usdValue){
        if (cell != focusedCell){
            String key = cell.getSpinnerKey();
            if (currencyValues.contains(key)) {
                double value = currencyValues.getValueFor(key);
                String ss = MyDecimalFormat.formatDecimal(value * usdValue);
                cell.setEditText(ss);
            } else {
                toastNoCurrencyValue(key);
            }
        }
        return 0;
    }

    private void toastNoCurrencyValue(String currencyKey) {
        Toast.makeText(getContext(), "No value for " + currencyKey, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            int id = v.getId();
            focusedCell = cells.get(id-100);
            focusedCell.getEditText().addTextChangedListener(this);
        } else {
            ((EditText) v).removeTextChangedListener(this);
        }
    }

    @Override
    public void spinnerItemSelected(int cellPosition, int choosenItemPosition) {
        viewModel.setChoosenCurrency(cellPosition, choosenItemPosition);
        updateEditTextCurrencys(cells.get(cellPosition));
    }

    private void updateEditTextCurrencys(Cell cell) {
        if (cell != focusedCell)
            calculateCurrencyForCell(cell, calculateUsdForFocusedCell());
        else
            calculateCurrencys();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                ((Exchange) getActivity()).openSettings();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        int tmpCurrencies = Integer.parseInt(sharedPreferences.getString(AMOUNT_OF_CURRENCIES_KEY, "0"));
        if (amountOfCurrencies != tmpCurrencies){
            cells = new ArrayList<>();
            focusedCell = null;
            createDynamicView();
            setupObservers();
            setFocus();
        }
    }
}

