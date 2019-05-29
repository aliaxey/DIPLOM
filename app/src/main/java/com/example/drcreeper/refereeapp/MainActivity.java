package com.example.drcreeper.refereeapp;

import android.os.Bundle;

import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.screens.contestchoose.ContestChooseFragment;
import com.example.drcreeper.refereeapp.screens.fields.FieldsFragment;
import com.example.drcreeper.refereeapp.screens.login.LoginFragment;
import com.example.drcreeper.refereeapp.screens.menu.MenuFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentWorker {
    static final String  FRAGMENT_CREATED = "created";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null && savedInstanceState.getBoolean(FRAGMENT_CREATED)){
            return;
        }
        Fragment fragment;
        switch (new SharedPreferencesWorker(this).getState()){
            case SharedPreferencesWorker.SET_CONTEST:
                fragment = new MenuFragment();
                break;
            case SharedPreferencesWorker.SET_USER:
                fragment = new ContestChooseFragment();
                break;
            case SharedPreferencesWorker.INITIAL:
                default:
                    fragment = new LoginFragment();
        }
        getTransaction().add(R.id.root,fragment).commitNow();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FRAGMENT_CREATED,true);
    }

    public void switchFragment(Fragment newFragment){
        FragmentTransaction transaction = getTransaction();
        transaction.replace(R.id.root,newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void setHeader(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setHeader(int title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBack() {
        getSupportFragmentManager().popBackStack();
    }

    private FragmentTransaction getTransaction(){
        return getSupportFragmentManager().beginTransaction();
    }
}
