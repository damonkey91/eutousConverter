package com.example.mrx.exchangeandusatoeuconverter.Helpers;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.mrx.exchangeandusatoeuconverter.Objects.Measurment;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Unit;
import com.example.mrx.exchangeandusatoeuconverter.R;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.core.content.ContextCompat;

public class ConstantArrays {

    private Context context;

    public ConstantArrays(Context context){
        this.context = context;
    }

    public HashMap<String ,Drawable> getIconArray(){
        HashMap<String ,Drawable> list = new HashMap<>();
        list.put(Constants.AREA_KEY, ContextCompat.getDrawable(context, R.drawable.ic_area));
        list.put(Constants.LENGTH_KEY, ContextCompat.getDrawable(context, R.drawable.ic_length));
        list.put(Constants.TEMPERATURE_KEY, ContextCompat.getDrawable(context, R.drawable.ic_temperature));
        list.put(Constants.SPEED_KEY, ContextCompat.getDrawable(context, R.drawable.ic_speed));
        list.put(Constants.WEIGHT_KEY, ContextCompat.getDrawable(context, R.drawable.ic_weight));
        list.put(Constants.NO_DRAWABLE, ContextCompat.getDrawable(context, R.drawable.ic_android_black_24dp));

        //;
        //ContextCompat.getDrawable(context, R.drawable.ic_length);
        //ContextCompat.getDrawable(context, R.drawable.ic_length);
        return list;
    }

    public ArrayList<Integer> getColorList(){
        int[] ints = context.getResources().getIntArray(R.array.colorArray);
        ArrayList<Integer> colorList = new ArrayList<>();
        for (int colorValue : ints) {
            colorList.add(colorValue);
        }
        return colorList;
    }

    public ArrayList<Measurment> getMeasurmentArray(){
        ArrayList<Measurment> array = new ArrayList<>();
        array.add(new Measurment(context.getString(R.string.temperatur), Constants.TEMPERATURE_KEY));
        array.add(new Measurment(context.getString(R.string.area), Constants.AREA_KEY));
       // array.add(new Measurment(context.getString(R.string.baking), Constants.BACKING_KEY));
        array.add(new Measurment(context.getString(R.string.length), Constants.LENGTH_KEY));
        array.add(new Measurment(context.getString(R.string.weight), Constants.WEIGHT_KEY));
        array.add(new Measurment(context.getString(R.string.speed),Constants.SPEED_KEY));

        return array;
    }

    public HashMap<String, ArrayList<Unit>> getUnitHashMap(){
        HashMap<String, ArrayList<Unit>> hashMap = new HashMap<>();
        hashMap.put(Constants.AREA_KEY, createAreaArray());
        //hashMap.put(Constants.BACKING_KEY, );
        hashMap.put(Constants.LENGTH_KEY, createLengthArray());
        hashMap.put(Constants.SPEED_KEY,createSpeedArray() );
        hashMap.put(Constants.TEMPERATURE_KEY, createTemperatureArray());
        hashMap.put(Constants.WEIGHT_KEY, createWeightArray());
        return hashMap;
    }

    private ArrayList<Unit> createAreaArray(){
        ArrayList<Unit> array = new ArrayList<>();
        array.add(new Unit(Constants.SQUARECENTIMETER_UNIT_KEY, context.getString(R.string.squarecentimeter), Constants.SQUARECENTIMETER_UNIT_VALUE));
        array.add(new Unit(Constants.SQUAREMETER_UNIT_KEY, context.getString(R.string.squaremeter), Constants.SQUAREMETER_UNIT_VALUE));
        array.add(new Unit(Constants.SQUAREKILOMETER_UNIT_KEY, context.getString(R.string.squarekilometer), Constants.SQUAREKILOMETER_UNIT_VALUE));
        array.add(new Unit(Constants.SQUAREFOOT_UNIT_KEY, context.getString(R.string.squarefoot), Constants.SQUAREFOOT_UNIT_VALUE));
        array.add(new Unit(Constants.SQUAREINCH_UNIT_KEY, context.getString(R.string.squareinch), Constants.SQUAREINCH_UNIT_VALUE));
        array.add(new Unit(Constants.SQUAREMILE_UNIT_KEY, context.getString(R.string.squaremile), Constants.SQUAREMILE_UNIT_VALUE));
        array.add(new Unit(Constants.SQUAREYARD_UNIT_KEY, context.getString(R.string.squareyard), Constants.SQUAREYARD_UNIT_VALUE));
        array.add(new Unit(Constants.ACRE_UNIT_KEY, context.getString(R.string.acre), Constants.ACRE_UNIT_VALUE));
        array.add(new Unit(Constants.HECTARE_UNIT_KEY, context.getString(R.string.hectare), Constants.HECTARE_UNIT_VALUE));
        return array;
    }

    private ArrayList<Unit> createTemperatureArray() {
        ArrayList<Unit> array = new ArrayList<>();
        array.add(new Unit(Constants.CELCIUS_UNIT, context.getString(R.string.celcius), -Constants.CELCIUS_UNIT_VALUE));
        array.add(new Unit(Constants.FAHRENHEIT_UNIT, context.getString(R.string.fahrenheit), -Constants.FAHRENHEIT_UNIT_VALUE));
        array.add(new Unit(Constants.KELVIN_UNIT, context.getString(R.string.kelvin), Constants.KELVIN_UNIT_VALUE));
        return array;
    }

    private ArrayList<Unit> createWeightArray() {
        ArrayList<Unit> array = new ArrayList<>();
        array.add(new Unit(Constants.GRAM_UNIT, context.getString(R.string.gram), Constants.GRAM_UNIT_VALUE));
        array.add(new Unit(Constants.KILOGRAM_UNIT, context.getString(R.string.kilogram), Constants.KILOGRAM_UNIT_VALUE));
        array.add(new Unit(Constants.POUND_UNIT, context.getString(R.string.pound), Constants.POUND_UNIT_VALUE));
        array.add(new Unit(Constants.OUNCE_UNIT, context.getString(R.string.ounce), Constants.OUNCE_UNIT_VALUE));
        array.add(new Unit(Constants.TON_UNIT, context.getString(R.string.ton), Constants.TON_UNIT_VALUE));
        return array;
    }

    private ArrayList<Unit> createLengthArray() {
        ArrayList<Unit> array = new ArrayList<>();
        array.add(new Unit(Constants.METER_UNIT, context.getString(R.string.meter), Constants.METER_UNIT_VALUE));
        array.add(new Unit(Constants.CENTIMETER_UNIT, context.getString(R.string.centimeter), Constants.CENTIMETER_UNIT_VALUE));
        array.add(new Unit(Constants.DECIMETER_UNIT, context.getString(R.string.decimeter), Constants.DECIMETER_UNIT_VALUE));
        array.add(new Unit(Constants.FOOT_UNIT, context.getString(R.string.foot), Constants.FOOT_UNIT_VALUE));
        array.add(new Unit(Constants.INCH_UNIT, context.getString(R.string.inch), Constants.INCH_UNIT_VALUE));
        array.add(new Unit(Constants.KILOMETER_UNIT, context.getString(R.string.kilometer), Constants.KILOMETER_UNIT_VALUE));
        array.add(new Unit(Constants.MILE_UNIT, context.getString(R.string.mile), Constants.MILE_UNIT_VALUE));
        array.add(new Unit(Constants.YARD_UNIT, context.getString(R.string.yard), Constants.YARD_UNIT_VALUE));
        return array;
    }

    private ArrayList<Unit> createSpeedArray() {
        ArrayList<Unit> array = new ArrayList<>();
        array.add(new Unit(Constants.METERPERSECOND_UNIT, context.getString(R.string.meterpersecond), Constants.METERPERSECOND_UNIT_VALUE));
        array.add(new Unit(Constants.FOOTPERSECOND_UNIT, context.getString(R.string.footpersecond), Constants.FOOTPERSECOND_UNIT_VALUE));
        array.add(new Unit(Constants.MILESPERHOUR_UNIT, context.getString(R.string.milesperhour), Constants.MILESPERHOUR_UNIT_VALUE));
        array.add(new Unit(Constants.KILOMETERPERHOUR_UNIT, context.getString(R.string.kilometerperhour), Constants.KILOMETERPERHOUR_UNIT_VALUE));
        array.add(new Unit(Constants.KNOT_UNIT, context.getString(R.string.knot), Constants.KNOT_UNIT_VALUE));
        return array;
    }

    private ArrayList<Unit> createBakingArray() {
        ArrayList<Unit> array = new ArrayList<>();

        return array;
    }
}
