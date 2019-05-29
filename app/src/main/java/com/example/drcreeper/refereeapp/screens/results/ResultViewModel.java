package com.example.drcreeper.refereeapp.screens.results;

import android.content.Context;
import android.widget.Toast;

import com.example.drcreeper.refereeapp.Answer;
import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.RefereeApp;
import com.example.drcreeper.refereeapp.SharedPreferencesWorker;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.models.Results;
import com.example.drcreeper.refereeapp.screens.contest.TableField;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import androidx.databinding.BaseObservable;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultViewModel extends BaseObservable {
    private Context context;
    private FragmentWorker fragmentWorker;
    private SharedPreferencesWorker preferencesWorker;
    ResultsRecyclerViewAdapter adapter;
    int width;

    public ResultViewModel(Context ctx, final FragmentWorker worker){
        context = ctx;
        fragmentWorker = worker;
        width = 2;
        preferencesWorker = new SharedPreferencesWorker(context);
        FormBody body = new FormBody.Builder()
                .add("contest",preferencesWorker.getContestString())
                .build();
        RefereeApp.getInstance().service.getResult(body).enqueue(new Callback<Results>() {
                    @Override
                    public void onResponse(Call<Results> call, Response<Results> response) {
                        if(response.body()!= null){
                            setAdapter(new ResultsRecyclerViewAdapter(context,response.body()));
                            setWidth(adapter.getWidth());
                        }else{
                            onFailure(call,new NullPointerException());
                        }
                    }

                    @Override
                    public void onFailure(Call<Results> call, Throwable t) {
                        Toast.makeText(context, R.string.reg_err_connect, Toast.LENGTH_LONG).show();
                        worker.onBack();
                    }
                });

    }

    public ResultsRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ResultsRecyclerViewAdapter adapter) {
        this.adapter = adapter;
        notifyChange();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        notifyChange();
    }

    private void showText(int errorId){
        Toast.makeText(context,errorId,Toast.LENGTH_LONG).show();
    }
}
