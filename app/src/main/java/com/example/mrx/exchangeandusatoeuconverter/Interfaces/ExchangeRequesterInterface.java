package com.example.mrx.exchangeandusatoeuconverter.Interfaces;

import java.util.ArrayList;

/**
 * Created by mrx on 2018-07-31.
 */

public interface ExchangeRequesterInterface {
    void gotRequestedList(ArrayList<ArrayList<String>> list, String requestType);
}
