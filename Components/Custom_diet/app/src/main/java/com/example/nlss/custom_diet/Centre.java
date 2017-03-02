package com.example.nlss.custom_diet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Centre extends Activity {
    DBaseHelper Mydb;
    //String username = "Leo";


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
        final TextView lntvdisp = (TextView) findViewById(R.id.lntvdisp);
        final TextView dntvdisp = (TextView) findViewById(R.id.dntvdisp);
        final TextView sntvdisp = (TextView) findViewById(R.id.sntvdisp);



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
                String bfpick = (String) parent.getItemAtPosition(position);
                Toast.makeText(context, "" + bfpick, Toast.LENGTH_SHORT).show();
                bftvdisp.setVisibility(View.VISIBLE);
                bftvdisp.setText(bfpick);
            }
        });

        ln.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                String lnpick = (String) parent.getItemAtPosition(position);
                Toast.makeText(context, "" + lnpick, Toast.LENGTH_SHORT).show();
                lntvdisp.setVisibility(View.VISIBLE);
                lntvdisp.setText(lnpick);
            }
        });

        dn.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                String dnpick = (String) parent.getItemAtPosition(position);
                Toast.makeText(context, "" + dnpick, Toast.LENGTH_SHORT).show();
                dntvdisp.setVisibility(View.VISIBLE);
                dntvdisp.setText(dnpick);
            }
        });

        sn.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                String snpick = (String) parent.getItemAtPosition(position);
                Toast.makeText(context, "" + snpick, Toast.LENGTH_SHORT).show();
                sntvdisp.setVisibility(View.VISIBLE);
                sntvdisp.setText(snpick);
            }
        });


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
                        Intent intent = new Intent(Centre.this, Second.class);
                        Centre.this.startActivity(intent);
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
    }

}
