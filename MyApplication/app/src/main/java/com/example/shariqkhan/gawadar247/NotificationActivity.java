package com.example.shariqkhan.gawadar247;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shariqkhan.gawadar247.Utils.NavViewHelper;
import com.example.shariqkhan.gawadar247.getdata.getHttpData;
import com.example.shariqkhan.gawadar247.models.FavouriteModel;
import com.example.shariqkhan.gawadar247.models.NotificationModel;
import com.irozon.sneaker.Sneaker;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class NotificationActivity extends AppCompatActivity {

    int width;
    int height;
    Toolbar toolbar;
    TextView textView;
    RecyclerView notifylistview;
    RecyclerView.LayoutManager layoutmangaer;
    SharedPreferences prefs;
    String getid;
    static NotificationActivity notifyRef = null;
    String gettoken;

    RecyclerView.Adapter adapter;
    ArrayList<NotificationModel> arrayList = new ArrayList<>();
        ArrayList<FavouriteModel> arrayList2 = new ArrayList<>();
    public static int Act_Num = 4;
    BottomNavigationViewEx bottomNavigationViewEx;
    private ProgressDialog progressDialog;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        notifyRef = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        notifyRef = this;
        overridePendingTransition(0, 0);
        textView = (TextView) toolbar.findViewById(R.id.tollbarText);
        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_nav_bar);
        NavViewHelper.modifyBottomNavBarEx(bottomNavigationViewEx);
        NavViewHelper.enableNavigation(this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(Act_Num);
        menuItem.setChecked(true);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        notifylistview = (RecyclerView) findViewById(R.id.notify_listview);


        prefs = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        getid = prefs.getString("id", null);

        gettoken = prefs.getString("token", null);


        layoutmangaer = new LinearLayoutManager(this);
        notifylistview.setLayoutManager(layoutmangaer);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        notifylistview.addItemDecoration(itemDecoration);

        adapter = new notifyAdapterClass(arrayList,arrayList2, this);
        notifylistview.setAdapter(adapter);


        Task task = new Task();
        task.execute();
    }


    private class Task extends AsyncTask<Object, Object, String> {

        @Override
        protected String doInBackground(Object... voids) {

            String url = "http://www.gwadar247.pk/api/notifications.php?" + "userid=" + getid + "&" + "token=" + gettoken;

            Log.e("url", url);

            String response = getHttpData.getData(url);

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String getCode = "";
            Log.e("NotificationResult", s);
            try {

                JSONObject jsonObject = new JSONObject(s);

                getCode = jsonObject.getString("ErrorCode");

                if (getCode.equals("000")) {

                    JSONArray jsonArray = jsonObject.getJSONArray("Notifications");

                    //                  Log.e("Properties",String.valueOf(jsonObject.getJSONArray("chuspa")));

                    for (int i = 0; i < jsonArray.length(); i++) {

                        NotificationModel notificationModel = new NotificationModel();
                        JSONObject js = jsonArray.getJSONObject(i);

                        notificationModel.NotifyTime = js.getString("NotificationTime");
                        notificationModel.Id = js.getString("NotificationID");
                        notificationModel.NotificationType = js.getString("NotificationType");
                        notificationModel.IsSeen = js.getString("IsSeen");
                        notificationModel.Response = js.getString("Response");
                        notificationModel.RequestID = js.getString("RequestID");
                        notificationModel.PropertID = js.getString("PropertyID");
                        notificationModel.PropertyType = js.getString("PropertyType");
                        notificationModel.PlotNo = js.getString("PlotNo");
                        notificationModel.Type = js.getString("Type");
                        notificationModel.IsResponded = js.getString("IsResponded");
                        notificationModel.Message = js.getString("Message");

                        JSONObject obj = js.getJSONObject("Property");

                        FavouriteModel model = new FavouriteModel();
                        model.Area = obj.getString("Area");
                        model.PlotType = obj.getString("PlotType");
                        model.PlotNo = obj.getString("PlotNo");
                        model.Price = obj.getString("Price");
                        model.Layer1Name = obj.getString("Layer1Name");
                        model.Layer2Name = obj.getString("Layer2Name");
                        model.SubSchemeName = obj.getString("SubSchemeName");
                        model.Image = obj.getString("Image");




                        arrayList.add(notificationModel);
                        arrayList2.add(model);


                    }
                    adapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                } else if (getCode.equals("802")) {


                    SharedPreferences.Editor edit = prefs.edit();
                    edit.clear();
                    edit.commit();
                    Task2 task2 = new Task2();
                    task2.execute();


                } else {
                    Sneaker.with(NotificationActivity.this)
                            .setTitle("Error!!")
                            .setDuration(5000)
                            .setMessage("This is the error message")
                            .sneakError();
                    progressDialog.dismiss();
                }

            } catch (JSONException e) {
                Log.e("NotifyError", e.getMessage());
                Log.e("ErrorCode", getCode);
                progressDialog.dismiss();
            }
            progressDialog.dismiss();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(NotificationActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Loading Notifications");
            progressDialog.setMessage("Please Wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

    }


    private class Task2 extends AsyncTask<Object, Object, String> {

        @Override
        protected String doInBackground(Object... voids) {


            String url = "http://www.gwadar247.pk/api/logout.php" + "?userid=" + getid + "&" + "token=" + gettoken;
            //  Log.e("logout", url);


            String response = getHttpData.getData(url);

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String getCode = jsonObject.getString("ErrorCode");
                if (getCode.equals("000")) {

                    Toast.makeText(NotificationActivity.this, "Re Login!", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.clear();
                    edit.commit();
                    Intent intent = new Intent(NotificationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    progressDialog.dismiss();
                } else if (getCode.equals("802")) {

                    SharedPreferences.Editor edit = prefs.edit();
                    edit.clear();
                    edit.commit();
                    Toast.makeText(NotificationActivity.this, "Login Again", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(NotificationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Sneaker.with(NotificationActivity.this)
                            .setTitle("Error!!")
                            .setDuration(4000)
                            .setMessage("Error Logging Out")
                            .sneakError();
                    progressDialog.dismiss();
                    Intent intent = new Intent(NotificationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    finish();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(NotificationActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Logging Out");
            progressDialog.setMessage("Please Wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
