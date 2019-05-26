package com.example.drcreeper.refereeapp.screens.contest;

import android.content.Context;
import android.widget.Toast;

import com.example.drcreeper.refereeapp.Answer;
import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.RefereeApp;
import com.example.drcreeper.refereeapp.SharedPreferencesWorker;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.models.ContestTab;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import androidx.databinding.BaseObservable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestViewModel extends BaseObservable {
    private Context context;
    private FragmentWorker fragmentWorker;
    private SharedPreferencesWorker preferencesWorker;
    ContestRecyclerViewAdapter adapter;
    int width;

    public ContestViewModel(Context ctx, final FragmentWorker worker){
        context = ctx;
        fragmentWorker = worker;
        width = 2;
        preferencesWorker = new SharedPreferencesWorker(context);
        final ContestViewModel base = this;
        RefereeApp.getInstance().service.getContest(preferencesWorker.getContestRequest().build())
                .enqueue(new Callback<ContestTab>() {
                    @Override
                    public void onResponse(Call<ContestTab> call, Response<ContestTab> response) {
                        if(response.body()!= null){
                            setAdapter(new ContestRecyclerViewAdapter(base, response.body()));
                            setWidth(adapter.getWidth());
                        }else{
                            onFailure(call,new NullPointerException());
                        }
                    }

                    @Override
                    public void onFailure(Call<ContestTab> call, Throwable t) {
                        Toast.makeText(context, R.string.reg_err_connect, Toast.LENGTH_LONG).show();
                        worker.onBack();
                    }
                });

    }

    public ContestRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ContestRecyclerViewAdapter adapter) {
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
    public void onSendResults(int row){
        JsonArray array = new JsonArray();
        for(TableField field : adapter.getValues(row)){
            JsonObject json = new JsonObject();
            if(field.getValue() != null && !field.getValue().isEmpty()){
                try{
                    int value = Integer.parseInt(field.getValue());
                    if(value>=0 && value<=field.getMaxValue()) {
                        json.addProperty("id",field.getField());
                        json.addProperty("team",field.getTeam());
                        json.addProperty("value",value);
                        array.add(json);
                    }else {
                        showText(R.string.err_max_value);
                        return;
                    }
                }catch (NumberFormatException e){
                    showText(R.string.err_number);
                    return;
                }
            }else {
                showText(R.string.err_null_values);
                return;
            }
        }
        RequestBody body = preferencesWorker.getContestRequest()
                .add("values",array.toString())
                .build();
        RefereeApp.getInstance().service.sendMarks(body).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                if(response.body()!= null&&response.body().getResult()>=0){
                    showText(R.string.success);
                }else {
                    showText(R.string.reg_err_connect);
                }
            }

            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                showText(R.string.reg_err_connect);
            }
        });
    }
    public void onSendAllResults(){
        for (int i = 0;i<adapter.getValuesCount();i++){
            onSendResults(i);
        }
    }
    private void showText(int errorId){
        Toast.makeText(context,errorId,Toast.LENGTH_LONG).show();
    }
}
