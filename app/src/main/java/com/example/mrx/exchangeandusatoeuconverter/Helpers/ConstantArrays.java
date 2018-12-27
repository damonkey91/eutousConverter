package com.example.mrx.exchangeandusatoeuconverter.Helpers;

import android.content.Context;

import com.example.mrx.exchangeandusatoeuconverter.Objects.Constants;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Measurment;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Unit;
import com.example.mrx.exchangeandusatoeuconverter.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ConstantArrays {

    private Context context;

    public ConstantArrays(Context context){
        this.context = context;
    }

    public ArrayList<Measurment> getMeasurmentArray(){
        ArrayList<Measurment> array = new ArrayList<>();
        array.add(new Measurment(context.getString(R.string.temperatur), Constants.TEMPERATURE_KEY));
        array.add(new Measurment(context.getString(R.string.area), Constants.AREA_KEY));
        array.add(new Measurment(context.getString(R.string.baking), Constants.BACKING_KEY));
        array.add(new Measurment(context.getString(R.string.length), Constants.LENGTH_KEY));
        array.add(new Measurment(context.getString(R.string.weight), Constants.WEIGHT_KEY));
        array.add(new Measurment(context.getString(R.string.speed),Constants.SPEED_KEY));

        return array;
    }

    public HashMap<String, ArrayList<Unit>> getUnitHashMap(){
        HashMap<String, ArrayList<Unit>> hashMap = new HashMap<>();
        hashMap.put(Constants.AREA_KEY, createAreaArray());
        /*
        tempMap.put(Constants.BACKING_KEY, );
        tempMap.put(Constants.LENGTH_KEY, );
        tempMap.put(Constants.SPEED_KEY, );
        tempMap.put(Constants.TEMPERATURE_KEY, );
        tempMap.put(Constants.WEIGHT_KEY, );
        */
        return hashMap;
    }

    private ArrayList<Unit> createAreaArray(){
        ArrayList<Unit> array = new ArrayList<>();
        array.add(new Unit(Constants.SQUARECENTIMETER_UNIT_KEY, context.getString(R.string.squarecentimeter), Constants.SQUARECENTIMETER_UNIT_VALUE));
        array.add(new Unit(Constants.SQUAREMETER_UNIT_KEY, context.getString(R.string.squaremeter), Constants.SQUARECENTIMETER_UNIT_VALUE));
        array.add(new Unit(Constants.SQUAREKILOMETER_UNIT_KEY, context.getString(R.string.squarekilometer), Constants.SQUAREKILOMETER_UNIT_VALUE));
        array.add(new Unit(Constants.SQUAREFOOT_UNIT_KEY, context.getString(R.string.squarefoot), Constants.SQUAREFOOT_UNIT_VALUE));
        array.add(new Unit(Constants.SQUAREINCH_UNIT_KEY, context.getString(R.string.squareinch), Constants.SQUAREINCH_UNIT_VALUE));
        array.add(new Unit(Constants.SQUAREMILE_UNIT_KEY, context.getString(R.string.squaremile), Constants.SQUAREMILE_UNIT_VALUE));
        array.add(new Unit(Constants.SQUAREYARD_UNIT_KEY, context.getString(R.string.squareyard), Constants.SQUAREYARD_UNIT_VALUE));
        array.add(new Unit(Constants.ACRE_UNIT_KEY, context.getString(R.string.acre), Constants.ACRE_UNIT_VALUE));
        array.add(new Unit(Constants.HECTARE_UNIT_KEY, context.getString(R.string.hectare), Constants.HECTARE_UNIT_VALUE));

        return array;
    }
}
