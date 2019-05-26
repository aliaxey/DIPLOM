package com.example.drcreeper.refereeapp.screens.contest;

import com.example.drcreeper.refereeapp.models.Field;

import androidx.databinding.BaseObservable;

public class TableField extends BaseObservable {
    private int team;
    private int field;
    private int maxValue;
    private String value;

    public TableField(int team, int field, int maxValue){
        this.team = team;
        this.field = field;
        this.maxValue = maxValue;
    }
    public int getTeam() {
        return team;
    }

    public int getField() {
        return field;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        notifyChange();
    }
}
