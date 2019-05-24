package com.example.drcreeper.refereeapp.screens.addfield;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.drcreeper.refereeapp.Answer;
import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.RefereeApp;
import com.example.drcreeper.refereeapp.SharedPreferencesWorker;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;

import androidx.databinding.BaseObservable;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFieldViewModel extends BaseObservable {
    private Context context;
    private FragmentWorker fragmentWorker;

    private String name;
    private String maxValue;
    private String  factor;
    private String error;
    private static final String  INITIAL_MAX_VALUE = "10";
    private static final String  INITIAL_FACTOR  = "1";

    public AddFieldViewModel(Context ctx,FragmentWorker worker){
        context = ctx;
        fragmentWorker = worker;
        setMaxValue(INITIAL_MAX_VALUE);
        setFactor(INITIAL_FACTOR);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

    public String getMaxValue() {
        return String.valueOf(maxValue);
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
        notifyChange();
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
        notifyChange();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        notifyChange();
    }

    public void  onSendClick(View v) {
        setError("");
        if (name == null || name.isEmpty()) {
            setError(context.getString(R.string.err_no_name));
            return;
        }
        try {
            int max = Integer.parseInt(getMaxValue());
            double factor = Double.parseDouble(getFactor());
            if (max < 1) {
                setError(context.getString(R.string.err_wrong_max));
                return;
            }
            if (factor <= 0) {
                setError(context.getString(R.string.err_wrong_factor));
                return;
            }
            addField();
        } catch (NumberFormatException e) {
            setError(context.getString(R.string.wrong_number));
        }
    }
    private void addField(){
        SharedPreferencesWorker preferences = new SharedPreferencesWorker(context);
        FormBody body = preferences.getContestRequest()
                .add("name",name)
                .add("max",String.valueOf(maxValue))
                .add("factor",String.valueOf(factor))
                .build();
        RefereeApp.getInstance().service.addField(body).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                if(response.body()!= null && response.body().getResult()>=0){
                    setError(String.valueOf(response.body().getResult()));
                    fragmentWorker.onBack();
                }else {
                    onFailure(call,null);
                }
            }
            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                setError(context.getString(R.string.reg_err_connect));
            }
        });
    }

}
