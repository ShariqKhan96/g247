package com.example.shariqkhan.gawadar247;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shariqkhan.gawadar247.Utils.NavViewHelper;
import com.example.shariqkhan.gawadar247.adapters.FavouritePagerAdapter;
import com.example.shariqkhan.gawadar247.getdata.getHttpData;
import com.irozon.sneaker.Sneaker;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PropertyActivity extends AppCompatActivity {
    private static int Act_Num = 3;
    private BottomNavigationViewEx bottomNavigationViewEx;



    TextView textView;

    SharedPreferences prefs;
    String getid;
    String gettoken;
    public static int activityCheck  =0;
    ArrayList<MyPropertyModel> listViewItems = new ArrayList<>();
    Toolbar toolbar;

    TextView toolbarText;

    ViewPager viewPager;
   public static PropertyActivity ct;
    public TabLayout tabLayout;

    public MyPropertyPagerAdapter sectionPagerAdapter;
    ProgressDialog progressDialog;


    private FloatingActionButton floatingActionButton;


    public static void giveInstance() {

        Intent intent = new Intent(ct, PropertyActivity.class);
        ct.finish();
        Log.e("insideInstance", "");
        ct.startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        overridePendingTransition(0,0);
        setSupportActionBar(toolbar);
        ct = this;
        toolbarText = (TextView) toolbar.findViewById(R.id.tollbarText);
        tabLayout = (TabLayout) findViewById(R.id.main_tab);
        toolbarText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);



        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.slide_in_left, 0);
                Intent intent = new Intent(PropertyActivity.this, GridActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_nav_bar);
        NavViewHelper.modifyBottomNavBarEx(bottomNavigationViewEx);
        NavViewHelper.enableNavigation(this, bottomNavigationViewEx);

        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(Act_Num);
        menuItem.setChecked(true);




        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        sectionPagerAdapter = new MyPropertyPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(sectionPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);




    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    finish();
    }


}
