
package com.example.drcreeper.refereeapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mark {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("competition_id")
    @Expose
    private int competitionId;
    @SerializedName("field_id")
    @Expose
    private int fieldId;
    @SerializedName("team_id")
    @Expose
    private int teamId;
    @SerializedName("mark")
    @Expose
    private int mark;
    @SerializedName("referee_id")
    @Expose
    private int refereeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(int refereeId) {
        this.refereeId = refereeId;
    }

}
