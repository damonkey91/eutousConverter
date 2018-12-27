package com.example.mrx.exchangeandusatoeuconverter.Objects;

public class Unit {
    private String unitName;
    private String unitFullName;
    private double unitValue;

    public Unit(String unitName, String unitFullName, double unitValue){
        this.unitName = unitName;
        this.unitFullName = unitFullName;
        this.unitValue = unitValue;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getUnitFullName() {
        return unitFullName;
    }

    public double getUnitValue() {
        return unitValue;
    }
}
