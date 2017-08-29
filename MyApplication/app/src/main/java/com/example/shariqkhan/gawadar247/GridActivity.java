package com.example.shariqkhan.gawadar247;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zameer on 22/08/2017.
 */
public class GridActivity extends AppCompatActivity {
    Toolbar toolbar;


    private StaggeredGridLayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_view);
        toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbarText);

        // getSupportActionBar().setIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<Book> bookList = getListItemData();

        BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(this, bookList);
        recyclerView.setAdapter(adapter);
    }


    private List<Book> getListItemData() {

        List<Book> listViewItems = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            listViewItems.add(new Book(R.drawable.circularimage, "George Orwell"));

        return listViewItems;
    }
}
