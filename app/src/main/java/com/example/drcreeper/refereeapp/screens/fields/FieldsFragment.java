package com.example.drcreeper.refereeapp.screens.fields;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.FragmentFieldsBinding;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class FieldsFragment extends Fragment {


    public FieldsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentWorker fragmentWorker = (FragmentWorker) getActivity();
        fragmentWorker.setHeader(R.string.fields_title);
        FieldsViewModel viewModel = new FieldsViewModel(getContext(),fragmentWorker);
        FragmentFieldsBinding binding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_fields,container,false);
        binding.setVm(viewModel);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return binding.getRoot();
    }

}
