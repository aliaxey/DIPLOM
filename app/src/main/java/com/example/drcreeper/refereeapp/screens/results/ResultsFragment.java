package com.example.drcreeper.refereeapp.screens.results;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.FragmentResultBinding;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;

public class ResultsFragment extends Fragment {


    public ResultsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentWorker fragmentWorker = (FragmentWorker) getActivity();
        fragmentWorker.setHeader(R.string.result_header);
        ResultViewModel viewModel = new ResultViewModel(getContext(),fragmentWorker);
        FragmentResultBinding binding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_result,container,false);
        binding.setVm(viewModel);
        binding.recycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        binding.recycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        return binding.getRoot();
    }

}
