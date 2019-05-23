package com.example.drcreeper.refereeapp;

import com.google.gson.annotations.SerializedName;

public class Answer {
    @SerializedName("result")
    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
