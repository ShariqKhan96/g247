package com.example.shariqkhan.gawadar247.DaniyalClasses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shariqkhan.gawadar247.R;

import java.util.ArrayList;

/**
 * Created by zameer on 15/08/2017.
 */
public class Wanted extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.wanted,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);


        ArrayList<Data> arrayList=new ArrayList<>();

        for (int i=0 ;i<50;i++) {

            Data data = new Data("5000 sq ft", "200000", "Residential","this is a very large text,very large text, a text whixh would be in 2 lines","64527");

            arrayList.add(data);
        }


        adapter=new Recycleradapter(arrayList);

        recyclerView.setAdapter(adapter);
        return view;
    }

}

