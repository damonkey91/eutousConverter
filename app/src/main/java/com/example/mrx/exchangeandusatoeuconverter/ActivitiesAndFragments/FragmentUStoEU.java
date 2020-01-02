package com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrx.exchangeandusatoeuconverter.Adapters.AdapterRecyclerViewConverter;
import com.example.mrx.exchangeandusatoeuconverter.Adapters.AdapterRecyclerViewUnit;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.GetDrawable;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.HideSoftKeyboard;
import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ICallbackEditTextTextChanged;
import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ICallbackRecyclerAdapter;
import com.example.mrx.exchangeandusatoeuconverter.R;
import com.example.mrx.exchangeandusatoeuconverter.ViewModels.ViewModelConverter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by mrx on 2018-07-15.
 */

public class FragmentUStoEU extends Fragment implements ICallbackRecyclerAdapter, ICallbackEditTextTextChanged {

    private static final String UNIT_ADAPTER = "unitAdapter";
    private static final String MEASURMENT_ADAPTER = "measurmentAdapter";

    private ViewModelConverter viewModel;
    private RecyclerView listView;
    private View view;
    private ActionBar actionBar;
    public boolean closeApp = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        viewModel = ViewModelProviders.of(getActivity()).get(ViewModelConverter.class);
        view = inflater.inflate(R.layout.fragment_usa_eu_tab, container, false);
        setupListView();
        return view;
    }

    private void setupListView() {
        listView = view.findViewById(R.id.recycler_view_converter);
        listView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);
        listView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        AdapterRecyclerViewConverter adapter = new AdapterRecyclerViewConverter(viewModel.getMeasurments(), this, viewModel.getColorList(), viewModel.getIconList());
        listView.setAdapter(adapter);

    }

    @Override
    public void callback(int position) {
        viewModel.setChoosenMeasurment(position);
        changeAdapter(UNIT_ADAPTER);
        ((Exchange) getActivity()).setToolbarColor(viewModel.getColorFromList(position));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                HideSoftKeyboard.hide(view);
                changeAdapter(MEASURMENT_ADAPTER);
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeAdapter(String adapterKey){
        switch (adapterKey){
            case MEASURMENT_ADAPTER:
                AdapterRecyclerViewConverter adapter = new AdapterRecyclerViewConverter(viewModel.getMeasurments(), this, viewModel.getColorList(), viewModel.getIconList());
                listView.setAdapter(adapter);
                closeApp = true;
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setTitle(GetDrawable.getStringResource(R.string.app_name));
                ((Exchange) getActivity()).setToolbarColor(GetDrawable.getColorResource(R.color.colorToolbar));
                break;
            case UNIT_ADAPTER:
                AdapterRecyclerViewUnit adapterUnit = new AdapterRecyclerViewUnit(viewModel.getUnitsForChoosenMeasurment(), this);
                listView.setAdapter(adapterUnit);
                closeApp = false;
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(viewModel.getChoosenMeasurment().getName());
                callbackTextChanged(0, 1);
                break;
        }
    }

    public void changeAdapterOrigin(){
        changeAdapter(MEASURMENT_ADAPTER);
    }

    @Override
    public void callbackTextChanged(int position, double input) {
        ((AdapterRecyclerViewUnit)listView.getAdapter()).updateAllEditTexts(viewModel.updateEditTexts(position, input), position);
    }
}

//Todo: designa units tabell celler, text tjocklek och indikation på vilken cell som är i fokus
//Todo: för många decimaler på convertern

