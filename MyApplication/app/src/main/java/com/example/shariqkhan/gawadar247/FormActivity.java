package com.example.shariqkhan.gawadar247;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shariqkhan.gawadar247.getdata.getHttpData;
import com.example.shariqkhan.gawadar247.models.FormModel;
import com.example.shariqkhan.gawadar247.models.PlotModel;
import com.irozon.sneaker.Sneaker;
import com.transitionseverywhere.TransitionManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {

    public static FormActivity formActivity;
    Button button1, button2;
    public ProgressDialog progressDialog;
    FrameLayout frameLayout1, frameLayout2;
    public String array[] = {};
    public CharSequence NamesOfLayer2[] = {};
    public String plotId[] = {};
    public static String whichLayer = "";
    String layernamearray[] = {};
    public static String PlotType = "";
    public static String PlotTypeDecider = "";
    public static String PlotIdToSend = "";
    public String AreaName;
    public String Id2[] = {};
    public String Id[] = {};
    public String type[] = {};
    public String type2[] = {};
    String AreaOfWantedPlot;
    public String HasLayer[] = {};
    public String TypeOfWantedPlot;
    public static FormActivity form;
    TextView schemeTextView;
    Button submitButton;
    EditText areaEditText;
    EditText priceofframeEditText;
    Toolbar toolbar;
    LinearLayout ll1, ll2, ll3, LL4;
    LinearLayout parentView;
    public String layer1id;
    public static String FORMURL = "http://www.gwadar247.pk/api/layer1.php?";
    public Button SubmitButton2;

    public String Area[] = {};
    public String Area2[] = {};
    TextView textView;
    Button commercial, residential;
    String plotname[] = {};
    EditText schemeEditText;
    String whichType = "Sale";
    EditText StreetEditText;
    public String getid;
    public String getLayerid;
    public String getLayerid2;
    SharedPreferences prefs;
    TextView demoPriceComission;
    TextView demoTotalPrice;
    public String getuserid;
    public String gettoken;
    boolean LayerOneSearch = false;
    boolean Layer2Search = false;
    public int priceToBeSent;
    public String price;
    private CharSequence[] array3 = {};
    String value = "";
    TextView streetTextView;
    EditText TypeEditText;
    TextView TypeTextView;
    String schemeid;
    String llName;
    String ll2Name = "";
    boolean checkForWanted = false;
    TextView propertyTextView;
    EditText plotEditText;

    private void clearFields() {
        plotEditText.setText("");
        TypeEditText.setText("");
        areaEditText.setText("");
        areaEditText.setHint("Select Area");
        schemeEditText.setText("");
        StreetEditText.setText("");
        PlotIdToSend = "";
        PlotType = "";

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        schemeid = getIntent().getStringExtra("schemeid");
        propertyTextView = (TextView) findViewById(R.id.plotTextView);
        getid = getIntent().getStringExtra("subchemeid");
        llName = getIntent().getStringExtra("layername");
        SubmitButton2 = (Button) findViewById(R.id.submitbutton2);
        schemeTextView = (TextView) findViewById(R.id.schemeTextView);
        plotEditText = (EditText) findViewById(R.id.plotEditText);

        schemeTextView.setText(llName);
        streetTextView = (TextView) findViewById(R.id.streetTextView);
        ll3 = (LinearLayout) findViewById(R.id.linearType);
        LL4 = (LinearLayout) findViewById(R.id.linearAddress);
        final TextView areaTextView = (TextView) findViewById(R.id.areaTextView);
        ll3.setVisibility(View.GONE);
        TypeEditText = (EditText) findViewById(R.id.TypeEditText);
        TypeTextView = (TextView) findViewById(R.id.TypeTextView);

        demoPriceComission = (TextView) findViewById(R.id.demoPriceComission);
        demoTotalPrice = (TextView) findViewById(R.id.demoTotalPrice);
        submitButton = (Button) findViewById(R.id.submitbutton);
        priceofframeEditText = (EditText) findViewById(R.id.priceofframeEditText);
        areaEditText = (EditText) findViewById(R.id.areaEditText);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        formActivity = this;
        parentView = (LinearLayout) findViewById(R.id.linearLayoutOfFirst3edittext);
        prefs = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        getuserid = prefs.getString("id", null);
        gettoken = prefs.getString("token", null);
        schemeEditText = (EditText) findViewById(R.id.SchemeEditText);
        StreetEditText = (EditText) findViewById(R.id.StreetEditText);

        Task task = new Task();
        task.execute();

        ll1 = (LinearLayout) findViewById(R.id.ll1);

        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll2.setVisibility(View.GONE);

//        if (llName != null) {
//
//            ll2.setVisibility(View.VISIBLE);
//        } else {
//            ll2.setVisibility(View.GONE);
//        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView) toolbar.findViewById(R.id.tollbarText);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);
        commercial = (Button) findViewById(R.id.commercial);
        residential = (Button) findViewById(R.id.Residential);

        priceofframeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null) {
                    value = charSequence.toString();
                    double com = Double.valueOf(value);
                    double ans = com * 0.01;
                    double dd = com + ans;
                    String con = BigDecimal.valueOf(dd).toPlainString();
//                    String con = String.valueOf(dd);
//                    value = (String) con;

                    demoPriceComission.setText(String.valueOf(ans));
                    demoTotalPrice.setText(con);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        frameLayout2 = (FrameLayout) findViewById(R.id.wantedFrameLayout);

        frameLayout1 = (FrameLayout) findViewById(R.id.saleFrameLayout);

        button1 = (Button) findViewById(R.id.formSalesButton);
        button2 = (Button) findViewById(R.id.formWantedButton);
        button2.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.not_pressed_candr));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
                whichType = "";
                ll3.setVisibility(View.GONE);
                Animation anim = AnimationUtils.loadAnimation(FormActivity.this, R.anim.slide_in_right_fade);
                frameLayout1.startAnimation(anim);
                frameLayout2.setVisibility(View.GONE);

                LL4 = (LinearLayout) findViewById(R.id.linearAddress);
                LL4.setVisibility(View.VISIBLE);
                plotEditText.setHint("Select Plot");
                propertyTextView.setText("Plot");
                areaEditText.setHint("Select Area");
                areaTextView.setText("Area");

                whichType = "Sale";

                button1.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.pressed));
                button1.setTextColor(Color.WHITE);
                button2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                button2.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.not_pressed_candr));
                frameLayout1.setVisibility(View.VISIBLE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
                LL4.setVisibility(View.GONE);
                whichType = "";
                plotEditText.setHint("Select Area");
                propertyTextView.setText("Area");

                areaEditText.setVisibility(View.GONE);
                Animation anim = AnimationUtils.loadAnimation(FormActivity.this, R.anim.slide_in_up);
                frameLayout2.startAnimation(anim);
                whichType = "Wanted";
                frameLayout1.setVisibility(View.GONE);
                button2.setTextColor(Color.WHITE);
                button2.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.pressed));
                button1.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.not_pressed_candr));
                button1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                frameLayout2.setVisibility(View.VISIBLE);
            }
        });

        schemeEditText.setOnTouchListener(new View.OnTouchListener() {
            AlertDialog alertdialog;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);


                    builder.setTitle("Select");
                    builder.setItems(array, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            schemeEditText.setText(array[i]);
                            layer1id = Id[i];
                            getLayerid = Id[i];
                            ll2Name = layernamearray[i];
                            if (ll2Name != null) {
                                ll2.setVisibility(View.VISIBLE);
                                streetTextView.setText(ll2Name);
                            } else {
                                ll2.setVisibility(View.GONE);
                            }
                            if (HasLayer[i].equals("true")) {


                                TransitionManager.beginDelayedTransition(parentView);
                                ll2.setVisibility(View.VISIBLE);
                                dialogInterface.dismiss();
                                LayerOneSearch = true;
                                Layer2Search = false;
                                LayerOneSearch = true;
                                String url = "http://www.gwadar247.pk/api/layer2.php?userid=" + getuserid + "&" + "token=" + gettoken + "&layer1id=" + getLayerid;
                                Layer2AsyncTask task2 = new Layer2AsyncTask();
                                dialogInterface.dismiss();
                                task2.execute(url);


                            } else {
                                if (whichType.equals("Sale")) {
                                    ll2.setVisibility(View.GONE);
                                    String url = "http://www.gwadar247.pk/api/plots.php?userid=" + getuserid + "&" + "token=" + gettoken + "&layer1id=" + getLayerid;

                                    LayerOneSearch = false;
                                    Layer2Search = true;
                                    dialogInterface.dismiss();
                                    PlotAsyncOfLayer task = new PlotAsyncOfLayer();
                                    dialogInterface.dismiss();
                                    task.execute(url);
                                } else {
                                    ll2.setVisibility(View.GONE);

                                    String urlll = "http://www.gwadar247.pk/api/plotareatype.php?userid=" + getuserid + "&" + "token=" + gettoken + "&layer1id=" + getLayerid;
                                    PlotAreaTypeAsync task = new PlotAreaTypeAsync();
                                    dialogInterface.dismiss();

                                    task.execute(urlll);

                                }
                            }

                        }
                    });
                    alertdialog = builder.create();
                    alertdialog.show();

                }

                return true;
            }
        });
// haslayer true
        StreetEditText.setOnTouchListener(new View.OnTouchListener() {
            AlertDialog alertdialog;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {


                    final AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);


                    builder.setTitle("Select");
                    builder.setItems(NamesOfLayer2, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            CharSequence name = NamesOfLayer2[i];
                            StreetEditText.setText(name);

                            getLayerid = Id2[i];
                            getLayerid2 = Id2[i];
                            if (whichType.equals("Sale")) {
                                String url = "http://www.gwadar247.pk/api/plots.php?userid=" + getuserid + "&" + "token=" + gettoken + "&layer2id=" + getLayerid;
                                //   StreetEditText.setText(NamesOfLayer2[i]);
                                PlotAsyncOfLayer plotAsyncOfLayer1 = new PlotAsyncOfLayer();
                                dialogInterface.dismiss();
                                plotAsyncOfLayer1.execute(url);

                            } else if (whichType.equals("Wanted")) {
                                Log.e("insideWanted", "thak");

                                String url = "http://www.gwadar247.pk/api/plotareatype.php?userid=" + getuserid + "&" + "token=" + gettoken + "&layer2id=" + getLayerid;
                                StreetEditText.setText(NamesOfLayer2[i]);
                                Log.e("beforeCalling", "thak");
                                PlotAreaTypeAsync plotAreaTypeAsync = new PlotAreaTypeAsync();
                                dialogInterface.dismiss();
                                plotAreaTypeAsync.execute(url);
                                Log.e("afterCalling", "thak");

                            }

                        }
                    });
                    alertdialog = builder.create();
                    alertdialog.show();

                    Layer2Search = true;
                    LayerOneSearch = true;


                }
                return true;
            }
        });

        plotEditText.setOnTouchListener(new View.OnTouchListener() {
            AlertDialog alertdialog;

            @Override
            public boolean onTouch(final View view, MotionEvent motionEvent) {

                if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {


                    final AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);

                    builder.setTitle("Select");

                    if (whichType.equals("Sale")) {


                        builder.setItems(plotname, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                areaEditText.setText(Area[i]);
                                plotEditText.setText(plotname[i]);
                                AreaName = Area[i];
                                areaEditText.setText(AreaName);
                                PlotIdToSend = plotId[i];
                                PlotTypeDecider = type[i];

                                if (PlotTypeDecider.equals("Commercial")) {

                                    PlotType = "";
                                    commercial.setTextColor(Color.WHITE);
                                    commercial.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.round_pressed));
                                    commercial.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_check, 0, 0, 0);
                                    PlotType = "Commercial";
                                    residential.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                    residential.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.not_pressed));
                                    residential.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    commercial.setEnabled(false);
                                    residential.setEnabled(false);

                                } else if (PlotTypeDecider.equals("Residential")) {

                                    PlotType = "";
                                    PlotType = "Residential";
                                    residential.setTextColor(Color.WHITE);
                                    residential.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.round_pressed));
                                    residential.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_check, 0, 0, 0);
                                    commercial.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.not_pressed));
                                    commercial.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                    commercial.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    commercial.setEnabled(false);
                                    residential.setEnabled(false);

                                } else {
                                    PlotType = "";
                                    commercial.setTextColor(Color.WHITE);
                                    commercial.setText(PlotTypeDecider);
                                    commercial.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.round_pressed));
                                    commercial.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_check, 0, 0, 0);
                                    PlotType = "Commercial";
                                    residential.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                    residential.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.not_pressed));
                                    residential.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    commercial.setEnabled(false);
                                    residential.setEnabled(false);
                                    residential.setVisibility(View.GONE);


                                }

                            }
                        });
                    } else if (whichType.equals("Wanted")) {
                        builder.setItems(Area2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AreaOfWantedPlot = Area2[i];
                                plotEditText.setText(AreaOfWantedPlot);

                                ll3.setVisibility(View.VISIBLE);
                                checkForWanted = true;

                            }
                        });
                    }

                    alertdialog = builder.create();
                    alertdialog.show();


                }
                return true;
            }
        });

        TypeEditText.setOnTouchListener(new View.OnTouchListener() {
            AlertDialog alertDialog;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
                    builder.setTitle("Select");
                    builder.setItems(type2, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            TypeOfWantedPlot = type2[i];
                            TypeEditText.setText(TypeOfWantedPlot);

                            if (TypeOfWantedPlot.equals("Commercial")) {

                                PlotType = "";
                                commercial.setTextColor(Color.WHITE);
                                commercial.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.round_pressed));
                                commercial.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_check, 0, 0, 0);
                                PlotType = "Commercial";
                                residential.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                residential.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.not_pressed));
                                residential.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                commercial.setEnabled(false);
                                residential.setEnabled(false);

                            } else if (TypeOfWantedPlot.equals("Residential")) {

                                PlotType = "";
                                PlotType = "Residential";
                                residential.setTextColor(Color.WHITE);
                                residential.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.round_pressed));
                                residential.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_check, 0, 0, 0);
                                commercial.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.not_pressed));
                                commercial.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                commercial.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                commercial.setEnabled(false);
                                residential.setEnabled(false);

                            } else {
                                commercial.setTextColor(Color.WHITE);
                                commercial.setText(TypeOfWantedPlot);
                                commercial.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.round_pressed));
                                commercial.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_check, 0, 0, 0);
                                PlotType = "Commercial";
                                residential.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                residential.setBackground(ContextCompat.getDrawable(FormActivity.this, R.drawable.not_pressed));
                                residential.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                residential.setVisibility(View.GONE);
                                commercial.setEnabled(false);
                                residential.setEnabled(false);

                            }
                            dialogInterface.dismiss();
                        }
                    });

                    alertDialog = builder.create();
                    alertDialog.show();

                }
                return true;
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String BuildUrl = "http://www.gwadar247.pk/api/addproperty.php?" + "userid=" + getuserid + "&" + "token=" + gettoken;
                AddPropertyAsyncTask task = new AddPropertyAsyncTask();
                task.execute(BuildUrl);
            }
        });
        SubmitButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String BuildUrl = "http://www.gwadar247.pk/api/addproperty.php?" + "userid=" + getuserid + "&" + "token=" + gettoken;
                AddPropertyAsyncTask task = new AddPropertyAsyncTask();
                task.execute(BuildUrl);

            }
        });

    }

    //for plot
    private class PlotAsyncOfLayer extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {
            String url = voids[0];

            Log.e("Inisdetask3", "Inisdetask3");

            Log.e("url", url);

            String response = getHttpData.getData(url);

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("PlotResponse", s);
            try {
                JSONObject jsonObject = new JSONObject(s);

                String getCode = jsonObject.getString("ErrorCode");


                if (getCode.equals("000")) {

                    JSONArray jsonArray
                            = jsonObject.getJSONArray("Plots");
                    Area = new String[jsonArray.length()];
                    type = new String[jsonArray.length()];
                    plotId = new String[jsonArray.length()];
                    plotname = new String[jsonArray.length()];


                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.e("JsonArrayLength", String.valueOf(jsonArray.length()));


                        PlotModel Subschemelistmodel = new PlotModel();
                        JSONObject js = jsonArray.getJSONObject(i);

                        Subschemelistmodel.ID = js.getString("ID");
                        //   Toast.makeText(FormActivity.this, js.getString("ID"), Toast.LENGTH_SHORT).show();
                        Subschemelistmodel.NAME = js.getString("Name");

                        Subschemelistmodel.TYPE = js.getString("Type");
                        Subschemelistmodel.AREA = js.getString("Area");

                        Area[i] = Subschemelistmodel.AREA;
                        plotname[i] = Subschemelistmodel.NAME;
                        type[i] = Subschemelistmodel.TYPE;
                        plotId[i] = Subschemelistmodel.ID;


                    }

                    progressDialog.dismiss();

                } else if (getCode.equals("802")) {

                    Toast.makeText(FormActivity.this, "Login Session Expired!", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor edit = prefs.edit();
                    edit.clear();
                    edit.commit();

                    Intent intent = new Intent(FormActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    progressDialog.dismiss();
                }
            } catch (JSONException e) {
                Log.e("PlotsAsyncError", e.getMessage());
                progressDialog.dismiss();
            }


        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FormActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Loading");
            progressDialog.setMessage("Please Wait");

            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }


    }


    private class PlotAreaTypeAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {
            String url = voids[0];

            //      Log.e("Inisdetask3", "Inisdetask3");

            Log.e("url", url);

//            Toast.makeText(FormActivity.this, url, Toast.LENGTH_SHORT).show();
            String response = getHttpData.getData(url);
            Log.e("responseivebaby", response);

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("PlotAreaTypeResponse", s);
            try {
                JSONObject jsonObject = new JSONObject(s);

                String getCode = jsonObject.getString("ErrorCode");
                Log.e("KeyKeyKey", getCode);

                if (getCode.equals("000")) {

                    JSONArray jsonArray
                            = jsonObject.getJSONArray("Areas");
                    JSONArray jsonArray2
                            = jsonObject.getJSONArray("Types");
                    Area2 = new String[jsonArray.length()];


                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.e("JsonArrayLength", String.valueOf(jsonArray.length()));


                        PlotModel Subschemelistmodel = new PlotModel();
                        JSONObject js = jsonArray.getJSONObject(i);

                        Subschemelistmodel.AREA = js.getString("Area");

                        Area2[i] = Subschemelistmodel.AREA;
                    }

                    type2 = new String[jsonArray2.length()];
                    Log.e("type2length", String.valueOf(type2.length));

                    for (int i = 0; i < jsonArray2.length(); i++) {
                        Log.e("JsonArrayLength", String.valueOf(jsonArray.length()));


                        PlotModel Subschemelistmodel = new PlotModel();
                        JSONObject js = jsonArray2.getJSONObject(i);

                        Subschemelistmodel.TYPE = js.getString("Type");

                        type2[i] = Subschemelistmodel.TYPE;
                    }


                    progressDialog.dismiss();

                } else if (getCode.equals("802")) {

                    Toast.makeText(FormActivity.this, "Login Session Expired!", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor edit = prefs.edit();
                    edit.clear();
                    edit.commit();

                    Intent intent = new Intent(FormActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    progressDialog.dismiss();
                }
            } catch (JSONException e) {
                Log.e("PlotsAreaAsyncError", e.getMessage());
                progressDialog.dismiss();
            }


        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FormActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Loading");
            progressDialog.setMessage("Please Wait");

            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }


    }

    //for layer 1
    private class Task extends AsyncTask<Object, Object, String> {

        @Override
        protected String doInBackground(Object... voids) {


            String url = FORMURL + "userid=" + getuserid + "&" + "token=" + gettoken + "&" + "subschemeid=" + getid;

            //  Log.e("url", url);

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
                            = jsonObject.getJSONArray("Layers");
                    layernamearray = new String[jsonArray.length()];
                    array = new String[jsonArray.length()];
                    HasLayer = new String[jsonArray.length()];
                    Id = new String[jsonArray.length()];

                    for (int i = 0; i < jsonArray.length(); i++) {

                        FormModel Subschemelistmodel = new FormModel();
                        JSONObject js = jsonArray.getJSONObject(i);

                        Subschemelistmodel.ID = js.getString("ID");
                        Subschemelistmodel.Name = js.getString("Name");

                        Subschemelistmodel.HasLayer = js.getString("HasLayer");
                        Subschemelistmodel.LayerName = js.getString("LayerName");


                        array[i] = Subschemelistmodel.Name;

                        HasLayer[i] = Subschemelistmodel.HasLayer;
                        layernamearray[i] = Subschemelistmodel.LayerName;
                        Log.e("Layer", HasLayer[i]);
                        Id[i] = Subschemelistmodel.ID;

                    }

                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                progressDialog.dismiss();
                finish();
            }


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FormActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Please Wait");

            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }


    }

    //for layer 2
    private class Layer2AsyncTask extends AsyncTask<String, Object, String> {


        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            String response = getHttpData.getData(url);
            return response;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("Layer2Response", s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String getCode = jsonObject.getString("ErrorCode");

                if (getCode.equals("000")) {

                    JSONArray jsonArray
                            = jsonObject.getJSONArray("Layers");

                    NamesOfLayer2 = new CharSequence[jsonArray.length()];
                    Id2 = new String[jsonArray.length()];

                    for (int i = 0; i < jsonArray.length(); i++) {

                        FormModel formModelList = new FormModel();
                        JSONObject js = jsonArray.getJSONObject(i);

                        formModelList.ID = js.getString("ID");
                        formModelList.Name = js.getString("Name");

                        NamesOfLayer2[i] = formModelList.Name;

                        Id2[i] = formModelList.ID;
                    }

                    progressDialog.dismiss();

                } else {

                    SharedPreferences.Editor edit = prefs.edit();
                    edit.clear();
                    edit.commit();
                    Intent intent = new Intent(FormActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            } catch (JSONException e) {
                Log.e("messageFromLayer2Task", e.getMessage());
                progressDialog.dismiss();
            }


        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FormActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Please Wait");

            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }


    private class AddPropertyAsyncTask extends AsyncTask<String, Void, String> {

        String stream = null;
        String url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FormActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Adding Plot");

            progressDialog.setMessage("Please Wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String[] params) {
            url = params[0];
            Log.e("Addpropertyurl", url);

            stream = getJson(url);

            return stream;

        }

        private String getJson(String url) {
            HttpClient httpClient = new DefaultHttpClient();


            HttpPost post = new HttpPost(url);

            List<NameValuePair> parameters = new ArrayList<>();
            Log.e("whichType", whichType);

            if (whichType.equals("Sale")) {
                parameters.add(new BasicNameValuePair("propertytype", whichType));
                parameters.add(new BasicNameValuePair("plotid", PlotIdToSend));
                Log.e("plotid", PlotIdToSend);
                parameters.add(new BasicNameValuePair("price", value));
                parameters.add(new BasicNameValuePair("area", AreaName));

            } else if (whichType.equals("Wanted")) {

                parameters.add(new BasicNameValuePair("propertytype", whichType));
                parameters.add(new BasicNameValuePair("schemeid", schemeid));
                parameters.add(new BasicNameValuePair("subschemeid", getid));

                parameters.add(new BasicNameValuePair("layer1id", getLayerid));
                if (getLayerid2 != null) {
                    parameters.add(new BasicNameValuePair("layer2id", getLayerid2));
                }
                //parameters.add(new BasicNameValuePair("layer2id", getLayerid2));
                parameters.add(new BasicNameValuePair("wantedpropertytype", TypeOfWantedPlot));
                parameters.add(new BasicNameValuePair("wantedpropertyarea", AreaOfWantedPlot));
            }


            StringBuilder buffer = new StringBuilder();

            try {
                // Log.e("Insidegetjson", "insidetry");
                UrlEncodedFormEntity encoded = new UrlEncodedFormEntity(parameters);
                post.setEntity(encoded);
                HttpResponse response = httpClient.execute(post);

                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String Line = "";

                while ((Line = reader.readLine()) != null) {
                    Log.e("buffer", Line);
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
            String errorCode = "";

            JSONObject jsonobj;
            if (s != null) {
                try {
                    jsonobj = new JSONObject(s);

                    errorCode = jsonobj.getString("ErrorCode");

                    if (errorCode.equals("000")) {
                        clearFields();
                        Sneaker.with(FormActivity.this)
                                .setDuration(5000)
                                .setMessage("Plot Added!")
                                .sneakSuccess();

                    } else {
                        Sneaker.with(FormActivity.this)
                                .setTitle("Error!")
                                .setDuration(5000)
                                .setMessage("Plot Not Added!")
                                .sneakError();
                        Log.e("InsideAddPropertyAsync", errorCode);
                    }
                } catch (JSONException e) {
                    Log.e("InsideAddPropertyAsync", errorCode);

                }
            }
            progressDialog.dismiss();
            commercial.setEnabled(true);
            residential.setEnabled(true);
            LayerOneSearch = false;
            Layer2Search = false;

        }
    }

    private class Task2 extends AsyncTask<Object, Object, String> {

        @Override
        protected String doInBackground(Object... voids) {


            String url = "http://www.gwadar247.pk/api/logout.php" + "?userid=" + getuserid + "&" + "token=" + gettoken;
            Log.e("logout", url);


            String response = getHttpData.getData(url);

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String getCode = "";
            try {
                JSONObject jsonObject = new JSONObject(s);
                getCode = jsonObject.getString("ErrorCode");
                if (getCode.equals("000")) {
                    Toast.makeText(FormActivity.this, "Re Login!", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor edit = prefs.edit();
                    edit.clear();
                    edit.commit();
                    Intent intent = new Intent(FormActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else if (getCode.equals("802")) {

                    Log.e("ErrorCode", getCode);
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.clear();
                    edit.commit();

                    Sneaker.with(FormActivity.this)
                            .setTitle("Error!")
                            .setDuration(4000)
                            .setMessage("Login Again")
                            .sneakError();

                    progressDialog.dismiss();
                    Intent intent = new Intent(FormActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Sneaker.with(FormActivity.this)
                            .setTitle("Error!")
                            .setDuration(4000)
                            .setMessage("Something Went Wrong! Please Relogin.")
                            .sneakWarning();
                    progressDialog.dismiss();
                }

            } catch (JSONException e) {
                Log.e("Logout", e.getMessage());
                Log.e("Cause", e.getLocalizedMessage());

            }


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FormActivity.this, R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Logging Out");
            progressDialog.setMessage("Please Wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        form = this;
    }
}