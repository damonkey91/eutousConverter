package com.example.mrx.exchangeandusatoeuconverter.Helpers;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MyDecimalFormat {

    public static double ParseStringToDouble(String stringDouble){
        double returnValue;

        if (stringDouble.isEmpty() || stringDouble.equals(".") || stringDouble.equals("-") || stringDouble.equals("-.") ||
                stringDouble.endsWith("E") || stringDouble.endsWith("-") ||
                stringDouble.startsWith("E") || stringDouble.startsWith("-E") || stringDouble.startsWith(".E"))
            returnValue = 0;
        else
            returnValue = Double.parseDouble(stringDouble);
        return returnValue;
    }

    public static String formatDecimal(double value){
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = null;
        if (value < 0.0001 && value != 0)
            decimalFormat = new DecimalFormat("#.##E0", decimalFormatSymbols);
        else if (value < 1)
            decimalFormat = new DecimalFormat("#.####", decimalFormatSymbols);
        else if (value > 1000000000) {
            decimalFormat = new DecimalFormat("#.##E0", decimalFormatSymbols);
            decimalFormat.setMaximumFractionDigits(2);
        }
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
