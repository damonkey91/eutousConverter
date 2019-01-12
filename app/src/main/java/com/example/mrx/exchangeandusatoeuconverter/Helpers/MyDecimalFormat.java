package com.example.mrx.exchangeandusatoeuconverter.Helpers;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MyDecimalFormat {
    public static String formatDecimal(double value){
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = null;
        if (value < 0.0001 && value != 0)
            decimalFormat = new DecimalFormat("#.##E0", decimalFormatSymbols);
        else if (value < 1)
            decimalFormat = new DecimalFormat("#.####", decimalFormatSymbols);
        else
            decimalFormat = new DecimalFormat("#.##", decimalFormatSymbols);
        String ss = decimalFormat.format(value);
        return decimalFormat.format(value);
    }

    public static String formatDecimalSimple(double value){
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#.##", decimalFormatSymbols);
        return decimalFormat.format(value);
    }
}
