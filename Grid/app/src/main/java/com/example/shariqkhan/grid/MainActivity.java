package com.example.shariqkhan.grid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StaggeredGridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<Book> bookList = getListItemData();

        BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(this, bookList);
        recyclerView.setAdapter(adapter);
    }

    private List<Book> getListItemData(){

        List<Book> listViewItems = new ArrayList<>();
        for(int i =0; i<50; i++)
        listViewItems.add(new Book(R.drawable.circularimage, "George Orwell"));

        return listViewItems;
    }
}