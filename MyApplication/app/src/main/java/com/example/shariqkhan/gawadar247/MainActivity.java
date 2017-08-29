package com.example.shariqkhan.gawadar247;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shariqkhan.gawadar247.Utils.NavViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SectionPagerAdapter sectionPagerAdapter;
    TextView textViwe;
    ImageView imageView;

    private static int Act_Num = 0;
    private BottomNavigationViewEx bottomNavigationViewEx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_nav_bar);
        NavViewHelper.modifyBottomNavBarEx(bottomNavigationViewEx);
        NavViewHelper.enableNavigation(MainActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(Act_Num);
        menuItem.setChecked(true);

        tabLayout = (TabLayout) findViewById(R.id.main_tab);
        textViwe=(TextView)findViewById(R.id.chat_person_name);
        imageView=(ImageView) findViewById(R.id.ic_navbar);


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
       // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_bar, null);

        actionBar.setCustomView(view);

        viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(sectionPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
