package com.example.drcreeper.refereeapp.screens.results;

import android.content.Context;
import android.view.View;

import com.example.drcreeper.refereeapp.databinding.TableHeaderBinding;
import com.example.drcreeper.refereeapp.databinding.TableInfoBinding;
import com.example.drcreeper.refereeapp.databinding.TableMarkBinding;
import com.example.drcreeper.refereeapp.screens.contest.TableText;

import androidx.recyclerview.widget.RecyclerView;

public class ResultsViewHolder extends RecyclerView.ViewHolder {
    TableHeaderBinding header;
    TableInfoBinding info;
    TableMarkBinding mark;

    public ResultsViewHolder(Context ctx){
        super(new View(ctx));
    }
    public ResultsViewHolder(TableHeaderBinding binding){
        super(binding.getRoot());
        header = binding;
    }
    public ResultsViewHolder(TableInfoBinding binding){
        super(binding.getRoot());
        info = binding;
    }
    public ResultsViewHolder(TableMarkBinding binding){
        super(binding.getRoot());
        mark = binding;
    }
    public void bind(TableText viewModel){
        switch (getItemViewType()){
            case ResultsRecyclerViewAdapter.TYPE_HEADER:
                header.setVm(viewModel);
                break;
            case ResultsRecyclerViewAdapter.TYPE_INFO:
                info.setVm(viewModel);
                break;
        }
    }
    public void bind(TableMark viewModel){
        mark.setVm(viewModel);
    }
}
