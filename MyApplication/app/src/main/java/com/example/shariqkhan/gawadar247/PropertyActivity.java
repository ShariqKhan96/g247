package com.example.shariqkhan.gawadar247;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.shariqkhan.gawadar247.DaniyalClasses.Data;
import com.example.shariqkhan.gawadar247.DaniyalClasses.Recycleradapter;
import com.example.shariqkhan.gawadar247.Utils.NavViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class PropertyActivity extends AppCompatActivity {
    private static int Act_Num = 2;
    private BottomNavigationViewEx bottomNavigationViewEx;

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    TextView textView;
    TextView toolbarText;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(toolbar);
        toolbarText = (TextView)toolbar.findViewById(R.id.toolbarText);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(R.layout.custom_bar_property, null);
//
//        ActionBar actionBar = getSupportActionBar();


        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PropertyActivity.this, GridActivity.class);
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.property_recycler);
        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_nav_bar);
        NavViewHelper.modifyBottomNavBarEx(bottomNavigationViewEx);
        NavViewHelper.enableNavigation(this, bottomNavigationViewEx);

        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(Act_Num);
        menuItem.setChecked(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        ArrayList<Data> arrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            Data data = new Data("5000 sq ft", "200000", "Residential", "this is a very large text,very large text, a text whixh would be in 2 lines", "64527");
            arrayList.add(data);
        }


        adapter = new Recycleradapter(arrayList);

        recyclerView.setAdapter(adapter);

    }
}
