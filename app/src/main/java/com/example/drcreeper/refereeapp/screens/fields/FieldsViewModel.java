package com.example.drcreeper.refereeapp.screens.fields;

import android.content.Context;

import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;

import androidx.databinding.BaseObservable;

public class FieldsViewModel extends BaseObservable {
    private FragmentWorker fragmentWorker;
    private Context context;
    private FieldsRecyclerViewAdapter adapter;


    public FieldsViewModel(Context ctx, FragmentWorker worker) {
        super();
        fragmentWorker = worker;
        context = ctx;
        adapter = new FieldsRecyclerViewAdapter();
    }

    public FieldsRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(FieldsRecyclerViewAdapter adapter) {
        this.adapter = adapter;
        notifyChange();
    }
}