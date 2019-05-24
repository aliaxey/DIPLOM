package com.example.drcreeper.refereeapp.screens.fields;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.ItemFieldBinding;
import com.example.drcreeper.refereeapp.databinding.ItemInfoBinding;
import com.example.drcreeper.refereeapp.models.ContestFields;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class FieldsRecyclerViewAdapter extends RecyclerView.Adapter<FieldsViewHolder> {
    private static final int HEADER_INFO = 1;
    private static final int HEADER_FIELD = 2;
    private static final int INFO = 3;
    private static final int FIELD = 4;

    private ContestFields fields;
    private int fieldsStart = 0;
    private List<Integer> map;

    public FieldsRecyclerViewAdapter(){
        super();
    }

    public FieldsRecyclerViewAdapter(ContestFields fields){
        this.fields = fields;
        map = new ArrayList<>();
        if(fields!=null){
            if(fields.getHeaders()!= null && !fields.getHeaders().isEmpty()){
                map.add(HEADER_INFO);
                fillMap(fields.getHeaders().size(),INFO);
            }
            if(fields.getFields()!= null && !fields.getFields().isEmpty()){
                map.add(HEADER_FIELD);
                fieldsStart = map.size();
                fillMap(fields.getFields().size(),FIELD);
            }
        }
    }

    @NonNull
    @Override
    public FieldsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FieldsViewHolder holder;
        switch (viewType){
            case HEADER_INFO:
                holder = new FieldsViewHolder(inflater.inflate(R.layout.item_info_header,parent,false));
                break;
            case HEADER_FIELD:
                holder = new FieldsViewHolder(inflater.inflate(R.layout.item_field_header,parent,false));
                break;
            case INFO:
                ItemInfoBinding infoBinding = DataBindingUtil.inflate(inflater,R.layout.item_info,parent, false);
                holder = new FieldsViewHolder(infoBinding);
                break;
            case FIELD:
                ItemFieldBinding fieldBinding = DataBindingUtil.inflate(inflater,R.layout.item_field,parent,false);
                holder = new FieldsViewHolder(fieldBinding);
                break;
                default:
                     holder = new FieldsViewHolder(new View(parent.getContext()));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FieldsViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case INFO:
                holder.setItemHeader(fields.getHeaders().get(position-1));
                break;
            case FIELD:
                holder.setItemField(fields.getFields().get(position-fieldsStart));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return map.size();
    }

    @Override
    public int getItemViewType(int position) {
        return map.get(position);
    }
    private void fillMap(int size,int value){
        for(int i = 0;i < size;i++){
            map.add(value);
        }
    }
}
