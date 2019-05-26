package com.example.drcreeper.refereeapp.screens.addteam;

import com.example.drcreeper.refereeapp.models.Header;

import androidx.databinding.BaseObservable;

public class InfoViewModel extends BaseObservable {
    private int id;
    private String name;
    private String value;
    public InfoViewModel(Header header){
        setId(header.getId());
        setName(header.getName());
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyChange();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        notifyChange();
    }
}
