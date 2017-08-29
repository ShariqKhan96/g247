//package com.example.shariqkhan.gawadar247.DaniyalClasses;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.BottomNavigationView;
//import android.support.design.widget.TabLayout;
//import android.support.v4.view.ViewPager;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.MenuItem;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TabHost;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//
///**
// * Created by zameer on 05/08/2017.
// */
//public class Recyclerlist extends AppCompatActivity {
//    RecyclerView recyclerView;
//    DrawerLayout drawerLayout;
//    Toolbar toolbar;
//    BottomNavigationView bottomNavigationView;
//TabLayout tabLayout;
//    ViewPager viewPager;
//    ListView listView;
//    android.support.v7.app.ActionBarDrawerToggle drawerToggle;
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.listandnavigator);
//
//  //      recyclerView=(RecyclerView)findViewById(R.id.recycler);
//        toolbar=(Toolbar)findViewById(R.id.toolbar);
//        listView=(ListView)findViewById(R.id.left_drawer);
//tabLayout=(TabLayout)findViewById(R.id.tablayout);
//bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomnavigationview);
//BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
//        viewPager=(ViewPager)findViewById(R.id.viewpager);
//        String []array=new String[]{"Home","Contact us","Setting","Profile"};
//        ArrayAdapter arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,array);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("HOME");
//
//
//        listView.setAdapter(arrayAdapter);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
//        drawerLayout.setDrawerListener(drawerToggle);
//        drawerToggle=new android.support.v7.app.ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
//        drawerToggle.syncState();
//        setupViewPager(viewPager);
//        tabLayout.setupWithViewPager(viewPager);
////        tablayout1.addTab(tablayout1.newTab().setText("Tab 1"));
//  //      tablayout1.addTab(tablayout1.newTab().setText("Tab 2"));
//    //    tablayout1.addTab(tablayout1.newTab().setText("Tab 3"));
//
////        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
//    //    recyclerView.setLayoutManager(layoutManager);
//
//       /* TabHost.TabSpec tabSpec= tabHost.newTabSpec("Sales");
//        tabSpec.setIndicator("Sales");
//        tabSpec.setContent(R.id.recycler);
//
//        tabHost.addTab(tabSpec);
//        tabSpec=tabHost.newTabSpec("Wanted");
//        tabSpec.setIndicator("Wanted");
//        tabSpec.setContent(R.id.recycler);
//        tabHost.addTab(tabSpec);
//*/
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//@NotNull
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.my_property:
//                        Intent intent = new Intent(Recyclerlist.this, Myproperty.class);
//                        startActivity(intent);
//                        break;
//                    case R.id.notificaiton:
//                        Intent intent1=new Intent(Recyclerlist.this,GridActivity.class);
//                        startActivity(intent1);
//                }
//                return true;
//            }
//        });
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(drawerToggle.onOptionsItemSelected(item)){
//            return  true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onPostCreate(savedInstanceState, persistentState);
//        drawerToggle.syncState();
//    }
//    private void setupViewPager(ViewPager viewPager) {
//        Viewpageradapter adapter = new Viewpageradapter(getSupportFragmentManager());
//        adapter.addFragment(new Sales(), "Sales");
//        adapter.addFragment(new Wanted(), "Wanted");
//        viewPager.setAdapter(adapter);
//    }
//}
