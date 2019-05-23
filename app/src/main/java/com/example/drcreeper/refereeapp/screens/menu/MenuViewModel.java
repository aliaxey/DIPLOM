package com.example.drcreeper.refereeapp.screens.menu;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.RefereeApp;
import com.example.drcreeper.refereeapp.SharedPreferencesWorker;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.models.ContestInfo;
import com.example.drcreeper.refereeapp.screens.contestchoose.ContestChooseFragment;
import com.example.drcreeper.refereeapp.screens.fields.FieldsFragment;

import androidx.databinding.BaseObservable;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuViewModel extends BaseObservable {
    private FragmentWorker fragmentWorker;
    private Context context;
    private String name, key;
    private boolean isAdmin;

    MenuViewModel(Context ctx, FragmentWorker worker){
        super();
        fragmentWorker = worker;
        context  = ctx;
        setName(context.getString(R.string.contest_name)+"...");
        setKey(context.getString(R.string.contest_key)+"...");
        setAdmin(false);
        SharedPreferencesWorker preferences = new SharedPreferencesWorker(context);
        FormBody request = new FormBody.Builder()
                .add("id",preferences.getUserString())
                .add("pass",preferences.getPassword())
                .add("contest",preferences.getContestString())
                .build();
        RefereeApp.getInstance().service.getInfo(request).enqueue(new Callback<ContestInfo>() {
            @Override
            public void onResponse(Call<ContestInfo> call, Response<ContestInfo> response) {
                ContestInfo body = response.body();
                if(body!=null){
                    setName(body.getName());
                    setKey(body.getId()+body.getKey());
                    setAdmin(body.getAccess()>0);
                }else{
                    onFailure(call,null);
                }
            }

            @Override
            public void onFailure(Call<ContestInfo> call, Throwable t) {
                Toast.makeText(context,R.string.reg_err_connect,Toast.LENGTH_LONG).show();
                fragmentWorker.switchFragment(new ContestChooseFragment());
            }
        });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
        notifyChange();
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
        notifyChange();
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_start:

                break;
            case R.id.btn_reg:

                break;
            case R.id.btn_edit:
                fragmentWorker.switchFragment(new FieldsFragment());
                break;
        }
    }
}
