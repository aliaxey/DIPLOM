package com.example.drcreeper.refereeapp.screens.fields;

import android.content.Context;
import android.view.View;

import com.example.drcreeper.refereeapp.RefereeApp;
import com.example.drcreeper.refereeapp.SharedPreferencesWorker;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.models.ContestFields;
import com.example.drcreeper.refereeapp.screens.addfield.AddFieldFragment;
import com.example.drcreeper.refereeapp.screens.addinfo.AddInfoFragment;

import androidx.databinding.BaseObservable;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FieldsViewModel extends BaseObservable {
    private FragmentWorker fragmentWorker;
    private Context context;
    private FieldsRecyclerViewAdapter adapter;


    public FieldsViewModel(Context ctx, FragmentWorker worker) {
        super();
        fragmentWorker = worker;
        context = ctx;
        refresh();
    }


    public FieldsRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(FieldsRecyclerViewAdapter adapter) {
        this.adapter = adapter;
        notifyChange();
    }
    public void addField(View v){
        fragmentWorker.switchFragment(new AddFieldFragment());
    }
    public void addInfo(View v){
        fragmentWorker.switchFragment(new AddInfoFragment());
    }
    private void refresh(){
        SharedPreferencesWorker preferences = new SharedPreferencesWorker(context);
        FormBody body = new FormBody.Builder()
                .add("id",preferences.getUserString())
                .add("pass",preferences.getPassword())
                .add("contest",preferences.getContestString())
                .build();
        RefereeApp.getInstance().service.getFields(body).enqueue(new Callback<ContestFields>() {
            @Override
            public void onResponse(Call<ContestFields> call, Response<ContestFields> response) {
                if(response.body()!=null){
                    setAdapter(new FieldsRecyclerViewAdapter(response.body()));
                }else {
                    onFailure(call,new NullPointerException());
                }
            }

            @Override
            public void onFailure(Call<ContestFields> call, Throwable t) {
                setAdapter(new FieldsRecyclerViewAdapter());
            }
        });
    }
}