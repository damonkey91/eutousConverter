package com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrx.exchangeandusatoeuconverter.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by mrx on 2018-07-15.
 */

public class FragmentUStoEU extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_usa_eu_tab, container, false);
        return view;
    }
}
