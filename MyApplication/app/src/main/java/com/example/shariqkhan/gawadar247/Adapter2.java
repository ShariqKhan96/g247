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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shariqkhan.gawadar247.models.SubScemeListModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShariqKhan on 8/29/2017.
 */

public class Adapter2 extends RecyclerView.Adapter<Adapter2.viewHolder> {


    private List<SubScemeListModel> list = new ArrayList<>();
    private SubSchemeActivity context;
    String LayerName;

    public Adapter2(SubSchemeActivity context, List itemList) {
        this.list = itemList;
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, null, false);
        return new viewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, final int position) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        int forImageWidth = width / 2;
        int forImageHeight = width / 2;


        final SubScemeListModel subScemeListModel = list.get(position);
        holder.author.setText(subScemeListModel.getName());

        holder.image.getLayoutParams().width = forImageWidth;
        holder.image.getLayoutParams().height = forImageHeight;
        holder.image.requestLayout();

        holder.background.getLayoutParams().width = forImageWidth - 12;
        holder.background.getLayoutParams().height = forImageHeight - 12;
        holder.background.requestLayout();

        Picasso.with(context).load(subScemeListModel.getImage()).placeholder(R.drawable.circularimage).into(holder.image);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SubSchemeActivity.checkFormSecondForm.equals("true"))
                {
                    Intent intent = new Intent(context, FormActivity.class);
                    intent.putExtra("subchemeid", subScemeListModel.getID());
                    intent.putExtra("layername", subScemeListModel.getLayerName());
                    intent.putExtra("schemeid", SubSchemeActivity.getscheme);
                    context.startActivity(intent);

                }else
                    {
                        Intent intent = new Intent(context, Form2.class);
                        intent.putExtra("subchemeid", subScemeListModel.getID());
                        intent.putExtra("schemename", subScemeListModel.getName());
                   //     intent.putExtra("layername", subScemeListModel.getLayerName());
                        intent.putExtra("schemeid", SubSchemeActivity.getscheme);

                        context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        context.startActivity(intent);

                    }

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private ImageView background;
        private TextView author;
        private View container;


        public viewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.book_name);
            background = (ImageView) itemView.findViewById(R.id.imm);
            author = (TextView) itemView.findViewById(R.id.author);

            container = itemView;
        }
    }
}