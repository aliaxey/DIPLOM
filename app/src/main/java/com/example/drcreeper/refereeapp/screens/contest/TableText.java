package com.example.drcreeper.refereeapp.screens.contest;

import androidx.databinding.BaseObservable;

public class TableText extends BaseObservable {
    private String name;

    public TableText(String n){
        super();
        name = n;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }
}
