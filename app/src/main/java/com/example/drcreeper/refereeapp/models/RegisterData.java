package com.example.drcreeper.refereeapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterData {
    @SerializedName("lastname")
    public String lastname;
    @SerializedName("na")
    public String name;
    @SerializedName("s")
    public String status;
    @SerializedName("k")
    public String key;

    public  RegisterData(){
        super();
    }
    public RegisterData(String lastname,String name,String status,String key){
        this.lastname = lastname;
        this.name = name;
        this.status = status;
        this.key = key;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
