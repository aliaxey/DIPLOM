package com.example.drcreeper.refereeapp.screens.login;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.FragmentLoginBinding;
import com.example.drcreeper.refereeapp.databinding.FragmentRegisterBinding;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.screens.register.RegisterViewModel;


public class LoginFragment extends Fragment {
    private FragmentWorker fragmentWorker;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentWorker = (FragmentWorker) getActivity();
        fragmentWorker.setHeader(R.string.login_title);
        LoginViewModel viewModel = new LoginViewModel(fragmentWorker);
        FragmentLoginBinding binding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        binding.setVm(viewModel);
        return binding.getRoot();
    }

}
