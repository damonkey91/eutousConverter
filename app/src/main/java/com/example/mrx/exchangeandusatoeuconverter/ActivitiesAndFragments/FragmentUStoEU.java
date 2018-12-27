package com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrx.exchangeandusatoeuconverter.Adapters.AdapterRecyclerViewConverter;
import com.example.mrx.exchangeandusatoeuconverter.Adapters.AdapterRecyclerViewUnit;
import com.example.mrx.exchangeandusatoeuconverter.Interfaces.ICallbackRecyclerAdapter;
import com.example.mrx.exchangeandusatoeuconverter.R;
import com.example.mrx.exchangeandusatoeuconverter.ViewModels.ViewModelConverter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by mrx on 2018-07-15.
 */

public class FragmentUStoEU extends Fragment implements ICallbackRecyclerAdapter {

    private ViewModelConverter viewModel;
    private RecyclerView listView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
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
        AdapterRecyclerViewConverter adapter = new AdapterRecyclerViewConverter(viewModel.getMeasurments(), this);
        listView.setAdapter(adapter);

    }

    @Override
    public void callback(int position) {
        Log.d("Hello", ""+position);
        viewModel.setChoosenMeasurment(position);
        AdapterRecyclerViewUnit adapterUnit = new AdapterRecyclerViewUnit(viewModel.getUnitsForChoosenMeasurment());
        listView.setAdapter(adapterUnit);
    }
}
