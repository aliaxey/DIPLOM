package com.example.drcreeper.refereeapp.screens.results;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.drcreeper.refereeapp.R;
import com.example.drcreeper.refereeapp.databinding.TableHeaderBinding;
import com.example.drcreeper.refereeapp.databinding.TableInfoBinding;
import com.example.drcreeper.refereeapp.databinding.TableMarkBinding;
import com.example.drcreeper.refereeapp.models.Field;
import com.example.drcreeper.refereeapp.models.Header;
import com.example.drcreeper.refereeapp.models.Info;
import com.example.drcreeper.refereeapp.models.Mark;
import com.example.drcreeper.refereeapp.models.Results;
import com.example.drcreeper.refereeapp.models.Row;
import com.example.drcreeper.refereeapp.screens.contest.TableText;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ResultsRecyclerViewAdapter extends RecyclerView.Adapter<ResultsViewHolder> {
    static final int TYPE_HEADER = 1;
    static final int TYPE_INFO = 2;
    static final int TYPE_MARK = 3;

    private int width;
    private int rows;
    private int infoPart;
    private List<String> headers;
    private List<List<String>> info;
    private List<List<Double>> values;
    private List<Double> total;


    public ResultsRecyclerViewAdapter(Context context,Results results){
        info = new ArrayList<>();
        values = new ArrayList<>();
        total = new ArrayList<>();
        headers = new ArrayList<>();
        headers.add("#");
        width = 3 + results.getHeaders().size() + results.getFields().size();
        rows = results.getRows().size();
        infoPart = results.getHeaders().size() + 1;
        List<Double> places = new ArrayList<>();
        int count =0;

        for (Header header : results.getHeaders()){
            headers.add(header.getName());
        }
        for (Field field : results.getFields()){
            headers.add(field.getName()+"("+field.getFactor()+")");
        }
        headers.add(context.getString(R.string.total));
        headers.add(context.getString(R.string.place));
        //working with row
        for(Row row : results.getRows()){
            List<String> infoRow = new ArrayList<>();
            infoRow.add(String.valueOf(row.getTeam()));
            for(Header header : results.getHeaders()){
                infoRow.add(getInfo(row.getInfo(),header.getId()));
            }
            info.add(infoRow);

            double score = 0;
            List<Double> averages = new ArrayList<>();
            for(Field field : results.getFields()){
                double sum = 0;
                int quantity = 0;
                for(Mark mark : row.getMarks()){
                    if(mark.getFieldId() == field.getId()){
                        sum += mark.getMark();
                        quantity ++;
                    }
                }
                if(quantity != 0){
                    double average = sum/quantity;
                    averages.add(average);
                    score += average * field.getFactor();
                }else {
                    averages.add(sum);
                }
            }
            averages.add(score);
            places.add(score);
            values.add(averages);
        }
        //calculating place
        int place = 1;
        int complete = 0;
        while (complete < values.size()){
            double maxResult = 0;
            int pos = 0;
            for (int j = 0; j< places.size();j++){
                if(places.get(j) > maxResult){
                    maxResult = places.get(j);
                    pos = j;
                }
            }
            places.set(pos,0d);
            values.get(pos).add((double) place);
            complete ++;
            for (int j = 0; j< places.size();j++){
                if(places.get(j) == maxResult){
                    places.set(j,0d);
                    values.get(j).add((double)place);
                    complete ++;
                }
            }
            place ++;
        }
    }


    public int getWidth() {
        return width;
    }

    @NonNull
    @Override
    public ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ResultsViewHolder viewHolder;
        switch (viewType){
            case TYPE_HEADER:
                TableHeaderBinding header = DataBindingUtil.inflate(inflater, R.layout.table_header,parent,false);
                viewHolder = new ResultsViewHolder(header);
                break;
            case TYPE_INFO:
                TableInfoBinding info = DataBindingUtil.inflate(inflater, R.layout.table_info,parent,false);
                viewHolder = new ResultsViewHolder(info);
                break;
            case TYPE_MARK:
                TableMarkBinding mark = DataBindingUtil.inflate(inflater,R.layout.table_mark,parent,false);
                viewHolder = new ResultsViewHolder(mark);
                break;
                default:
                    viewHolder = new ResultsViewHolder(parent.getContext());
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsViewHolder holder, int position) {
        int row = position/width - 1;
        switch (getItemViewType(position)){
            case TYPE_HEADER:
                holder.bind(new TableText(headers.get(position)));
                break;
            case TYPE_INFO:
                holder.bind(new TableText(info.get(row).get(position%width)));
                break;
            case TYPE_MARK:
                double value = values.get(row).get(position%width - infoPart);
                holder.bind(new TableMark(fromNumber(value)));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return width * (rows + 1);
    }

    @Override
    public int getItemViewType(int position) {
        if(position<width){
            return TYPE_HEADER;
        }
        if(position%width < infoPart){
            return TYPE_INFO;
        }
        return TYPE_MARK;
    }
    private String getInfo(List<Info> list,int id){
        for(Info info : list){
            if(info.getHeaderId() == id){
                return info.getValue();
            }
        }
        return "?";
    }
    private String fromNumber(double value){
        if(value == (int) value){
            return String.valueOf((int)value);
        }else {
            BigDecimal number = new BigDecimal(value);
            number.setScale(2, RoundingMode.HALF_UP);
            return number.toString();
        }
    }

}
