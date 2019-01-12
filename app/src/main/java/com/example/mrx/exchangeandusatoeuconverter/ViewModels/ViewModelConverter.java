package com.example.mrx.exchangeandusatoeuconverter.ViewModels;

import android.app.Application;
import android.graphics.drawable.Drawable;

import com.example.mrx.exchangeandusatoeuconverter.Helpers.ConstantArrays;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.Converters;
import com.example.mrx.exchangeandusatoeuconverter.Objects.AllUnits;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Measurment;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Unit;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ViewModelConverter extends AndroidViewModel {

    private AllUnits allUnits;
    private ArrayList<Measurment> measurments;
    private Measurment choosenMeasurment;
    private ArrayList<Integer> colorList;
    private HashMap<String, Drawable> iconList;

    public ViewModelConverter(@NonNull Application application) {
        super(application);
        ConstantArrays constantArrays = new ConstantArrays(application);
        measurments = constantArrays.getMeasurmentArray();
        allUnits = new AllUnits(application);
        colorList = constantArrays.getColorList();
        iconList = constantArrays.getIconArray();
    }

    public ArrayList<Measurment> getMeasurments() {
        return measurments;
    }

    public ArrayList<Unit> getUnitsForChoosenMeasurment(){
        return allUnits.getUnitsFrom(choosenMeasurment.getConstant());
    }

    public Measurment getChoosenMeasurment() {
        return choosenMeasurment;
    }

    public void setChoosenMeasurment(int position) {
        this.choosenMeasurment = measurments.get(position);
    }

    public ArrayList<String> updateEditTexts(int position, double input) {
        ArrayList<Unit> units = getUnitsForChoosenMeasurment();
        return Converters.convert(choosenMeasurment.getConstant(), units, position, input);
    }

    public ArrayList<Integer> getColorList() {
        return colorList;
    }

    public int getColorFromList(int position){
        if (colorList.size() > position){
            return colorList.get(position);
        }
        return colorList.get(0);
    }

    public HashMap<String, Drawable> getIconList() {
        return iconList;
    }
}
