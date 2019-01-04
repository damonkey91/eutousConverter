package com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;

import com.example.mrx.exchangeandusatoeuconverter.R;
import com.example.mrx.exchangeandusatoeuconverter.SearchableSpinner.Adapters.RecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchableListDialog extends DialogFragment implements SearchView.OnQueryTextListener {

    private RecyclerViewAdapter listAdapter;
    private View view;
    private SearchView searchView;

    public static SearchableListDialog newInstance(RecyclerViewAdapter listAdapter){
        SearchableListDialog searchableListDialog = new SearchableListDialog();
        searchableListDialog.listAdapter = listAdapter;
        return searchableListDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.searchable_list_dialog, null);
        setupView();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("Välj valuta!")
                .setView(view)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listAdapter.resetFilteredList();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    private void setupView() {
        setupSearchField();
        setupRecyclerView();
    }

    private void setupSearchField() {
        searchView = view.findViewById(R.id.searchViewDialog);
        searchView.setOnQueryTextListener(this);
    }

    private void setupRecyclerView() {
        RecyclerView listView = view.findViewById(R.id.recyclerViewDialog);
        listView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);
        listView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(listAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.getFilter().filter(newText);
        return true;
    }
}
