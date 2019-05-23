package com.example.drcreeper.refereeapp;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BindingAdapters {

    @BindingAdapter({"app:res_id"})
    public static void setFileIcon(ImageView imageView, int resId){
        imageView.setImageResource(resId);
    }
    @BindingAdapter({"app:manager"})
    public static void setLayoutManager(RecyclerView recyclerView, int managerId){
        switch (managerId){
            case 1:
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                break;
            default:
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),managerId));
        }
    }
    @BindingAdapter({"app:visible"})
    public static void setVisiblity(View v, boolean visible){
        if(visible) {
            v.setVisibility(View.VISIBLE);
        }else {
            v.setVisibility(View.INVISIBLE);
        }
    }
}
