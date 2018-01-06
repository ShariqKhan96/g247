package com.example.shariqkhan.gawadar247;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v13.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.shariqkhan.gawadar247.Utils.NavViewHelper;
import com.example.shariqkhan.gawadar247.getdata.getHttpData;
import com.example.shariqkhan.gawadar247.models.LoginModel;
import com.example.shariqkhan.gawadar247.models.newsFlashModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonObject;
import com.irozon.sneaker.Sneaker;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    public static LatLng arrayOfPins[];
    public static String titles[];
    public static String Ids[];
    private GoogleMap mMap;
    private Toolbar toolbar;
    private String gettoken;
    SharedPreferences prefs;
    private String getuserid;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SectionPagerAdapter sectionPagerAdapter;
    TextView textViwe;
    TextView txtNewsFeed;
    ImageView drawerImage, searchImage;
    static MainActivity ct = null;
    ImageView imageView;
    ProgressDialog progressDialog;
    String contactNo;
    public static int activityCheck = 0;
    public String LOGOUT_URL = "http://www.gwadar247.pk/api/logout.php";
    public static String BASE_URL = "http://www.gwadar247.pk/api/gwadarmappins.php?";

    private static int Act_Num = 0;
    private BottomNavigationViewEx bottomNavigationViewEx;


    public static void giveInstance() {

        Intent intent = new Intent(ct, MainActivity.class);
        ct.finish();
        Log.e("insideInstance", "");
        ct.startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ct = this;
        if (SearchActivity.reference != null)
            SearchActivity.reference.finish();


        prefs = getSharedPreferences("SharedPreferences", MODE_PRIVATE);

        getuserid = prefs.getString("id", null);
        gettoken = prefs.getString("token", null);

        MapTsk task = new MapTsk();
        task.execute();
//overridePendingTransition();


        SearchReultActivity.searchReultActivity = null;
        overridePendingTransition(0, 0);
        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_nav_bar);
        NavViewHelper.modifyBottomNavBarEx(bottomNavigationViewEx);
        NavViewHelper.enableNavigation(MainActivity.this, bottomNavigationViewEx);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(Act_Num);
        menuItem.setChecked(true);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GridActivity.class);
                startActivity(intent);
            }
        });
        txtNewsFeed = (TextView) findViewById(R.id.toolbarText);
        txtNewsFeed.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);

        searchImage = (ImageView) findViewById(R.id.searchimage);
        drawerImage = (ImageView) findViewById(R.id.drawerimage);
        drawerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int getItem = item.getItemId();

                if (getItem == R.id.drawer_home) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);

                } else if (getItem == R.id.drawer_fav) {
                    Intent intent = new Intent(MainActivity.this, Favourite.class);
                    startActivity(intent);

                } else if (getItem == R.id.drawer_notify) {
                    Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                    startActivity(intent);

                } else if (getItem == R.id.drawer_add) {
                    Intent intent = new Intent(MainActivity.this, GridActivity.class);
                    startActivity(intent);

                } else if (getItem == R.id.drawer_my_prop) {
                    Intent intent = new Intent(MainActivity.this, PropertyActivity.class);
                    startActivity(intent);

                } else if (getItem == R.id.drawer_needs) {
                    Intent intent = new Intent(MainActivity.this, NeedsActivity.class);
                    startActivity(intent);

                } else if (getItem == R.id.map_master) {

//                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
//                    startActivity(intent);

                } else if (getItem == R.id.map_gawadar) {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);

                } else if (getItem == R.id.map_district) {
                    Intent intent = new Intent(MainActivity.this, DistrictMap.class);
                    startActivity(intent);

                } else if (getItem == R.id.drawer_news) {
                    Intent intent = new Intent(MainActivity.this, NewsFeedActivity.class);
                    startActivity(intent);

                } else if (getItem == R.id.drawer_terms) {
                    Intent intent = new Intent(MainActivity.this, TermsAndCondtitionActivity.class);
                    startActivity(intent);

                } else if (getItem == R.id.drawer_requests) {
                    Intent intent = new Intent(MainActivity.this, RequestsActivity.class);
                    startActivity(intent);

                } else if (getItem == R.id.drawer_setting) {
                    Toast.makeText(MainActivity.this, "Settings To Be Instructed", Toast.LENGTH_SHORT).show();
                }

                if (getItem == R.id.drawer_contacts) {
                    ContactMeAsync async = new ContactMeAsync();
                    async.execute();


                } else if (getItem == R.id.drawer_logout) {


                    Task task = new Task();
                    task.execute();


                }

                return true;
            }
        });


        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

                searchImage.startAnimation(animFadein);
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });
        tabLayout = (TabLayout) findViewById(R.id.main_tab);
        textViwe = (TextView) findViewById(R.id.chat_person_name);
        imageView = (ImageView) findViewById(R.id.ic_navbar);


        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(sectionPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private class Task extends AsyncTask<Object, Object, String> {

        @Override
        protected String doInBackground(Object... voids) {
            LoginModel loginModel = new LoginModel();

            SharedPreferences prefs = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
            String getid = prefs.getString("id", null);
            String gettoken = prefs.getString("token", null);
            Log.e("logout", getid);
            Log.e("logout", gettoken);

            String url = LOGOUT_URL + "?userid=" + getid + "&" + "token=" + gettoken;
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


                    Sneaker.with(MainActivity.this)
                            .setTitle("Success")
                            .setDuration(4000)
                            .setMessage("Logout Successful")
                            .sneakSuccess();

                    SharedPreferences preferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.clear();
                    edit.commit();
                    if (Favourite.favRef != null) Favourite.favRef.finish();
                    if (NotificationActivity.notifyRef != null)
                        NotificationActivity.notifyRef.finish();


                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


                } else if (getCode.equals("802")) {
                    SharedPreferences preferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.clear();
                    edit.commit();
                    Sneaker.with(MainActivity.this)
                            .setTitle("Error!")
                            .setDuration(5000)
                            .setMessage("Re Login")
                            .sneakError();

                    progressDialog.dismiss();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Sneaker.with(MainActivity.this)
                            .setTitle("Error!")
                            .setDuration(5000)
                            .setMessage("Something Went Wrong! Please Relogin.")
                            .sneakWarning();


                    progressDialog.dismiss();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Logging Out");
            progressDialog.setMessage("Please Wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

    }


    private class ContactMeAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {
            String url = "http:www.gwadar247.pk/api/getcontact.php";


            Log.e("url", url);

            String response = getHttpData.getData(url);

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("contactResponse", s);
            try {
                JSONObject jsonObject = new JSONObject(s);

                contactNo = jsonObject.getString("Phone");
                callPhoneNumber();


                progressDialog.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Calling");
            progressDialog.setMessage("Please Wait");

            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

        }


    }

    public void callPhoneNumber() {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contactNo));
                startActivity(callIntent);

            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contactNo));
                startActivity(callIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhoneNumber();
            } else {
                Log.e("NotGranted", "Permission not Granted");
            }
        }
    }


    private class MapTsk extends AsyncTask<String, Void, String> {
        String stream = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Loading");

            progressDialog.setMessage("Please Wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String[] params) {

            String getResponse = getJson();
            stream = getResponse;

            return stream;

        }

        private String getJson() {
            HttpClient httpClient = new DefaultHttpClient();
            String urltoSend = BASE_URL + "userid=" + getuserid + "&" + "token=" + gettoken;

            HttpPost post = new HttpPost(urltoSend);

            StringBuilder buffer = new StringBuilder();

            try {
                // Log.e("Insidegetjson", "insidetry");
                HttpResponse response = httpClient.execute(post);

                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String Line = "";

                while ((Line = reader.readLine()) != null) {
                    Log.e("buffer", buffer.toString());
                    buffer.append(Line);

                }
                reader.close();

            } catch (Exception o) {
                Log.e("Error", o.getMessage());

            }
            return buffer.toString();
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONObject jsonobj;
            Log.e("Res", s);
            if (s != null) {
                try {
                    jsonobj = new JSONObject(s);


                    String errorCode = jsonobj.getString("ErrorCode");

                    if (errorCode.equals("000")) {

                        JSONArray jsonArray = jsonobj.getJSONArray("Pins");
                        arrayOfPins = new LatLng[jsonArray.length()];
                        titles = new String[jsonArray.length()];
                        Ids = new String[jsonArray.length()];

                        for (int i = 0; i < jsonArray.length(); i++) {
                            newsFlashModel model = new newsFlashModel();
                            JSONObject js = jsonArray.getJSONObject(i);

                            model.id = js.getString("ID");
                            model.date = js.getString("Name");
                            model.image = js.getString("Latitude");
                            model.thumb = js.getString("Longitude");
                            Log.e("Latitude", model.image + " Longitude " + model.thumb);
                            double v = Double.valueOf(model.image);
                            double v1 = Double.valueOf(model.thumb);
                            arrayOfPins[i] = new LatLng(v, v1);
                            titles[i] = model.id;
                            Ids[i] = model.date;


                        }

                        progressDialog.dismiss();


                    }


                } catch (JSONException e) {
                    Sneaker.with(MainActivity.this)
                            .setTitle("Error!")
                            .setDuration(5000)
                            .setMessage("Something went wrong!")
                            .sneakError();
                    progressDialog.dismiss();
                }


            }

            progressDialog.dismiss();
        }
    }


}
