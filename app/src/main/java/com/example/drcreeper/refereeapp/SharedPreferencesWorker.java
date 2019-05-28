package com.example.drcreeper.refereeapp;

import android.content.Context;
import android.content.SharedPreferences;

import okhttp3.FormBody;

public class SharedPreferencesWorker {
    private static final String FILENAME = "userdata";
    private static final String ID = "id";
    private static final String PASSWORD = "password";
    private static final String CONTEST = "contest";
    private static final String CONTEST_CODE = "contest_code";
    private static final String CONTEST_NAME = "name";
    private static final String STATE = "state";

    public static final int INITIAL = 0;
    public static final int SET_USER = 1;
    public static final int SET_CONTEST = 2;
    Context context;
    public SharedPreferencesWorker(Context ctx){
        context = ctx;
    }
    public void setUser(int id, String pass){
        SharedPreferences preferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ID,id);
        editor.putString(PASSWORD,pass);
        editor.putInt(STATE,SET_USER);
        editor.apply();
    }
    public void setContest(int id){
        SharedPreferences preferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CONTEST,id);
        editor.putInt(STATE,SET_CONTEST);
        editor.apply();
    }
    public void setContestData(String name,String key){
        SharedPreferences preferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CONTEST_NAME,name);
        editor.putString(CONTEST_CODE,key);
        editor.apply();
    }
    public int getState(){
        return getPreferences().getInt(STATE,INITIAL);
    }
    public  int getUserId(){
        return getPreferences().getInt(ID,-1);
    }
    public String getUserString(){
        return String.valueOf(getUserId());
    }
    public  String getPassword(){
        return getPreferences().getString(PASSWORD,"");
    }
    public int getContest(){
        return getPreferences().getInt(CONTEST,-1);
    }
    public String getContestString(){
        return String.valueOf(getContest());
    }
    public String getContestName(){
        return getPreferences().getString(CONTEST_NAME,"");
    }
    public String getContestCode(){
        return getPreferences().getString(CONTEST_CODE,"");
    }

    public FormBody.Builder getContestRequest(){
        return new FormBody.Builder()
                .add("id",getUserString())
                .add("pass",getPassword())
                .add("contest",getContestString());
    }
    private SharedPreferences getPreferences(){
        return context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
    }
}
