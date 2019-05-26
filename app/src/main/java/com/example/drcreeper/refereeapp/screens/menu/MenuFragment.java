package com.example.drcreeper.refereeapp.screens.menu;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.FragmentMenuBinding;
import com.example.drcreeper.refereeapp.interfaces.FragmentWorker;
import com.example.drcreeper.refereeapp.screens.contestchoose.ContestChooseFragment;
import com.example.drcreeper.refereeapp.screens.login.LoginFragment;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {

    FragmentWorker fragmentWorker;
    public MenuFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentWorker = (FragmentWorker) getActivity();
        fragmentWorker.setHeader(R.string.menu_title);
        MenuViewModel viewModel = new MenuViewModel(getContext(),fragmentWorker);
        setHasOptionsMenu(true);
        FragmentMenuBinding binding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_menu,container,false);
        binding.setVm(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.full_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.setcontest:
                fragmentWorker.switchFragment(new ContestChooseFragment());
                break;
            case R.id.logout:
                fragmentWorker.switchFragment(new LoginFragment());
                break;
        }
        return true;
    }
}
