package com.example.mrx.exchangeandusatoeuconverter.Objects;

public class Measurment {

    private String name;
    private String constant;

    public Measurment(String name, String constant){
        this.name = name;
        this.constant = constant;
    }

    public String getName() {
        return name;
    }

    public String getConstant() {
        return constant;
    }
}
