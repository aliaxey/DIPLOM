package com.example.drcreeper.refereeapp.screens.contestchoose;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.FragmentContestChooseBinding;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.screens.login.LoginFragment;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


public class ContestChooseFragment extends Fragment {
    private FragmentWorker fragmentWorker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentWorker = (FragmentWorker) getActivity();
        fragmentWorker.setHeader(R.string.choose_header);
        setHasOptionsMenu(true);
        ContestChooseViewModel viewModel = new ContestChooseViewModel(fragmentWorker);
        FragmentContestChooseBinding binding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_contest_choose,container,false);
        binding.setVm(viewModel);
        return binding.getRoot();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.half_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                fragmentWorker.switchFragment(new LoginFragment());
                break;
        }
        return true;
    }
}
