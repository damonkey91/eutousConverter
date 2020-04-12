package com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.Adapters.RecyclerViewAdapter;
import com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.Interface.IItemClickCallback;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("AppCompatCustomView")
public class SearchableSpinner extends Spinner implements View.OnTouchListener, IItemClickCallback {


    public static final int NO_ITEM_SELECTED = -1;
    private Context context;
    private SearchableListDialog searchableListDialog;
    private long lastTouch = 0;

    public SearchableSpinner(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public SearchableSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        searchableListDialog = new SearchableListDialog();
        init();
    }

    private void init() {
        setOnTouchListener(this);
    }

    public void setAdapters(SpinnerAdapter spinnerAdapter, RecyclerViewAdapter listAdapter){
        setAdapter(spinnerAdapter);
        listAdapter.setCallback(this);
        searchableListDialog = SearchableListDialog.newInstance(listAdapter);
    }

    @Override
    public int getSelectedItemPosition() {
        return super.getSelectedItemPosition();
    }

    @Override
    public Object getSelectedItem() {
        return super.getSelectedItem();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean clickable = SystemClock.elapsedRealtime() - lastTouch > 1000;
        if (event.getAction() == MotionEvent.ACTION_UP && clickable){
            lastTouch = SystemClock.elapsedRealtime();
            //open searchable dialog on click
            searchableListDialog.show(scanForActivity(context).getSupportFragmentManager(), "TAG");
            return true;
        }
        return false;
    }

    private AppCompatActivity scanForActivity(Context cont) {

        if (cont instanceof AppCompatActivity)
            return (AppCompatActivity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());

        return null;
    }

    @Override
    public void itemClicked(int position) {
        setSelection(position);
        searchableListDialog.dismiss();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
