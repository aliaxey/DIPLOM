package com.example.drcreeper.refereeapp.screens.register;

import android.util.Log;

import com.example.drcreeper.refereeapp.Answer;
import com.example.drcreeper.refereeapp.Constants;
import com.example.drcreeper.refereeapp.RefereeApp;
import com.example.drcreeper.refereeapp.models.RegisterData;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterModel {
    private boolean validNames(String name){
        return !name.trim().isEmpty();
    }
    private boolean validEmail(String email){
        if(email.isEmpty()){
            return false;
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean validPassword(String pass){
        return pass.trim().length() >= Constants.PASS_MIN_SIZE;
    }
    private boolean validPasswordRepeat(String pass1,String pass2){
        return !(pass1.isEmpty()) && pass1.equals(pass2);
    }
    public void send(final RegisterViewModel vm){
        vm.error.set("");
        boolean ok = true;
        if(validEmail(vm.email.get())){
            vm.emailCheck.set(vm.iconOk);
        }else {
            vm.emailCheck.set(vm.iconError);
            ok = false;
        }
        if(validNames(vm.name.get())){
            vm.nameCheck.set(vm.iconOk);
        }else {
            vm.nameCheck.set(vm.iconError);
            ok = false;
        }
        if(validPassword(vm.pass.get())){
            vm.passCheck.set(vm.iconOk);
        }else {
            vm.passCheck.set(vm.iconError);
            vm.error.set(vm.errPass);
            ok = false;
        }
        if(validPasswordRepeat(vm.pass.get(),vm.passRepeat.get())){
            vm.passRepeatCheck.set(vm.iconOk);
        }else {
            vm.passRepeatCheck.set(vm.iconError);
            ok = false;
        }
        if(ok){
            RequestBody b = new FormBody.Builder()
                    .add("email",vm.email.get())
                    .add("name",vm.name.get())
                    .add("lastname", vm.lastname.get())
                    .add("pass",vm.pass.get())
                    .add("status",vm.status.get())
                    .build();
            RefereeApp.getInstance().service.registerUser(b).enqueue(new Callback<Answer>() {
                @Override
                public void onResponse(Call<Answer> call, Response<Answer> response) {
                    switch (response.body().getResult()){
                        case 0:
                            vm.goChoose();
                            break;
                        case -1:
                            vm.error.set(vm.errExist);
                            break;
                    }
                }

                @Override
                public void onFailure(Call<Answer> call, Throwable t) {
                    vm.error.set(vm.errConnect);
                }
            });
        }

    }
}
