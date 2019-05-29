
package com.example.drcreeper.refereeapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {

    @SerializedName("team")
    @Expose
    private int team;
    @SerializedName("info")
    @Expose
    private List<Info> info = null;
    @SerializedName("marks")
    @Expose
    private List<Mark> marks = null;

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

}
