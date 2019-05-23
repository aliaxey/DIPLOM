package com.example.drcreeper.refereeapp.screens.menu;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.FragmentMenuBinding;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {

    public MenuFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentWorker fragmentWorker = (FragmentWorker) getActivity();
        fragmentWorker.setHeader(R.string.menu_title);
        MenuViewModel viewModel = new MenuViewModel(getContext(),fragmentWorker);
        FragmentMenuBinding binding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_menu,container,false);
        binding.setVm(viewModel);
        return binding.getRoot();
    }


}
