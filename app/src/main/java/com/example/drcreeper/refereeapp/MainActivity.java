package com.example.drcreeper.refereeapp;

import android.os.Bundle;

import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.screens.contestchoose.ContestChooseFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentWorker {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getTransaction().add(R.id.root,new ContestChooseFragment()).commitNow();
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

    private FragmentTransaction getTransaction(){
        return getSupportFragmentManager().beginTransaction();
    }
}
