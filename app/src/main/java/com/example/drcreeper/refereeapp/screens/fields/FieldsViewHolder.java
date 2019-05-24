package com.example.drcreeper.refereeapp.screens.fields;

import android.view.View;

import com.example.drcreeper.refereeapp.databinding.ItemFieldBinding;
import com.example.drcreeper.refereeapp.databinding.ItemInfoBinding;
import com.example.drcreeper.refereeapp.models.Field;
import com.example.drcreeper.refereeapp.models.Header;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FieldsViewHolder extends RecyclerView.ViewHolder {
    private ItemInfoBinding infoBinding;
    private ItemFieldBinding fieldBinding;


    public FieldsViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public FieldsViewHolder(ItemInfoBinding binding){
        super(binding.getRoot());
        infoBinding = binding;
    }
    public FieldsViewHolder(ItemFieldBinding binding){
        super(binding.getRoot());
        fieldBinding = binding;
    }
    public void setItemHeader(Header header){
        ItemInfo info = new ItemInfo();
        info.setName(header.getName());
        infoBinding.setVm(info);
    }
    public void setItemField(Field field){
        ItemField info = new ItemField();
        info.setName(field.getName());
        info.setMaxValue(field.getMaxValue());
        info.setFactor(field.getFactor());
        fieldBinding.setVm(info);
    }
    public View getView(){
        return itemView;
    }
}
