package com.example.drcreeper.refereeapp.screens.fields;


public class ItemField extends ItemInfo {
    private int maxValue;
    private float factor;

    public ItemField(){
        super();
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        notifyChange();
    }
    public String getMaxValueString(){
        return String.valueOf(maxValue);
    }

    public float getFactor() {
        return factor;
    }
    public String  getFactorValue() {
        return String.valueOf(factor);
    }

    public void setFactor(float factor) {
        this.factor = factor;
        notifyChange();
    }
}
