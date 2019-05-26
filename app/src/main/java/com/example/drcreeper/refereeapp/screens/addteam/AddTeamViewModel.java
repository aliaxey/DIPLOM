package com.example.drcreeper.refereeapp.screens.addteam;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.drcreeper.refereeapp.Answer;
import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.RefereeApp;
import com.example.drcreeper.refereeapp.SharedPreferencesWorker;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.models.ContestFields;


import androidx.databinding.BaseObservable;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTeamViewModel extends BaseObservable {
    private Context context;
    private FragmentWorker fragmentWorker;
    private TeamFieldsRecyclerViewAdapter adapter;
    private String error;

    public AddTeamViewModel(Context ctx,FragmentWorker worker){
        context = ctx;
        fragmentWorker = worker;
        SharedPreferencesWorker preferences  = new SharedPreferencesWorker(context);
        RefereeApp.getInstance().service.getFields(preferences.getContestRequest().build())
                .enqueue(new Callback<ContestFields>() {
                    @Override
                    public void onResponse(Call<ContestFields> call, Response<ContestFields> response) {
                        if(response.body()!=null){
                            if(response.body().getHeaders().size()<1||response.body().getFields().size()<1){
                                Toast.makeText(context,R.string.err_no_properties,Toast.LENGTH_LONG).show();
                                fragmentWorker.onBack();
                            }else{
                                setAdapter(new TeamFieldsRecyclerViewAdapter(response.body().getHeaders()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ContestFields> call, Throwable t) {
                        setError(context.getString(R.string.reg_err_connect));
                    }
                });
    }

    public TeamFieldsRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(TeamFieldsRecyclerViewAdapter adapter) {
        this.adapter = adapter;
        notifyChange();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        notifyChange();
    }

    public void onClick(View v){
        SharedPreferencesWorker preferences = new SharedPreferencesWorker(context);
        FormBody.Builder builder = preferences.getContestRequest();
        for(InfoViewModel viewModel : adapter.getValues()){
            if(viewModel.getValue()!= null && !viewModel.getValue().isEmpty()){
                builder.add(String.valueOf(viewModel.getId()),viewModel.getValue());
            }else {
                setError(context.getString(R.string.err_null_values));
                return;
            }
        }
        RefereeApp.getInstance().service.addTeam(builder.build()).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                if(response.body()!= null && response.body().getResult() >=0){
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
