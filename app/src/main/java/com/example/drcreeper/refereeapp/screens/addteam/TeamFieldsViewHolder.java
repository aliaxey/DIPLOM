package com.example.drcreeper.refereeapp.screens.addteam;

import com.example.drcreeper.refereeapp.databinding.ItemTeamBinding;

import androidx.recyclerview.widget.RecyclerView;

public class TeamFieldsViewHolder extends RecyclerView.ViewHolder {
    ItemTeamBinding teamBinding;
    public TeamFieldsViewHolder(ItemTeamBinding binding) {
        super(binding.getRoot());
        teamBinding = binding;
    }
    public void setData(InfoViewModel viewModel){
        teamBinding.setVm(viewModel);
    }
}
