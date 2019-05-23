package com.example.drcreeper.refereeapp.screens.register;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.screens.login.LoginFragment;

import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;

public class RegisterViewModel {

    private Context context;
    public FragmentWorker worker;
    private RegisterModel model;
    public Drawable iconOk;
    public Drawable iconError;
    public String errPass, errConnect, errExist;
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> lastname = new ObservableField<>("");
    public ObservableField<String>  name = new ObservableField<>("");
    public ObservableField<String> pass = new ObservableField<>("");
    public ObservableField<String> passRepeat = new ObservableField<>("");
    public ObservableField<String> status = new ObservableField<>("");
    public ObservableField<String> error = new ObservableField<>("");

    public ObservableField<Drawable> emailCheck = new ObservableField<>();
    public ObservableField<Drawable> nameCheck = new ObservableField<>();
    public ObservableField<Drawable> passCheck = new ObservableField<>();
    public ObservableField<Drawable> passRepeatCheck = new ObservableField<>();

    RegisterViewModel(Context context, FragmentWorker worker){
        this.context = context;
        this.worker = worker;
        model = new RegisterModel();
        iconOk =  ContextCompat.getDrawable(context,R.drawable.ic_ok);
        iconError = ContextCompat.getDrawable(context,R.drawable.ic_err);
        errPass = context.getString(R.string.reg_err_pass_lenth);
        errConnect = context.getString(R.string.reg_err_connect);
        errExist = context.getString(R.string.reeg_err_exist);


    }
    public void send(View view){
        model.send(this);
    }
    public void goChoose(){
        worker.switchFragment(new LoginFragment());
    }
}
