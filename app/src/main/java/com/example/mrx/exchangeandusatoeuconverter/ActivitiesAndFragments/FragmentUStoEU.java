package com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrx.exchangeandusatoeuconverter.Adapters.AdapterRecyclerViewConverter;
import com.example.mrx.exchangeandusatoeuconverter.Adapters.AdapterRecyclerViewUnit;
import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ICallbackEditTextTextChanged;
import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ICallbackRecyclerAdapter;
import com.example.mrx.exchangeandusatoeuconverter.R;
import com.example.mrx.exchangeandusatoeuconverter.ViewModels.ViewModelConverter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

        Drawable mDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_length);
        mDrawable.setColorFilter(new PorterDuffColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN));

        listView = view.findViewById(R.id.recycler_view_converter);
        listView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);
        listView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        AdapterRecyclerViewConverter adapter = new AdapterRecyclerViewConverter(viewModel.getMeasurments(), this, viewModel.getColorList(), mDrawable);
        listView.setAdapter(adapter);

    }

    @Override
    public void callback(int position) {
        Log.d("Hello", ""+position);
        viewModel.setChoosenMeasurment(position);
        changeAdapter(UNIT_ADAPTER);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                changeAdapter(MEASURMENT_ADAPTER);
                actionBar.setDisplayHomeAsUpEnabled(false);
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeAdapter(String adapterKey){
        switch (adapterKey){
            case MEASURMENT_ADAPTER:
                ContextCompat.getDrawable(getContext(), R.drawable.ic_b1ncacik6a);
                Drawable mDrawable = getResources().getDrawable(R.drawable.ic_android_black_24dp);
                mDrawable.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY));

                AdapterRecyclerViewConverter adapter = new AdapterRecyclerViewConverter(viewModel.getMeasurments(), this, viewModel.getColorList(), mDrawable);
                listView.setAdapter(adapter);
                break;
            case UNIT_ADAPTER:
                AdapterRecyclerViewUnit adapterUnit = new AdapterRecyclerViewUnit(viewModel.getUnitsForChoosenMeasurment(), this);
                listView.setAdapter(adapterUnit);
                break;
        }
    }

    public void changeAdapterOrigin(){
        changeAdapter(MEASURMENT_ADAPTER);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void callbackTextChanged(int position, double input) {
        ((AdapterRecyclerViewUnit)listView.getAdapter()).updateAllEditTexts(viewModel.updateEditTexts(position, input), position);
    }
}
