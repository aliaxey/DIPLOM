package com.example.drcreeper.refereeapp.screens.contestchoose;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.example.drcreeper.refereeapp.Answer;
import com.example.drcreeper.refereeapp.FieldAnswer;
import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.RefereeApp;
import com.example.drcreeper.refereeapp.SharedPreferencesWorker;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.screens.menu.MenuFragment;

import java.nio.charset.Charset;
import java.util.Random;

import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.DialogTitle;
import androidx.databinding.BaseObservable;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestChooseViewModel extends BaseObservable {
    FragmentWorker fragmentWorker;
    private boolean isNewContest;
    private String code, name, error;

    public ContestChooseViewModel(FragmentWorker worker){
        fragmentWorker = worker;
        isNewContest = false;
    }
    public boolean isNewContest() {
        return isNewContest;
    }

    public void setNewContest(boolean newContest) {
        isNewContest = newContest;
        notifyChange();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        notifyChange();
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

    public void onSwitchClick(View v){
        switch (v.getId()){
            case R.id.case_code:
                setNewContest(false);
                break;
            case R.id.case_new:
                setNewContest(true);
                break;
        }
    }
    public void onContestChoose(View v){
        final Context context = v.getContext();
        final SharedPreferencesWorker preferences = new SharedPreferencesWorker(context);
        if(isCodeValid()){
            setError("");
            String[] keys = code.split("c",2);
            FormBody body = new FormBody.Builder()
                    .add("contest", keys[0])
                    .add("key","c" + keys[1])
                    .add("id",preferences.getUserString())
                    .add("pass",preferences.getPassword())
                    .build();
            RefereeApp.getInstance().service.checkContest(body).enqueue(new Callback<Answer>() {
                @Override
                public void onResponse(Call<Answer> call, Response<Answer> response) {
                    if(response.body()!=null&&response.body().getResult()>0){
                        preferences.setContest(response.body().getResult());
                        fragmentWorker.switchFragment(new MenuFragment());
                    }else {
                        setError(context.getString(R.string.choose_err_code));
                    }
                }

                @Override
                public void onFailure(Call<Answer> call, Throwable t) {
                    setError(context.getString(R.string.reg_err_connect));
                }
            });
        }else {
            setError(context.getString(R.string.choose_err_code));
        }
    }
    public void onNewContest(View v){
        final Context context = v.getContext();
        if(name!=null&&!name.isEmpty()){
            setError("");
            final String key = getKey();
            final SharedPreferencesWorker preferences = new SharedPreferencesWorker(context);
            final RequestBody body = new FormBody.Builder()
                    .add("name",getName())
                    .add("group","0")
                    .add("id",String.valueOf(preferences.getUserId()))
                    .add("pass",preferences.getPassword())
                    .add("key",key)
                    .build();
            RefereeApp.getInstance().service.newContest(body).enqueue(new Callback<Answer>() {
                @Override
                public void onResponse(Call<Answer> call, final Response<Answer> response) {
                    if(response.body()!=null){
                        final String code = String.valueOf(response.body().getResult()) + key;
                        DialogInterface.OnClickListener onCreateContest = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //setNewContest(false);
                                //setCode(code);
                                preferences.setContest(response.body().getResult());
                            }
                        };
                        AlertDialog dialog = new AlertDialog.Builder(context)
                                .setTitle(R.string.choose_new_ok)
                                .setMessage(context.getString(R.string.chooose_key) + code)
                                .setNeutralButton(R.string.login_in,onCreateContest)
                                .show();
                    }
                }
                @Override
                public void onFailure(Call<Answer> call, Throwable t) {
                    setError(context.getString(R.string.reg_err_connect));
                }
            });
        }else {
            setError(context.getString(R.string.choose_err_name));
        }
    }
    private boolean isCodeValid(){
        if(code == null || code.isEmpty()){
            return false;
        }
        if(code.split("c",2).length!=2) {
            return false;
        }
        return true;
    }
    private String getKey(){
        StringBuilder result = new StringBuilder("c");
        for (int i = 0;i<5;i++){
            result.append((char)(Math.random()*27+95));
        }
        return result.toString();
    }
}
