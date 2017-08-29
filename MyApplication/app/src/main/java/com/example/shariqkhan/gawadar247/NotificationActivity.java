package com.example.shariqkhan.gawadar247;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.shariqkhan.gawadar247.Utils.NavViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class NotificationActivity extends AppCompatActivity {

    ListView notifylistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notifylistview = (ListView) findViewById(R.id.notify_listview);
        String array[] = {"This is notification", "This is notification", "This is notification", "This is notification",
                "This is notification", "This is notification", "This is notification"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, array);
        notifylistview.setAdapter(adapter);
        notifylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NotificationActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });


    }
}
