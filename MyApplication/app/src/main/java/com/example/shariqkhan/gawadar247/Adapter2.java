package com.example.shariqkhan.gawadar247;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShariqKhan on 8/29/2017.
 */

public class Adapter2 extends RecyclerView.Adapter<Adapter2.viewHolder> {


    private List<Book> bookList = new ArrayList<>();
    private Context context;

    public Adapter2(Context context, List itemList) {
        this.bookList = itemList;
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, null, false);
        return new viewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, final int position) {
        holder.image.setImageResource(bookList.get(position).getImage());
        holder.author.setText(bookList.get(position).getAuthor());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FormActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.bookList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView author;
        private View container;

        public viewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.book_name);
            author = (TextView) itemView.findViewById(R.id.author);
            container = itemView.findViewById(R.id.card_view);
        }
    }
}