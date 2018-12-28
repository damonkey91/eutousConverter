package com.example.mrx.exchangeandusatoeuconverter.Helpers;

import com.example.mrx.exchangeandusatoeuconverter.Objects.Unit;

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
            String calcValue = ""+(unit.getUnitValue() * measuringValue);
            array.add(calcValue);
        }
        return array;
    }

    private static ArrayList<String> temperatureConverter(ArrayList<Unit> units, int position, double value){
        return new ArrayList<>();
    }
}
