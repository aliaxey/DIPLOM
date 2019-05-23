package com.example.drcreeper.refereeapp;

import android.app.Application;
import android.content.Context;

import com.example.drcreeper.refereeapp.interfaces.RefereeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.core.app.ActivityCompat;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RefereeApp extends Application {
    private static RefereeApp instance;
    public RefereeService service;
    private RefereeApp(){
        super();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.BASE_IRL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(RefereeService.class);
    }
    public static RefereeApp getInstance(){
        if(instance == null){
            instance = new RefereeApp();
            instance.onCreate();
        }
        return instance;
    }
}
