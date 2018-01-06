package com.example.shariqkhan.gawadar247.DaniyalClasses;

/**
 * Created by zameer on 05/08/2017.
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shariqkhan.gawadar247.R;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by zameer on 05/08/2017.
 */
public class Recycleradapter extends RecyclerView.Adapter<Recycleradapter.Recyclerholder> {

    private ArrayList<Data> arrayList = new ArrayList<>();
    int checkForText;

    public Recycleradapter(ArrayList<Data> arrayList, int checkForText) {
        this.arrayList = arrayList;
        this.checkForText = checkForText;
    }

    public Recyclerholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_notification_layout, parent, false);
        return new Recyclerholder(view);
    }

    @Override
    public void onBindViewHolder(Recyclerholder holder, int position) {

        Data data = arrayList.get(position);
//        holder.price.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                holder.size.getContext().getResources().getDimension(R.dimen.font_size));
//        // holder.price.setText("RS :" + "1200000");
        float scale = holder.addtocart.getContext().getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (8 * scale + 0.5f);
        holder.addtocart.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);
        holder.size.setText("Size: " + "size qunatity");

//        holder.size.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                holder.size.getContext().getResources().getDimension(R.dimen.font_size));

        holder.subscheme.setText("Subscheme : subscheme");
//        holder.subscheme.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                holder.size.getContext().getResources().getDimension(R.dimen.font_size));

        holder.scheme.setText("Scheme : schemename");
//        holder.scheme.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                holder.size.getContext().getResources().getDimension(R.dimen.font_size));

        holder.area.setText("Area :areaname");
//        holder.area.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                holder.size.getContext().getResources().getDimension(R.dimen.font_size));

        holder.phase.setText("Phase : phasename ");
//        holder.phase.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                holder.size.getContext().getResources().getDimension(R.dimen.font_size));

        holder.phase2.setText("Phase : phasename ");
   /*     holder.phase2.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                holder.size.getContext().getResources().getDimension(R.dimen.font_size));
*/

        if (position % 2 == 0) {

            holder.ratingBar.setRating(5);
            holder.relativeLayout.setBackgroundColor(Color.WHITE);

        } else {

            holder.relativeLayout.setBackgroundColor(Color.rgb(242, 242, 242));
            holder.ratingBar.setRating(3);

        }
        if (checkForText == 3) {
//            float scales = holder.addtocart.getContext().getResources().getDisplayMetrics().density;
//            int dpAsPixelss = (int) (8 * scales + 0.5f);
//            holder.addtocart.setPadding(dpAsPixelss, dpAsPixelss, dpAsPixelss, dpAsPixelss);
//            holder.circleImageView.setPadding(dpAsPixelss, 0, 0, 0);
        }
        if (this.checkForText != 0) {

            holder.sendinquiry.setText("Modify  Property");
            holder.addtocart.setText("Remove Property");
            float scales = holder.addtocart.getContext().getResources().getDisplayMetrics().density;
            int dpAsPixelss = (int) (6.5 * scales + 0.5f);
            holder.addtocart.setPadding(dpAsPixelss, dpAsPixelss, dpAsPixelss, dpAsPixelss);
        }


    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public static class Recyclerholder extends RecyclerView.ViewHolder {

        public TextView price, scheme, size, subscheme, area, phase, phase2;
        public Button sendinquiry, addtocart;
        CircleImageView circleImageView;
        SimpleRatingBar ratingBar;
        View view;
        RelativeLayout relativeLayout;

        public Recyclerholder(View itemView) {
            super(itemView);
            view = itemView;

            relativeLayout = (RelativeLayout) view.findViewById(R.id.use_for_color);
            price = (TextView) itemView.findViewById(R.id.amount);
            phase2 = (TextView) itemView.findViewById(R.id.phase1);
            scheme = (TextView) itemView.findViewById(R.id.scheme);
            size = (TextView) itemView.findViewById(R.id.size);
            subscheme = (TextView) itemView.findViewById(R.id.subscheme);
            area = (TextView) itemView.findViewById(R.id.area);
            sendinquiry = (Button) itemView.findViewById(R.id.sendenquiry);
            addtocart = (Button) itemView.findViewById(R.id.add_to_cart);
            phase = (TextView) itemView.findViewById(R.id.phase);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.profile_image);
            ratingBar = (SimpleRatingBar) itemView.findViewById(R.id.rating);


        }
    }
}

