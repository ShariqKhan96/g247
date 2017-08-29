package com.example.shariqkhan.gawadar247;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class Main2Activity extends AppCompatActivity {
    TextView price, scheme, size, subscheme, area, phase;
    Button sendinquiry, addtocart;
    CircleImageView circleImageView;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.for_notification_layout);
        price = (TextView) findViewById(R.id.amount);
        scheme = (TextView) findViewById(R.id.scheme);
        size = (TextView) findViewById(R.id.size);
        subscheme = (TextView) findViewById(R.id.subscheme);
        area = (TextView) findViewById(R.id.area);
        sendinquiry = (Button) findViewById(R.id.sendenquiry);
        addtocart = (Button) findViewById(R.id.add_to_cart);
        phase = (TextView) findViewById(R.id.phase);
        circleImageView = (CircleImageView) findViewById(R.id.profile_image);
        ratingBar = (RatingBar) findViewById(R.id.rating);

    }
}
