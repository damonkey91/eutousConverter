package com.example.mrx.exchangeandusatoeuconverter.ViewModels;

import android.app.Application;

import com.example.mrx.exchangeandusatoeuconverter.Helpers.ConstantArrays;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.Converters;
import com.example.mrx.exchangeandusatoeuconverter.Objects.AllUnits;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Measurment;
import com.example.mrx.exchangeandusatoeuconverter.Objects.Unit;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ViewModelConverter extends AndroidViewModel {

    private AllUnits allUnits;
    private ArrayList<Measurment> measurments;
    private Measurment choosenMeasurment;

    public ViewModelConverter(@NonNull Application application) {
        super(application);
        ConstantArrays constantArrays = new ConstantArrays(application);
        measurments = constantArrays.getMeasurmentArray();
        allUnits = new AllUnits(application);
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
}
