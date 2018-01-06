package com.example.shariqkhan.gawadar247;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.shariqkhan.gawadar247.models.LoginModel;
import com.example.shariqkhan.gawadar247.models.SchemeListModel;
import com.irozon.sneaker.Sneaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zameer on 22/08/2017.
 */
public class GridActivity extends AppCompatActivity {
    Toolbar toolbar;

    ImageView imageView;
    public static GridActivity reference;
    private StaggeredGridLayoutManager layoutManager;
    GridActivity context;
    public String SCHEMEURL = "http://www.gwadar247.pk/api/schemes.php";
    ProgressDialog progressDialog;
    public String getid, gettoken;
    SharedPreferences prefs;
    RecyclerView recyclerView;
    public List<SchemeListModel> listViewItems = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_view);

        SubSchemeActivity.subSchemeActivity = null;
        FormActivity.formActivity = null;
        context = this;
        if (SubSchemeActivity.subSchemeActivity == null)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

        prefs = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        getid = prefs.getString("id", null);
        gettoken = prefs.getString("token", null);


        toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(toolbar);
        reference = this;
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

        // getSupportActionBar().setIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);


        Task task = new Task();
        task.execute();


    }


    private class Task extends AsyncTask<Object, Object, String> {

        @Override
        protected String doInBackground(Object... voids) {

            String url = SCHEMEURL + "?userid=" + getid + "&" + "token=" + gettoken;

            Log.e("url", url);

            String response = getHttpData.getData(url);

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("Res", s);
            try {
                JSONObject jsonObject = new JSONObject(s);

                String getCode = jsonObject.getString("ErrorCode");

                if (getCode.equals("000")) {

                    JSONArray jsonArray
                            = jsonObject.getJSONArray("Schemes");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        SchemeListModel schemelistmodel = new SchemeListModel();
                        JSONObject js = jsonArray.getJSONObject(i);

                        schemelistmodel.ID = js.getString("ID");

                        schemelistmodel.Name = js.getString("Name");
                        schemelistmodel.Image = js.getString("Image");
                        schemelistmodel.SecondForm = js.getString("SecondForm");
                        listViewItems.add(schemelistmodel);

                    }
                    layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(GridActivity.this, listViewItems);
                    recyclerView.setAdapter(adapter);
                    progressDialog.dismiss();

                } else if(getCode.equals("802")) {
                    Sneaker.with(GridActivity.this)
                            .setTitle("Error!")
                            .setDuration(4000)
                            .setMessage("Please Relogin.")
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
            progressDialog = new ProgressDialog(GridActivity.this, R.style.MyAlertDialogStyle);
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
