package com.example.nickydcruz.loginregister;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Leo on 2/16/2017.
 */

public class Centre extends Activity {
    DBaseHelper controller = new DBaseHelper(this);
    DBaseHelper Mydb;

    //for breakfast
    ArrayList <String> selectedbf = new ArrayList<String>();
    private ArrayAdapter<String> listAdapter ;
    ListView bfListView ;

    //for lunch
    ArrayList <String> selectedln = new ArrayList<String>();
    private ArrayAdapter<String> lnAdapter ;
    ListView lnListView ;

    //for dinner
    ArrayList <String> selecteddn = new ArrayList<String>();
    private ArrayAdapter<String> dnAdapter ;
    ListView dnListView ;

    //for snack
    ArrayList <String> selectedsn = new ArrayList<String>();
    private ArrayAdapter<String> snAdapter ;
    ListView snListView ;



    //String username = "Leo";
    String bfpick;
    String lnpick;
    String snpick;
    String dnpick;
    String selectQuery;
    HashMap<String,String> breakfast;
    HashMap<String,String> lunch;
    HashMap<String,String> dinner;
    HashMap<String,String> snack;


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

        //Listviews
        //for breakfast
        bfListView = (ListView) findViewById( R.id.bfListView );
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow,selectedbf);
        bfListView.setAdapter(listAdapter);

        //for lunch
        lnListView = (ListView) findViewById( R.id.lnListView );
        lnAdapter = new ArrayAdapter<String>(this, R.layout.lunchrow,selectedln);
        lnListView.setAdapter(lnAdapter);

        //for dinner
        dnListView = (ListView) findViewById( R.id.dnListView );
        dnAdapter = new ArrayAdapter<String>(this, R.layout.dinnerrow,selecteddn);
        dnListView.setAdapter(dnAdapter);

        //for snack
        snListView = (ListView) findViewById( R.id.snListView );
        snAdapter = new ArrayAdapter<String>(this, R.layout.snacksrow,selectedsn);
        snListView.setAdapter(snAdapter);

        final TextView bftvdisp = (TextView) findViewById(R.id.bftvdisp);
        final TextView bfcal = (TextView) findViewById(R.id.bfcal);
        final TextView lntvdisp = (TextView) findViewById(R.id.lntvdisp);
        final TextView lncal = (TextView) findViewById(R.id.lncal);
        final TextView dntvdisp = (TextView) findViewById(R.id.dntvdisp);
        final TextView dncal = (TextView) findViewById(R.id.dncal);
        final TextView sntvdisp = (TextView) findViewById(R.id.sntvdisp);
        final TextView sncal = (TextView) findViewById(R.id.sncal);
        final Button bfADD= (Button) findViewById(R.id.breakfastAddBT);
        final Button lnADD= (Button) findViewById(R.id.LunchAddBT);
        final Button dnADD= (Button) findViewById(R.id.dinnerAddBT);
        final Button snADD= (Button) findViewById(R.id.snacksAddBT);
        breakfast = new HashMap<>();
        lunch = new HashMap<>();
        dinner = new HashMap<>();
        snack = new HashMap<>();
        final int[] bfcount = {0};
        int lncount =0;
        int dncount =0;
        int sncount =0;

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

        //for breakfast
        final String[] a = new String[1];
        final String[] b = new String[1];
        final String[] bfadd = new String[1];

        bf.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                bfpick = (String) parent.getItemAtPosition(position);
                //Toast.makeText(context, "" + bfpick, Toast.LENGTH_SHORT).show();
                bftvdisp.setVisibility(View.VISIBLE);
                bfcal.setVisibility(View.VISIBLE);
                DBaseHelper dbasehelper = new DBaseHelper(Centre.this);
                String[] res = dbasehelper.selectfood(bfpick);
//                ArrayList<HashMap<String, String>> userList = controller.selectfood();
                bftvdisp.setText(res[0]);
                bfcal.setText(res[1]);
                a[0] = res[0].toString();
                b[0] = res[1].toString();
            }
        });
        //bftvdisp.getText().toString()
        //bfcal.getText().toString()

//        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
//                "Jupiter", "Saturn", "Uranus", "Neptune"};
//        ArrayList<String> planetList = new ArrayList<String>();
//        planetList.addAll( Arrays.asList(planets) );

        bfADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                breakfast.get("bfname" + bfcount[0])
                if(!(bftvdisp.getText().toString().equals("TextView") && bfcal.getText().toString().equals("TextView"))){

                    bfadd[0] = bftvdisp.getText().toString();
                    selectedbf.add(bfadd[0]);
                    listAdapter.notifyDataSetChanged();
//                    //selectedbf.add(a[0]);

//                    //breakfast.put("bfname"+ bfcount[0],a[0].toString());
//                    //breakfast.put("bfcal" + bfcount[0],b[0].toString());
//
//                    //listAdapter.add(breakfast.get("bfcal" + bfcount[0]));
//                    //mainListView.setAdapter( listAdapter );// but this line not in for loop...outside it
//                   // bfcount[0]++; //bfcount me ek extra hai...-1 in intent(something putint)
//                    listAdapter = new ArrayAdapter<String>(context, R.layout.simplerow,selectedbf);
//                    listAdapter.add(a[0]); //next activity me ...this line in for loop
//                    listAdapter.notifyDataSetChanged();
//                    mainListView.setAdapter( listAdapter );

                }

            }
        });

        bfListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedbf.remove(position);
                listAdapter.notifyDataSetChanged();
                Toast.makeText(Centre.this, "Item has been removed from your list", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //for lunch
        final String[] c = new String[1];
        final String[] d = new String[1];
        final String[] lnadd = new String[1];

        ln.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                lnpick = (String) parent.getItemAtPosition(position);
                //Toast.makeText(context, "" + lnpick, Toast.LENGTH_SHORT).show();
                lntvdisp.setVisibility(View.VISIBLE);
                lncal.setVisibility(View.VISIBLE);
                DBaseHelper dbasehelper = new DBaseHelper(Centre.this);
                String[] res = dbasehelper.selectfood(lnpick);
                lntvdisp.setText(res[0]);
                lncal.setText(res[1]);
                c[0] = res[0].toString();
                d[0] = res[1].toString();
            }
        });

        lnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                breakfast.get("bfname" + bfcount[0])
                if(!(lntvdisp.getText().toString().equals("TextView") && lncal.getText().toString().equals("TextView"))){

                    lnadd[0] = lntvdisp.getText().toString();
                    selectedln.add(lnadd[0]);
                    lnAdapter.notifyDataSetChanged();
                }

            }
        });

        lnListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedln.remove(position);
                lnAdapter.notifyDataSetChanged();
                Toast.makeText(Centre.this, "Item has been removed from your list", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //for dinner
        final String[] e = new String[1];
        final String[] f = new String[1];
        final String[] dnadd = new String[1];

        dn.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                dnpick = (String) parent.getItemAtPosition(position);
                //Toast.makeText(context, "" + dnpick, Toast.LENGTH_SHORT).show();
                dntvdisp.setVisibility(View.VISIBLE);
                dncal.setVisibility(View.VISIBLE);
                DBaseHelper dbasehelper = new DBaseHelper(Centre.this);
                String[] res = dbasehelper.selectfood(dnpick);
                dntvdisp.setText(res[0]);
                dncal.setText(res[1]);
                e[0] = res[0].toString();
                f[0] = res[1].toString();
            }
        });

        dnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                breakfast.get("bfname" + bfcount[0])
                if(!(dntvdisp.getText().toString().equals("TextView") && dncal.getText().toString().equals("TextView"))){

                    dnadd[0] = dntvdisp.getText().toString();
                    selecteddn.add(dnadd[0]);
                    dnAdapter.notifyDataSetChanged();
                }

            }
        });

        dnListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selecteddn.remove(position);
                dnAdapter.notifyDataSetChanged();
                Toast.makeText(Centre.this, "Item has been removed from your list", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //for snacks
        final String[] g = new String[1];
        final String[] h = new String[1];
        final String[] snadd = new String[1];

        sn.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                snpick = (String) parent.getItemAtPosition(position);
                //Toast.makeText(context, "" + snpick, Toast.LENGTH_SHORT).show();
                sntvdisp.setVisibility(View.VISIBLE);
                sncal.setVisibility(View.VISIBLE);
                DBaseHelper dbasehelper = new DBaseHelper(Centre.this);
                String[] res = dbasehelper.selectfood(snpick);
                sntvdisp.setText(res[0]);
                sncal.setText(res[1]);
                g[0] = res[0].toString();
                h[0] = res[1].toString();
            }
        });

        snADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                breakfast.get("bfname" + bfcount[0])
                if(!(sntvdisp.getText().toString().equals("TextView") && sncal.getText().toString().equals("TextView"))){

                    snadd[0] = sntvdisp.getText().toString();
                    selectedsn.add(snadd[0]);
                    snAdapter.notifyDataSetChanged();
                }

            }
        });

        snListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedsn.remove(position);
                snAdapter.notifyDataSetChanged();
                Toast.makeText(Centre.this, "Item has been removed from your list", Toast.LENGTH_SHORT).show();
                return false;
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




//        List<String> pickList = new ArrayList<>();
//        pickList.add(bfpick);
//        pickList.add(lnpick);
//        pickList.add(dnpick);
//        pickList.add(snpick);
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        for(int i=0; i<pickList.size();i++){
//            String food_pick =pickList.get(i);
//            DBaseHelper dbasehelper = new DBaseHelper(Centre.this);
//            Cursor res = dbasehelper.selectfood(food_pick);
//            map.put("foodname"+i,res.getString(0));
//            map.put("foodcal"+i,res.getString(1));
//            //selectQuery = "SELECT food,calorie FROM foodDatabase WHERE food ="+food_pick;
//            //        Cursor cursor = database.rawQuery(selectQuery, null);
//        }
//        bftvdisp.setText(map.get("foodname0"));
//        lntvdisp.setText(map.get("foodname1"));
//        dntvdisp.setText(map.get("foodname2"));
//        sntvdisp.setText(map.get("foodname3"));
//        bfcal.setText(map.get("foodcal0"));
//        lncal.setText(map.get("foodcal1"));
//        dncal.setText(map.get("foodcal2"));
//        sncal.setText(map.get("foodcal3"));
//        Toast.makeText(Centre.this, "" + selectQuery, Toast.LENGTH_LONG).show();
//submit btn
//        //HashMap<String, String> hashMap = new HashMap<String, String>();
//        //hashMap.put("key", "value");
//        Intent intent = new Intent(this, Second.class);
//        intent.putExtra("mappy", map);
//        startActivity(intent);

    }


}
