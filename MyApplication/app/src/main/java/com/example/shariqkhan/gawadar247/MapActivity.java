package com.example.shariqkhan.gawadar247;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.shariqkhan.gawadar247.Utils.NavViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MapActivity extends AppCompatActivity {

    public static int Act_Num= 1;
    BottomNavigationViewEx bottomNavigationViewEx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.bottom_nav_bar);
        NavViewHelper.modifyBottomNavBarEx(bottomNavigationViewEx);
        NavViewHelper.enableNavigation(this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(Act_Num);
        menuItem.setChecked(true);
    }
}
