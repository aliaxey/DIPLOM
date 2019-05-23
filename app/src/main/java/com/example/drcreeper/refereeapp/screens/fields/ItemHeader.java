package com.example.drcreeper.refereeapp.screens.fields;

import androidx.databinding.BaseObservable;

public class ItemHeader extends BaseObservable {
    private String name;
    public ItemHeader(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }
}
