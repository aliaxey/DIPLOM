package com.example.drcreeper.refereeapp.screens.addinfo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.FragmentAddInfoBinding;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class AddInfoFragment extends Fragment {


    public AddInfoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentWorker fragmentWorker = (FragmentWorker) getActivity();
        fragmentWorker.setHeader(R.string.add_info);
        AddInfoViewModel viewModel = new AddInfoViewModel(getContext(),fragmentWorker);
        FragmentAddInfoBinding binding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_add_info,container,false);
        binding.setVm(viewModel);
        return binding.getRoot();
    }

}
