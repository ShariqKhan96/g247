package com.example.shariqkhan.grid;

/**
 * Created by ShariqKhan on 8/29/2017.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.BookViewHolder> {

    private List<Book> bookList = new ArrayList<>();
    private Context context;

    public BookRecyclerViewAdapter(Context context, List itemList) {
        this.bookList = itemList;
        this.context = context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, null, false);
        return new BookViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, final int position) {
        holder.image.setImageResource(bookList.get(position).getImage());
        holder.author.setText(bookList.get(position).getAuthor());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You clicked at position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.bookList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView author;
        private View container;

        public BookViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.book_name);
            author = (TextView) itemView.findViewById(R.id.author);
            container = itemView.findViewById(R.id.card_view);
        }
    }
}

