package com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
        spinnerAdapter = new SpinnerAdapter(getContext(), 0, new ArrayList<CurrencyName>());

        for (int i = 0; i <= 5; i++){
            int etID = 100 + i;
            int spinnerID = 200 + i;
            RelativeLayout.LayoutParams lpSpinner = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams lpEditText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<CurrencyName>());

            SearchableSpinner searchableSpinner = new SearchableSpinner(getContext());
            searchableSpinner.setAdapters(spinnerAdapter, recyclerViewAdapter);
            searchableSpinner.setId(spinnerID);
            searchableSpinner.setSelection(viewModel.getChoosenCurrency(i));
            searchableSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener(i, this));
            lpSpinner.setMargins(getMargin(), getMargin(), getMargin(), getMargin());
            lpSpinner.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lpSpinner.addRule(RelativeLayout.BELOW, spinnerID-1);

            adapterList.add(recyclerViewAdapter);

            EditText editText = new EditText(getContext());
            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            editText.setOnFocusChangeListener(this);
            editText.setId(etID);
            editText.setBackgroundResource(R.drawable.edittext_background_square);
            lpEditText.setMargins(getMargin(), getMargin(), getMargin(), getMargin());
            lpEditText.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            lpEditText.addRule(RelativeLayout.END_OF, spinnerID);
            lpEditText.addRule(RelativeLayout.RIGHT_OF, spinnerID);
            lpEditText.addRule(RelativeLayout.ALIGN_BOTTOM, spinnerID);
            lpEditText.addRule(RelativeLayout.ALIGN_TOP, spinnerID);
            editText.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);

            editText.setText(textlist[i]);

            relativeLayout.addView(searchableSpinner,lpSpinner);
            relativeLayout.addView(editText, lpEditText);
            cells.add(new Cell(editText, searchableSpinner, i));
        }
    }

    private int getMargin(){
        int dpValue = 8;
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
        String currencyKey = focusedCell.getSpinnerKey();
        String text = focusedCell.getEditText().getText().toString();
        double inputValue = text.isEmpty() ? 0 : Double.parseDouble(text);
        if (currencyValues.contains(currencyKey)){
            double focusedValue = currencyValues.getValueFor(currencyKey);
            double usd = inputValue / focusedValue;

            for (Cell cell : cells) {
                if (cell != focusedCell){
                    String key = cell.getSpinnerKey();
                    if (currencyValues.contains(key)) {
                        double value = currencyValues.getValueFor(key);
                        cell.setEditText(""+new DecimalFormat("#.##").format(value * usd));
                    } else {
                        toastNoCurrencyValue(key);
                    }
                }
            }
        } else {
            toastNoCurrencyValue(currencyKey);
        }
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
    }
}

//Todo: save choosen currencys.
//Todo: räkna ut värdet direkt när appen startar och när ny currency väljs.
