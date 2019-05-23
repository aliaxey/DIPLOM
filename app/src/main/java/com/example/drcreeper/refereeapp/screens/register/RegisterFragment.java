package com.example.drcreeper.refereeapp.screens.register;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.FragmentRegisterBinding;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;

import java.security.MessageDigest;

public class RegisterFragment extends Fragment {
    private FragmentWorker fragmentWorker;

    public RegisterFragment(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentWorker = (FragmentWorker) getActivity();
        fragmentWorker.setHeader(R.string.reg_header);
        RegisterViewModel viewModel = new RegisterViewModel(getContext(),fragmentWorker);
        FragmentRegisterBinding binding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_register,container,false);
        binding.setVm(viewModel);
        return binding.getRoot();
    }

}
