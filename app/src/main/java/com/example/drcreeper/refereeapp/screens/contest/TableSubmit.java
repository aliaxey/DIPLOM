package com.example.drcreeper.refereeapp.screens.contest;

import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

public class TableSubmit extends BaseObservable {
    private ContestViewModel viewModel;
    private int row;

    public TableSubmit(ContestViewModel viewModel, int row){
        this.viewModel = viewModel;
        this.row = row;
    }
    public void onSubmit(View v){
        if(row == -1){
            viewModel.onSendAllResults();
        }else {
            viewModel.onSendResults(row);
        }
    }
}
