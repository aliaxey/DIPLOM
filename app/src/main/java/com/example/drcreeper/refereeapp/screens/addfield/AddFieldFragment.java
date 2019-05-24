package com.example.drcreeper.refereeapp.screens.addfield;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.FragmentAddFieldBinding;
import com.example.drcreeper.refereeapp.databinding.FragmentFieldsBinding;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.screens.fields.FieldsViewModel;

public class AddFieldFragment extends Fragment {


    public AddFieldFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentWorker fragmentWorker = (FragmentWorker) getActivity();
        fragmentWorker.setHeader(R.string.add_field);
        AddFieldViewModel viewModel = new AddFieldViewModel(getContext(),fragmentWorker);
        FragmentAddFieldBinding binding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_add_field,container,false);
        binding.setVm(viewModel);
        return binding.getRoot();
    }

}
