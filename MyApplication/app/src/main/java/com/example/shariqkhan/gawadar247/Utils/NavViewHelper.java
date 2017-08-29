package com.example.shariqkhan.gawadar247.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.shariqkhan.gawadar247.MainActivity;
import com.example.shariqkhan.gawadar247.MapActivity;
import com.example.shariqkhan.gawadar247.NotificationActivity;
import com.example.shariqkhan.gawadar247.PropertyActivity;
import com.example.shariqkhan.gawadar247.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * Created by ShariqKhan on 8/18/2017.
 */

public class NavViewHelper {
    public static void modifyBottomNavBarEx(BottomNavigationViewEx bottomNavigationViewEx)
    {
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);

    }
public static void enableNavigation(final Context context, BottomNavigationViewEx bottomNavigationViewEx)
{
    bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId()){

                case R.id.home:
                    Intent intent1 = new Intent(context, MainActivity.class);
                    context.startActivity(intent1);
                    break;

                case R.id.map:
                    Intent intent2 = new Intent(context, MapActivity.class);
                    context.startActivity(intent2);

                    break;

                case R.id.my_property:
                    Intent intent5 = new Intent(context, PropertyActivity.class);
                    context.startActivity(intent5);
                    break;

                case R.id.notificaiton:
                    Intent intent4 = new Intent(context, NotificationActivity.class);
                    context.startActivity(intent4);
                    break;
            }
            return false;
        }
    });
}

}
