package com.example.shariqkhan.gawadar247;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.shariqkhan.gawadar247.getdata.getHttpData;
import com.example.shariqkhan.gawadar247.models.SchemeListModel;
import com.example.shariqkhan.gawadar247.models.SubScemeListModel;
import com.irozon.sneaker.Sneaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubSchemeActivity extends AppCompatActivity {
    Toolbar toolbar;

    public static SubSchemeActivity subSchemeActivity;
    private StaggeredGridLayoutManager layoutManager;
    public SharedPreferences prefs;
    public String getid;
    public String gettoken;
    public static String getscheme;
  static  String checkFormSecondForm = "";
    public String SUBSCHEMEURL = "http://www.gwadar247.pk/api/subschemes.php";
    ProgressDialog progressDialog;

    RecyclerView recyclerView;
    public List<SubScemeListModel> listViewItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_scheme);

        getscheme = getIntent().getStringExtra("schemekey");
        checkFormSecondForm = getIntent().getStringExtra("secondform");


        FormActivity.formActivity = null;

        if (FormActivity.formActivity == null)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);


        toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(toolbar);
        subSchemeActivity = this;

        prefs = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        getid = prefs.getString("id", null);
        gettoken = prefs.getString("token", null);


        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbarText);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);

//        getSupportActionBar().setIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        Task task = new Task();
        task.execute();

    }

    private class Task extends AsyncTask<Object, Object, String> {

        @Override
        protected String doInBackground(Object... voids) {

            String url = SUBSCHEMEURL + "?userid=" + getid + "&" + "token=" + gettoken + "&" + "schemeid=" + getscheme;

            //    Log.e("url", url);

            String response = getHttpData.getData(url);

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("Resofsubscheme", s);
            try {
                JSONObject jsonObject = new JSONObject(s);

                String getCode = jsonObject.getString("ErrorCode");

                if (getCode.equals("000")) {

                    JSONArray jsonArray
                            = jsonObject.getJSONArray("Subschemes");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        SubScemeListModel Subschemelistmodel = new SubScemeListModel();
                        JSONObject js = jsonArray.getJSONObject(i);

                        Subschemelistmodel.ID = js.getString("ID");
                        Subschemelistmodel.Name = js.getString("Name");
                        Subschemelistmodel.Image = js.getString("Image");
                        Subschemelistmodel.Popular = js.getString("Popular");
                        Subschemelistmodel.AreaUnit = js.getString("AreaUnit");
                        Subschemelistmodel.LayerName = js.getString("LayerName");


                        listViewItems.add(Subschemelistmodel);

                    }
                    layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    Adapter2 adapter = new Adapter2(SubSchemeActivity.this, listViewItems);
                    recyclerView.setAdapter(adapter);
                    progressDialog.dismiss();

                } else if (getCode.equals("802")) {
                    Sneaker.with(SubSchemeActivity.this)
                            .setTitle("Erorr!")
                            .setDuration(5000)
                            .setMessage("Login Session Expired!.")
                            .sneakError();
                    progressDialog.dismiss();
                } else {
                    Sneaker.with(SubSchemeActivity.this)
                            .setTitle("Erorr!")
                            .setDuration(5000)
                            .setMessage("Something went wrong!")
                            .sneakWarning();
                    progressDialog.dismiss();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(SubSchemeActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Loading");
            progressDialog.setMessage("Please Wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
