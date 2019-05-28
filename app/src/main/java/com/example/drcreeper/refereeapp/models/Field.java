
package com.example.drcreeper.refereeapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Field {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("group")
    @Expose
    private int group;
    @SerializedName("competition_id")
    @Expose
    private int competitionId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("max_value")
    @Expose
    private int maxValue;
    @SerializedName("factor")
    @Expose
    private float factor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }
}
