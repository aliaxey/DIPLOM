package com.example.drcreeper.refereeapp.screens.results;

import android.graphics.Color;

import androidx.databinding.BaseObservable;

public class TableMark extends BaseObservable {
    private String mark;
    private int color;

    public TableMark(String mark){
        this.mark = mark;
        color = Color.rgb(50,255,55);
    }
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
        notifyChange();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        notifyChange();
    }
}
