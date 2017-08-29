package com.example.shariqkhan.gawadar247;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shariqkhan.gawadar247.DaniyalClasses.Sales;
import com.example.shariqkhan.gawadar247.DaniyalClasses.Wanted;

/**
 * Created by ShariqKhan on 8/28/2017.
 */

public class SectionPagerAdapter extends FragmentPagerAdapter {
    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Wanted wanted = new Wanted();
                return wanted;
            case 1:
                Sales sales = new Sales();
                return sales;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Wanted";
            case 1:
                return "Sales";
            default:
                return null;
        }

    }
}

