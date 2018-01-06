package com.example.shariqkhan.gawadar247;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shariqkhan.gawadar247.models.SchemeListModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShariqKhan on 8/29/2017.
 */

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.BookViewHolder> {

    private List<SchemeListModel> bookList = new ArrayList<>();
    public GridActivity context;

    public BookRecyclerViewAdapter(GridActivity context, List itemList) {
        this.bookList = itemList;
        this.context = context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, null, false);
        return new BookViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, final int position) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        int forImageWidth = width / 2;
        int forImageHeight = width / 2;


        final SchemeListModel scheme = bookList.get(position);

        String getname = scheme.getName();
        String id = scheme.getID();
        String getimage = scheme.getImage().toString();


        // Log.e("imurl",getimage);
        final String secondform = scheme.getSecondForm();

        holder.author.setText(getname);
        holder.image.getLayoutParams().width = forImageWidth;
        holder.image.getLayoutParams().height = forImageHeight;
        holder.image.requestLayout();
        holder.background.getLayoutParams().width = forImageWidth-12;
        holder.background.getLayoutParams().height = forImageHeight-12;
        holder.background.requestLayout();
        Picasso.with(context).load(getimage).placeholder(R.drawable.circularimage).into(holder.image);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pos = String.valueOf(holder.getAdapterPosition());
              //  Toast.makeText(holder.container.getContext(), pos, Toast.LENGTH_SHORT).show();
              //  Toast.makeText(holder.container.getContext(), holder.author.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, SubSchemeActivity.class);
                intent.putExtra("schemekey", scheme.getID());
                intent.putExtra("secondform",secondform);
                context.overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_left);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.bookList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private ImageView background;
        private TextView author;
        private View container;

        public BookViewHolder(View itemView) {
            super(itemView);
            container = itemView;
            image = (ImageView) itemView.findViewById(R.id.book_name);
            background = (ImageView) itemView.findViewById(R.id.imm);
            author = (TextView) itemView.findViewById(R.id.author);
            container = itemView;

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    int posi = getLayoutPosition();
                    Toast.makeText(container.getContext(), getAdapterPosition(), Toast.LENGTH_LONG).show();
                }
            });
        }


    }
}
