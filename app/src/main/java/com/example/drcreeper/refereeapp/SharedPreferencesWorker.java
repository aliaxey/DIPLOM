package com.example.drcreeper.refereeapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesWorker {
    private static final String FILENAME = "userdata";
    private static final String ID = "id";
    private static final String PASSWORD = "password";
    private static final String CONTEST = "contest";
    //private static final String CODE = "code";
    Context context;
    public SharedPreferencesWorker(Context ctx){
        context = ctx;
    }
    public void setUser(int id, String pass){
        SharedPreferences preferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ID,id);
        editor.putString(PASSWORD,pass);
        editor.apply();
    }
    public void setContest(int id){
        SharedPreferences preferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CONTEST,id);
        //editor.putString(CODE,code);
        editor.apply();
    }
    public  int getUserId(){
        return context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE).getInt(ID,-1);
    }
    public String getUserString(){
        return String.valueOf(getUserId());
    }
    public  String getPassword(){
        return context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE).getString(PASSWORD,"");
    }
    public int getContest(){
        return context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE).getInt(CONTEST,-1);
    }
    public String getContestString(){
        return String.valueOf(getContest());
    }

}
