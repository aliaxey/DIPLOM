package com.example.drcreeper.refereeapp.screens.addteam;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.FragmentAddFieldBinding;
import com.example.drcreeper.refereeapp.databinding.FragmentAddTeamBinding;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.screens.addfield.AddFieldViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class AddTeamFragment extends Fragment {


    public AddTeamFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentWorker fragmentWorker = (FragmentWorker) getActivity();
        fragmentWorker.setHeader(R.string.add_team_title);
        AddTeamViewModel viewModel = new AddTeamViewModel(getContext(),fragmentWorker);
        FragmentAddTeamBinding binding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_add_team,container,false);
        binding.setVm(viewModel);
        return binding.getRoot();
    }

}
