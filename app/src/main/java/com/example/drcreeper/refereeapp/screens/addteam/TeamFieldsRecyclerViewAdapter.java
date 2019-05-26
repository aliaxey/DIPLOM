package com.example.drcreeper.refereeapp.screens.addteam;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.ItemTeamBinding;
import com.example.drcreeper.refereeapp.models.Header;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class TeamFieldsRecyclerViewAdapter extends RecyclerView.Adapter<TeamFieldsViewHolder> {
    private List<Header> list;
    private List<InfoViewModel> viewModels;
    public TeamFieldsRecyclerViewAdapter(List<Header> list){
        this.list = list;
        viewModels = new ArrayList<>();
    }
    @NonNull
    @Override
    public TeamFieldsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTeamBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_team,parent,false);
        return new TeamFieldsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamFieldsViewHolder holder, int position) {
        InfoViewModel viewModel = new InfoViewModel(list.get(position));
        viewModels.add(viewModel);
        holder.setData(viewModel);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public List<InfoViewModel> getValues(){
        return viewModels;
    }
}
