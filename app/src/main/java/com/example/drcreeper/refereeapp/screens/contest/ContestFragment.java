package com.example.drcreeper.refereeapp.screens.contest;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.FragmentContestBinding;
import com.example.drcreeper.refereeapp.databinding.TableHeaderBinding;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ContestFragment extends Fragment {


    public ContestFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentWorker fragmentWorker = (FragmentWorker) getActivity();
        fragmentWorker.setHeader(R.string.contest);
        ContestViewModel viewModel = new ContestViewModel(getContext(),fragmentWorker);
        FragmentContestBinding binding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_contest,container,false);
        binding.setVm(viewModel);
        binding.recycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        binding.recycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        binding.recycler.setHasFixedSize(false);




        return binding.getRoot();
    }

}
