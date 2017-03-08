package com.example.nlss.custom_diet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Leo on 2/16/2017.
 */

public class Centre extends Activity {
    DBaseHelper Mydb;
    //String username = "Leo";
    String bfpick;
    String lnpick;
    String snpick;
    String dnpick;
    String selectQuery;


    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre);
        Mydb = new DBaseHelper(context);
        AutoCompleteTextView bf;
        AutoCompleteTextView ln;
        AutoCompleteTextView dn;
        AutoCompleteTextView sn;
        bf = (AutoCompleteTextView) findViewById(R.id.bfac);
        ln = (AutoCompleteTextView) findViewById(R.id.lnac);
        dn = (AutoCompleteTextView) findViewById(R.id.dnac);
        sn = (AutoCompleteTextView) findViewById(R.id.snac);

        final TextView bftvdisp = (TextView) findViewById(R.id.bftvdisp);
        final TextView bfcal = (TextView) findViewById(R.id.bfcal);
        final TextView lntvdisp = (TextView) findViewById(R.id.lntvdisp);
        final TextView lncal = (TextView) findViewById(R.id.lncal);
        final TextView dntvdisp = (TextView) findViewById(R.id.dntvdisp);
        final TextView dncal = (TextView) findViewById(R.id.dncal);
        final TextView sntvdisp = (TextView) findViewById(R.id.sntvdisp);
        final TextView sncal = (TextView) findViewById(R.id.sncal);



        String[] foo1 = getResources().getStringArray(R.array.foods);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, foo1);
        bf.setAdapter(adapter1);

        String[] foo2 = getResources().getStringArray(R.array.foods);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, foo2);
        ln.setAdapter(adapter2);

        String[] foo3 = getResources().getStringArray(R.array.foods);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, foo3);
        dn.setAdapter(adapter3);

        String[] foo4 = getResources().getStringArray(R.array.foods);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, foo4);
        sn.setAdapter(adapter4);


        bf.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                bfpick = (String) parent.getItemAtPosition(position);
                //Toast.makeText(context, "" + bfpick, Toast.LENGTH_SHORT).show();
                bftvdisp.setVisibility(View.VISIBLE);
                bftvdisp.setText(bfpick);
            }
        });

        ln.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                lnpick = (String) parent.getItemAtPosition(position);
                //Toast.makeText(context, "" + lnpick, Toast.LENGTH_SHORT).show();
                lntvdisp.setVisibility(View.VISIBLE);
                lntvdisp.setText(lnpick);
            }
        });

        dn.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                dnpick = (String) parent.getItemAtPosition(position);
                //Toast.makeText(context, "" + dnpick, Toast.LENGTH_SHORT).show();
                dntvdisp.setVisibility(View.VISIBLE);
                dntvdisp.setText(dnpick);
            }
        });

        sn.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                snpick = (String) parent.getItemAtPosition(position);
                //Toast.makeText(context, "" + snpick, Toast.LENGTH_SHORT).show();
                sntvdisp.setVisibility(View.VISIBLE);
                sntvdisp.setText(snpick);
                //return(snpick);
            }
        });

        Toast.makeText(context, "" + snpick, Toast.LENGTH_SHORT).show();

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    int key = jsonResponse.getInt("key");
                    if (success) {
                        for (int i = 0; i < key; i = i + 6)
                            Mydb.insertData(jsonResponse.getString(i + ""), jsonResponse.getString((i + 1) + ""), jsonResponse.getString((i + 2) + ""), jsonResponse.getString((i + 3) + ""), jsonResponse.getString((i + 4) + ""), jsonResponse.getString((i + 5) + ""));

                        Toast.makeText(Centre.this, "Success", Toast.LENGTH_LONG).show();
                        int i=1000;
                    } else {
                        Toast.makeText(Centre.this, "Error", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        CustomRequest customRequest = new CustomRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(Centre.this);
        queue.add(customRequest);

        List<String> pickList = new ArrayList<>();
        pickList.add(bfpick);
        pickList.add(lnpick);
        pickList.add(dnpick);
        pickList.add(snpick);

        HashMap<String, String> map = new HashMap<String, String>();
        for(int i=0; i<pickList.size();i++){
            String food_pick =pickList.get(i);
            DBaseHelper dbasehelper = new DBaseHelper(context);
            Cursor res = dbasehelper.selectfood(food_pick);
            map.put("foodname"+i,res.getString(0));
            map.put("foodcal"+i,res.getString(1));
            //selectQuery = "SELECT food,calorie FROM foodDatabase WHERE food ="+food_pick;
            //        Cursor cursor = database.rawQuery(selectQuery, null);
        }
        bftvdisp.setText(map.get("foodname0"));
        lntvdisp.setText(map.get("foodname1"));
        dntvdisp.setText(map.get("foodname2"));
        sntvdisp.setText(map.get("foodname3"));
        bfcal.setText(map.get("foodcal0"));
        lncal.setText(map.get("foodcal1"));
        dncal.setText(map.get("foodcal2"));
        sncal.setText(map.get("foodcal3"));
        Toast.makeText(Centre.this, "" + selectQuery, Toast.LENGTH_LONG).show();

    }


}
