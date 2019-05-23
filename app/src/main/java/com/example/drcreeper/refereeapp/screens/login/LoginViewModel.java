package com.example.drcreeper.refereeapp.screens.login;

import android.content.Context;
import android.view.View;

import com.example.drcreeper.refereeapp.Answer;
import com.example.drcreeper.refereeapp.Constants;
import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.RefereeApp;
import com.example.drcreeper.refereeapp.SharedPreferencesWorker;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.screens.contestchoose.ContestChooseFragment;

import androidx.databinding.BaseObservable;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends BaseObservable {
    private FragmentWorker fragmentWorker;
    private String email, password, error = new String();
    private  boolean emailCheck, passwordCheck;

    LoginViewModel(FragmentWorker worker){
        fragmentWorker = worker;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyChange();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyChange();
    }

    public boolean isEmailCheck() {
        return emailCheck;
    }

    public void setEmailCheck(boolean emailCheck) {
        this.emailCheck = emailCheck;
        notifyChange();
    }

    public boolean isPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(boolean passwordCheck) {
        this.passwordCheck = passwordCheck;
        notifyChange();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        notifyChange();
    }

    public void onLogin(View v){
        setError("");
        if(validEmail(email)) {
            setEmailCheck(true);
            if (password != null && password.length() >= Constants.PASS_MIN_SIZE) {
                setPasswordCheck(true);
                RequestBody user = new FormBody.Builder()
                        .add("email", getEmail())
                        .add("pass", getPassword())
                        .build();
                final Context c = v.getContext();
                RefereeApp.getInstance().service.loginUser(user).enqueue(new Callback<Answer>() {
                    @Override
                    public void onResponse(Call<Answer> call, Response<Answer> response) {
                        int result = response.body().getResult();
                        if (result > 0) {
                            SharedPreferencesWorker worker = new SharedPreferencesWorker(c);
                            worker.setUser(result,password);
                            fragmentWorker.switchFragment(new ContestChooseFragment());
                        } else {
                            switch (result) {
                                case -1:
                                    setEmailCheck(false);
                                    setError(c.getString(R.string.login_err_bad_email));
                                    break;
                                case -2:{
                                    setPasswordCheck(false);
                                    setError(c.getString(R.string.login_err_wrong_pass));
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Answer> call, Throwable t) {
                        setError(c.getString(R.string.reg_err_connect));
                    }
                });
            }else {
                setPasswordCheck(false);
            }
        }else {
            setEmailCheck(false);
        }
    }
    private boolean validEmail(String email){
        if(email == null){
            return  false;
        }
        if(email.isEmpty()){
            return false;
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
