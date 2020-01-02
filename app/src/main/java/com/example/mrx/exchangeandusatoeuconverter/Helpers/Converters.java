package com.example.mrx.exchangeandusatoeuconverter.Helpers;

import com.example.mrx.exchangeandusatoeuconverter.Objects.Unit;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Converters {

    public static ArrayList<String> convert(String measurement, ArrayList<Unit> units, int position, double value){
        ArrayList<String> arrayList = new ArrayList<>();
        switch (measurement){
            case Constants.LENGTH_KEY:
            case Constants.WEIGHT_KEY:
            case Constants.SPEED_KEY:
            case Constants.AREA_KEY:
                arrayList = simpleValueConverter(units, position, value);
                break;
            case Constants.TEMPERATURE_KEY:
                arrayList = temperatureConverter(units, position, value);
                break;
        }
        return arrayList;
    }

    private static ArrayList<String> simpleValueConverter(ArrayList<Unit> units, int position, double value){
        ArrayList<String> array = new ArrayList<>();
        double measuringValue = value/units.get(position).getUnitValue();
        for (Unit unit : units) {
            double tempValue = (unit.getUnitValue() * measuringValue);
            String calcValue = MyDecimalFormat.formatDecimal(tempValue);
            array.add(calcValue);
        }
        return array;
    }

    private static ArrayList<String> temperatureConverter(ArrayList<Unit> units, int position, double value){
        double kelvin = 0;
        Unit unit = units.get(position);
        switch (position){
            case 0:
                kelvin = 273.15 + value;
                break;
            case 1:
                kelvin = (value + 459.67)* (5.0/9.0);
                break;
            case 2:
                kelvin = value;
                break;
        }
        kelvin = kelvin < 0 ? 0 : kelvin;
        ArrayList<String> array = new ArrayList<>();
        array.add(""+ MyDecimalFormat.formatDecimalSimple(kelvin - 273.15));
        array.add(""+ MyDecimalFormat.formatDecimalSimple(((kelvin * (9.0/5.0)) - 459.67)));
        array.add(""+ MyDecimalFormat.formatDecimalSimple(kelvin));
        return array;
    }
}
