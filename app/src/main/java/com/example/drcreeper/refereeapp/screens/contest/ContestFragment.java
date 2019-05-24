package com.example.drcreeper.refereeapp.screens.contest;


import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.drcreeper.refereeapp.R;

public class ContestFragment extends Fragment {


    public ContestFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contest, container, false);
        GridView g = (GridView)v.findViewById(R.id.gv);
        g.setNumColumns(5);
        g.setAdapter(new ListAdapter() {
            String[] arr = {
                "lol","kek","cheburek","lol","kek","chsddsak","lossssdsdadsl","keasdasdk","chasdasdeburek","lol","kek","cheburek",
                    "lol","kek","cheburek",
                    "lol","kek","cheburek","lol","keasdsdsdsk","chebasdsadurek","lol","kek","cheburek"
            };
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int position) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getCount() {
                return arr.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView label;
                if (convertView == null) {
                    convertView = new TextView(getContext());
                    label = (TextView) convertView;
                }else{
                    label = (TextView)convertView;
                }
                label.setText(arr[position]);
                return (convertView);
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
        return v;
    }

}
