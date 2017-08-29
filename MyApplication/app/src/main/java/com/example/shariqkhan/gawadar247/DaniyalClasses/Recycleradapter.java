package com.example.shariqkhan.gawadar247.DaniyalClasses;

/**
 * Created by zameer on 05/08/2017.
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shariqkhan.gawadar247.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by zameer on 05/08/2017.
 */
public class Recycleradapter extends RecyclerView.Adapter<Recycleradapter.Recyclerholder> {

    private ArrayList<Data> arrayList = new ArrayList<>();

    public Recycleradapter(ArrayList<Data> arrayList) {
        this.arrayList = arrayList;
    }

    public Recyclerholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_notification_layout, parent, false);
        return new Recyclerholder(view);
    }

    @Override
    public void onBindViewHolder(Recyclerholder holder, int position) {
        Data data = arrayList.get(position);

        holder.price.setText("RS :" + "1200000");
        holder.size.setText("Size: " + "size qunatity");
        holder.subscheme.setText("Subscheme : subschemename");
        holder.scheme.setText("Scheme : schemename");
        holder.area.setText("Area :areaname");
        holder.phase.setText("Phase : phasename ");
if (position%2 ==0)
{
    holder.ratingBar.setRating(5);
    holder.relativeLayout.setBackgroundColor(Color.WHITE);
}else{
    holder.relativeLayout.setBackgroundColor(Color.rgb(254, 255, 242));
    holder.ratingBar.setRating(3);
}

    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public static class Recyclerholder extends RecyclerView.ViewHolder {

        public TextView price, scheme, size, subscheme, area, phase;
        public Button sendinquiry, addtocart;
        CircleImageView circleImageView;
        RatingBar ratingBar;
        View view;
        RelativeLayout relativeLayout;

        public Recyclerholder(View itemView) {
            super(itemView);
            view = itemView;
            relativeLayout = (RelativeLayout)view.findViewById(R.id.use_for_color);
            price = (TextView) itemView.findViewById(R.id.amount);
            scheme = (TextView) itemView.findViewById(R.id.scheme);
            size = (TextView) itemView.findViewById(R.id.size);
            subscheme = (TextView) itemView.findViewById(R.id.subscheme);
            area = (TextView) itemView.findViewById(R.id.area);
            sendinquiry = (Button) itemView.findViewById(R.id.sendenquiry);
            addtocart = (Button) itemView.findViewById(R.id.add_to_cart);
            phase = (TextView) itemView.findViewById(R.id.phase);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.profile_image);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rating);


        }
    }
}

