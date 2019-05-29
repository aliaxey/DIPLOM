package com.example.drcreeper.refereeapp.screens.contest;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.TableFieldBinding;
import com.example.drcreeper.refereeapp.databinding.TableHeaderBinding;
import com.example.drcreeper.refereeapp.databinding.TableInfoBinding;
import com.example.drcreeper.refereeapp.databinding.TableSubmitBinding;
import com.example.drcreeper.refereeapp.models.ContestTab;
import com.example.drcreeper.refereeapp.models.Field;
import com.example.drcreeper.refereeapp.models.Header;
import com.example.drcreeper.refereeapp.models.Team;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ContestRecyclerViewAdapter extends RecyclerView.Adapter<ContestViewHolder> {
    static final int TYPE_HEADER = 1;
    static final int TYPE_INFO = 2;
    static final int TYPE_FIELD = 3;
    static final int TYPE_SUBMIT = 4;

    private int width;
    private int rows;
    private int infoPart;
    private List<String> headers;
    private List<List<String>> info;
    private List<List<TableField>> values;
    private List<TableSubmit> submits;
    private ArraySet<Integer> teams;

    public ContestRecyclerViewAdapter(ContestViewModel base, ContestTab fields){
        headers = new ArrayList<>();
        info = new ArrayList<>();
        values = new ArrayList<>();
        submits = new ArrayList<>();
        headers.add("#");
        infoPart = fields.getHeaders().size();
        width = 2 + fields.getHeaders().size() + fields.getFields().size();
        teams  = new ArraySet<>();
        rows = 0;
        for (Team team : fields.getTeams()){
            if (!teams.contains(team.getTeamId())) {
                teams.add(team.getTeamId());
                rows++;
            }
        }
        //rows = fields.getTeams().size()/infoPart;
        for(Header header:fields.getHeaders()){
            headers.add(header.getName());
        }
        for (Field field:fields.getFields()){
            headers.add(field.getName() +"("+field.getMaxValue()+")");
        }
        for(int team:teams){
            List<String> infoRow = new ArrayList<>();
            infoRow.add(String.valueOf(team));
            for (Header header : fields.getHeaders()){
                infoRow.add(getInfo(fields.getTeams(),header.getId(),team));
            }
            info.add(infoRow);
            List<TableField> holders = new ArrayList<>();
            for (Field field : fields.getFields()) {
                holders.add(new TableField(team, field.getId(), field.getMaxValue()));
            }
            values.add(holders);
            submits.add(new TableSubmit(base,teams.indexOf(team)));
        }
        submits.add(new TableSubmit(base,-1));
        /*
        for (int i = 0;i < rows; i++){

            List<String> infoRow = new ArrayList<>();
            int team = fields.getTeams().get(i*infoPart).getTeamId();
            infoRow.add(String.valueOf(team));
            for (int j = 1;j < infoPart+1;j++){
                infoRow.add(fields.getTeams().get(i*infoPart +(j -1)).getValue());
            }

            //List<String> infoRow = new ArrayList<>();
            //for (int team: teams){
            //    infoRow.add(String.valueOf(team));}
            info.add(infoRow);
            List<TableField> holders  = new ArrayList<>();
            for (Field field: fields.getFields()) {
                holders.add(new TableField(team, field.getId(), field.getMaxValue()));
            }
            values.add(holders);
            submits.add(new TableSubmit(base,i));
        }
        submits.add(new TableSubmit(base,-1));
        */
    }


    public int getWidth() {
        return width;
    }

    @NonNull
    @Override
    public ContestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ContestViewHolder viewHolder;
        switch (viewType){
            case TYPE_HEADER:
                TableHeaderBinding header = DataBindingUtil.inflate(inflater, R.layout.table_header,parent,false);
                viewHolder = new ContestViewHolder(header);
                break;
            case TYPE_INFO:
                TableInfoBinding info = DataBindingUtil.inflate(inflater, R.layout.table_info,parent,false);
                viewHolder = new ContestViewHolder(info);
                break;
            case TYPE_FIELD:
                TableFieldBinding field = DataBindingUtil.inflate(inflater,R.layout.table_field,parent,false);
                viewHolder = new ContestViewHolder(field);
                break;
            case TYPE_SUBMIT:
                TableSubmitBinding submit = DataBindingUtil.inflate(inflater,R.layout.table_submit,parent,false);
                viewHolder = new ContestViewHolder(submit);
                break;
                default:
                    viewHolder = new ContestViewHolder(parent.getContext());
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContestViewHolder holder, int position) {
        int row = position/width - 1;
        switch (getItemViewType(position)){
            case TYPE_HEADER:
                holder.bind(new TableText(headers.get(position)));
                break;
            case TYPE_INFO:
                holder.bind(new TableText(info.get(row).get(position%width)));
                break;
            case TYPE_FIELD:
                holder.bind(values.get(row).get(position%width - (infoPart + 1)));
                break;
            case TYPE_SUBMIT:
                if(row>=0){
                    holder.bind(submits.get(row));
                }else {
                    holder.bind(submits.get(submits.size()-1));
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return width * (rows + 1);
    }

    @Override
    public int getItemViewType(int position) {
        if(position % width == width-1){
            return TYPE_SUBMIT;
        }
        if(position<width){
            return TYPE_HEADER;
        }
        if(position%width < infoPart+1){
            return TYPE_INFO;
        }
        return TYPE_FIELD;
    }
    public List<TableField> getValues(int pos){
        return values.get(pos);
    }
    public int getValuesCount(){
        return values.size();
    }
    private String getInfo(List<Team> list,int id,int team){
        for(Team info : list){
            if(info.getHeaderId() == id && info.getTeamId() == team){
                return info.getValue();
            }
        }
        return "?";
    }
}
