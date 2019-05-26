package com.example.drcreeper.refereeapp.screens.contest;

import android.content.Context;
import android.view.View;

import com.example.drcreeper.refereeapp.databinding.TableFieldBinding;
import com.example.drcreeper.refereeapp.databinding.TableHeaderBinding;
import com.example.drcreeper.refereeapp.databinding.TableInfoBinding;
import com.example.drcreeper.refereeapp.databinding.TableSubmitBinding;

import androidx.recyclerview.widget.RecyclerView;

public class ContestViewHolder extends RecyclerView.ViewHolder {
    TableHeaderBinding header;
    TableFieldBinding field;
    TableInfoBinding info;
    TableSubmitBinding submit;
    public ContestViewHolder(Context ctx){
        super(new View(ctx));
    }
    public ContestViewHolder(TableHeaderBinding binding){
        super(binding.getRoot());
        header = binding;
    }
    public ContestViewHolder(TableFieldBinding binding){
        super(binding.getRoot());
        field = binding;
    }
    public ContestViewHolder(TableInfoBinding binding){
        super(binding.getRoot());
        info = binding;
    }
    public ContestViewHolder(TableSubmitBinding binding){
        super(binding.getRoot());
        submit = binding;
    }
    public void bind(TableText viewModel){
        switch (getItemViewType()){
            case ContestRecyclerViewAdapter.TYPE_HEADER:
                header.setVm(viewModel);
                break;
            case ContestRecyclerViewAdapter.TYPE_INFO:
                info.setVm(viewModel);
                break;
        }
    }
    public void bind(TableField viewModel){
        field.setVm(viewModel);
    }
    public void bind(TableSubmit viewModel){
        submit.setVm(viewModel);
    }
}
