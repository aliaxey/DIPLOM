package com.example.drcreeper.refereeapp.screens.fields;

import androidx.databinding.BaseObservable;

public class ItemInfo extends BaseObservable {
    private String name;
    public ItemInfo(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }
}
