package com.example.drcreeper.refereeapp.screens.addinfo;

import android.content.Context;
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

public class AddInfoViewModel extends BaseObservable {
    private Context context;
    private FragmentWorker fragmentWorker;

    private String name;
    private String error;

    public AddInfoViewModel(Context ctx, FragmentWorker worker){
        context = ctx;
        fragmentWorker = worker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        addInfo();
    }
    private void addInfo(){
        SharedPreferencesWorker preferences = new SharedPreferencesWorker(context);
        FormBody body = preferences.getContestRequest()
                .add("name",name)
                .build();
        RefereeApp.getInstance().service.addInfo(body).enqueue(new Callback<Answer>() {
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
