package com.example.shariqkhan.gawadar247.DaniyalClasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shariqkhan.gawadar247.FavouriteSales;
import com.example.shariqkhan.gawadar247.LoginActivity;
import com.example.shariqkhan.gawadar247.NewsFeedActivity;
import com.example.shariqkhan.gawadar247.R;
import com.example.shariqkhan.gawadar247.adapters.favouriteAdapter;
import com.example.shariqkhan.gawadar247.getdata.getHttpData;
import com.example.shariqkhan.gawadar247.models.FavouriteModel;
import com.example.shariqkhan.gawadar247.models.NewsFeedModel;
import com.irozon.sneaker.Sneaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zameer on 15/08/2017.
 */
public class Sales extends Fragment {
    @Nullable

    RecyclerView recyclerView;
    int checkForText = 0;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    public static String NEWSFEED_URL = "http://www.gwadar247.pk/api/newsfeed.php?";
    SharedPreferences prefs;
    private String getuserid;
    public ProgressDialog progressDialog;
    private String gettoken;
    private Context context = getContext();
    ArrayList<FavouriteModel> arrayList = new ArrayList<>();
    public String LOGOUT_URL = "http://www.gwadar247.pk/api/logout.php";

    public void refreshFrag()
    {
        Sales sale = new Sales();
        getFragmentManager().beginTransaction().detach(sale).commit();
        getFragmentManager().beginTransaction().attach(sale).commit();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sales, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler1);

        prefs = getContext().getSharedPreferences("SharedPreferences", MODE_PRIVATE);

        getuserid = prefs.getString("id", null);
        gettoken = prefs.getString("token", null);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        adapter = new favouriteAdapter(arrayList, getContext(),1);
        recyclerView.setAdapter(adapter);
        Task task = new Task();
        task.execute();

        return view;
    }

    private class Task extends AsyncTask<Object, Object, String> {

        @Override
        protected String doInBackground(Object... voids) {

            String url = NEWSFEED_URL + "userid=" + getuserid + "&" + "token=" + gettoken;

            Log.e("url", url);

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
                    Log.e("Res", s);
                    // Toast.makeText(getContext(), "inside sales class", Toast.LENGTH_SHORT).show();

                    JSONObject propertiesObject
                            = jsonObject.getJSONObject("Properties");

                    JSONArray jsonArray = propertiesObject.getJSONArray("Sale");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        //  Log.e("loop", String.valueOf(i));

                        FavouriteModel schemelistmodel = new FavouriteModel();

                        JSONObject js = jsonArray.getJSONObject(i);


                        schemelistmodel.ID = js.getString("ID");
                        schemelistmodel.Rating = js.getString("Rating");
                        schemelistmodel.Price = js.getString("Price");
                        schemelistmodel.Image = js.getString("Image");
                        schemelistmodel.Area = js.getString("Area");
                        schemelistmodel.IsSold = js.getString("IsSold");
                        schemelistmodel.PlotType = js.getString("PlotType");
                        schemelistmodel.PlotNo = js.getString("PlotNo");
                        schemelistmodel.SchemeName = js.getString("SchemeName");
                        schemelistmodel.SubSchemeName = js.getString("SubSchemeName");
                        schemelistmodel.Layer1Name = js.getString("Layer1Name");
                        schemelistmodel.Layer2Name = js.getString("Layer2Name");
                        schemelistmodel.IsFavourite = js.getString("IsFavorite");
                        schemelistmodel.IsRequested = js.getString("IsRequested");

                        //       Toast.makeText(getContext(), schemelistmodel.Layer2Name, Toast.LENGTH_SHORT).show();


                        arrayList.add(schemelistmodel);


                    }

                    adapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                } else if (getCode.equals("802")) {
                    Toast.makeText(getContext(), "Login Session Expired !", Toast.LENGTH_SHORT).show();
                    Task2 task = new Task2();
                    task.execute();
                    progressDialog.dismiss();

                } else {
                    Sneaker.with(getActivity())
                            .setTitle("Error!!")
                            .setDuration(4000)
                            .setMessage("This is the error message")
                            .sneakError();
                    progressDialog.dismiss();
                }

            } catch (JSONException e) {
                Log.e("timeout", e.getMessage());
                progressDialog.dismiss();
            }
progressDialog.dismiss();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Loading ");
            progressDialog.setMessage("Please Wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

    }

    private class Task2 extends AsyncTask<Object, Object, String> {

        @Override
        protected String doInBackground(Object... voids) {


            String url = LOGOUT_URL + "?userid=" + getuserid + "&" + "token=" + gettoken;
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
                    Toast.makeText(getContext(), "Re Login!", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor edit = prefs.edit();
                    edit.clear();
                    edit.commit();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().getSupportFragmentManager().popBackStack();

                } else if (getCode.equals("802")) {

                    SharedPreferences.Editor edit = prefs.edit();
                    edit.clear();
                    edit.commit();
                    Toast.makeText(getActivity(), "Login Again", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Sneaker.with(getActivity())
                            .setTitle("Error!!")
                            .setDuration(4000)
                            .setMessage("This is the error message")
                            .sneakError();
                    progressDialog.dismiss();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
            progressDialog.setTitle("Loading Plot of sales");
            progressDialog.setMessage("Please Wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

    }

}
