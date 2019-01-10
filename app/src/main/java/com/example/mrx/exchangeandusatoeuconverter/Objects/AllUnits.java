package com.example.mrx.exchangeandusatoeuconverter.Objects;

import android.content.Context;

import com.example.mrx.exchangeandusatoeuconverter.Helpers.ConstantArrays;

import java.util.ArrayList;
import java.util.HashMap;

public class AllUnits {
    private HashMap<String, ArrayList<Unit>> allUnits;
    private Context context;

    public AllUnits(Context context){
        this.context = context;
        ConstantArrays constantArrays = new ConstantArrays(context);
        allUnits = constantArrays.getUnitHashMap();
    }

    public ArrayList<Unit> getUnitsFrom(String choosenMeasurment) {
        return allUnits.get(choosenMeasurment);
    }

}
