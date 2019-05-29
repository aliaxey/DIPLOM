package com.example.drcreeper.refereeapp.screens.menu;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.RefereeApp;
import com.example.drcreeper.refereeapp.SharedPreferencesWorker;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.models.ContestInfo;
import com.example.drcreeper.refereeapp.screens.addteam.AddTeamFragment;
import com.example.drcreeper.refereeapp.screens.contest.ContestFragment;
import com.example.drcreeper.refereeapp.screens.contestchoose.ContestChooseFragment;
import com.example.drcreeper.refereeapp.screens.fields.FieldsFragment;
import com.example.drcreeper.refereeapp.screens.results.ResultsFragment;

import androidx.databinding.BaseObservable;
import androidx.fragment.app.Fragment;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuViewModel extends BaseObservable {
    private FragmentWorker fragmentWorker;
    private Context context;
    private String name, key;
    private boolean isAdmin;
    private ContestInfo body;

    MenuViewModel(Context ctx, FragmentWorker worker){
        super();
        fragmentWorker = worker;
        context  = ctx;
        setAdmin(false);
        final SharedPreferencesWorker preferences = new SharedPreferencesWorker(context);
        if(preferences.getContestName()!=""){
            setName(context.getString(R.string.contest_name)+preferences.getContestName());
            setKey(context.getString(R.string.contest_key)+preferences.getContestCode());
        }else{
            setName(context.getString(R.string.contest_name)+"...");
            setKey(context.getString(R.string.contest_key)+"...");
        }
        FormBody request = preferences.getContestRequest().build();
        RefereeApp.getInstance().service.getInfo(request).enqueue(new Callback<ContestInfo>() {
            @Override
            public void onResponse(Call<ContestInfo> call, Response<ContestInfo> response) {
                body = response.body();
                if(body!=null){
                    setName(context.getString(R.string.contest_name)+body.getName());
                    setKey(context.getString(R.string.contest_key)+body.getId()+body.getKey());
                    setAdmin(body.getAccess()>0);
                    preferences.setContestData(body.getName(),body.getId()+body.getKey());
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
                fragmentWorker.switchFragment(new ContestFragment());
                break;
            case R.id.btn_reg:
                fragmentWorker.switchFragment(new AddTeamFragment());
                break;
            case R.id.btn_results:
                fragmentWorker.switchFragment(new ResultsFragment());
                break;
            case R.id.btn_edit:
                fragmentWorker.switchFragment(new FieldsFragment());
                break;
        }
    }

}
