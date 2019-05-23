
package com.example.drcreeper.refereeapp.models;

import com.example.drcreeper.refereeapp.Answer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContestInfo extends Answer {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("group")
    @Expose
    private int group;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("access")
    @Expose
    private int access;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }
}
