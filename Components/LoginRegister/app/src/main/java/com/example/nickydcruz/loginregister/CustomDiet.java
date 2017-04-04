package com.example.nickydcruz.loginregister;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Leo on 2/16/2017.
 */

public class CustomDiet extends Activity {
    DBaseHelper controller = new DBaseHelper(this);
    DBaseHelper Mydb;
    DBHelper myDb;
    DietContract.DietEntry de;
    TextView t;


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
    int cobf=0;
    ArrayList<String> bfac;
    ArrayList<String> bfca;
    ArrayList<String> bfxpl,lnac,lnca,lnxpl,dnac,dnca,dnxpl,snac,snca,snxpl;
    int lncount =0;
    int dncount =0;
    int sncount =0;
    SharedPreferences pref;

    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);

        t = (TextView) findViewById(R.id.bf);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/Zapf Humanist 601 Bold BT.ttf");
        t.setTypeface(myCustomFont);

        t = (TextView) findViewById(R.id.ln);
        Typeface myCustomFont1=Typeface.createFromAsset(getAssets(),"fonts/Zapf Humanist 601 Bold BT.ttf");
        t.setTypeface(myCustomFont1);

        t = (TextView) findViewById(R.id.sn);
        Typeface myCustomFont2=Typeface.createFromAsset(getAssets(),"fonts/Zapf Humanist 601 Bold BT.ttf");
        t.setTypeface(myCustomFont2);

        t = (TextView) findViewById(R.id.dn);
        Typeface myCustomFont3=Typeface.createFromAsset(getAssets(),"fonts/Zapf Humanist 601 Bold BT.ttf");
        t.setTypeface(myCustomFont3);

        de = new DietContract.DietEntry(pref.getString("username",""));
        myDb = new DBHelper(this,pref.getString("username",""));
        Mydb = new DBaseHelper(context);
        bfac= new ArrayList<>(15);
        bfca= new ArrayList<>(15);
        bfxpl = new ArrayList<>();
        snxpl= new ArrayList<>(15);
        dnxpl= new ArrayList<>(15);
        lnxpl= new ArrayList<>(15);
        lnac= new ArrayList<>(15);
        dnac= new ArrayList<>(15);
        snac= new ArrayList<>(15);
        lnca= new ArrayList<>(15);
        dnca= new ArrayList<>(15);
        snca= new ArrayList<>(15);
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
        final String[] bfc = new String[1];
        final String[] bfadd = new String[1];
        bf.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                bfpick = (String) parent.getItemAtPosition(position);
                //Toast.makeText(context, "" + bfpick, Toast.LENGTH_SHORT).show();
                bftvdisp.setVisibility(View.VISIBLE);
                bfcal.setVisibility(View.VISIBLE);
                DBaseHelper dbasehelper = new DBaseHelper(CustomDiet.this);
                String[] res = dbasehelper.selectfood(bfpick);
//                ArrayList<HashMap<String, String>> userList = controller.selectfood();
                bftvdisp.setText(res[0]);
                bfcal.setText(res[1]);
                a[0] = res[0];
                b[0] = res[1];
                bfc[0] = res[2];

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
//                    if(cobf==0) {
//                        breakfast.put("bf", a[0]);
//                        breakfast.put("bfcal",b[0]);
//                        breakfast.put(cobf+"",bfc[0]);
//                        cobf++;
//                    }
//                    else {
//                        breakfast.put("bf",breakfast.get("bf")+ ";"+a[0]);
//                        breakfast.put("bfcal",breakfast.get("bfcal")+"\n"+b[0]);
//                        breakfast.put(cobf+"",bfc[0]);
//                        cobf++;
//                    }
                    bfac.add(cobf,a[0]);
                    bfca.add(cobf,b[0]);
                    bfxpl.add(cobf,bfc[0]);
                    selectedbf.add(bfadd[0]);
                    cobf++;
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
                cobf--;
                bfac.remove(position);
                bfca.remove(position);
                bfxpl.remove(position);
                selectedbf.remove(position);
                listAdapter.notifyDataSetChanged();
                Toast.makeText(CustomDiet.this, "Item has been removed from your list", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //for lunch
        final String[] c = new String[1];
        final String[] d = new String[1];
        final String[] lnd = new String[1];
        final String[] lnadd = new String[1];

        ln.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                lnpick = (String) parent.getItemAtPosition(position);
                //Toast.makeText(context, "" + lnpick, Toast.LENGTH_SHORT).show();
                lntvdisp.setVisibility(View.VISIBLE);
                lncal.setVisibility(View.VISIBLE);
                DBaseHelper dbasehelper = new DBaseHelper(CustomDiet.this);
                String[] res = dbasehelper.selectfood(lnpick);
                lntvdisp.setText(res[0]);
                lncal.setText(res[1]);
                c[0] = res[0];
                d[0] = res[1];
                lnd[0] = res[2];
            }
        });

        lnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                breakfast.get("bfname" + bfcount[0])
                if(!(lntvdisp.getText().toString().equals("TextView") && lncal.getText().toString().equals("TextView"))){

                    lnac.add(lncount,c[0]);
                    lnca.add(lncount,d[0]);
                    lnxpl.add(lncount,lnd[0]);
                    lncount++;
                    lnadd[0] = lntvdisp.getText().toString();
                    selectedln.add(lnadd[0]);
                    lnAdapter.notifyDataSetChanged();
                }

            }
        });

        lnListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lncount--;
                lnac.remove(position);
                lnca.remove(position);
                lnxpl.remove(position);
                selectedln.remove(position);
                lnAdapter.notifyDataSetChanged();
                Toast.makeText(CustomDiet.this, "Item has been removed from your list", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //for dinner
        final String[] e = new String[1];
        final String[] f = new String[1];
        final String[] dnf = new String[1];
        final String[] dnadd = new String[1];

        dn.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                dnpick = (String) parent.getItemAtPosition(position);
                //Toast.makeText(context, "" + dnpick, Toast.LENGTH_SHORT).show();
                dntvdisp.setVisibility(View.VISIBLE);
                dncal.setVisibility(View.VISIBLE);
                DBaseHelper dbasehelper = new DBaseHelper(CustomDiet.this);
                String[] res = dbasehelper.selectfood(dnpick);
                dntvdisp.setText(res[0]);
                dncal.setText(res[1]);
                e[0] = res[0];
                f[0] = res[1];
                dnf[0] = res[2];
            }
        });

        dnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                breakfast.get("bfname" + bfcount[0])
                if(!(dntvdisp.getText().toString().equals("TextView") && dncal.getText().toString().equals("TextView"))){

                    dnac.add(dncount,e[0]);
                    dnca.add(dncount,f[0]);
                    dnxpl.add(dncount,dnf[0]);
                    dncount++;
                    dnadd[0] = dntvdisp.getText().toString();
                    selecteddn.add(dnadd[0]);
                    dnAdapter.notifyDataSetChanged();
                }

            }
        });

        dnListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dncount--;
                dnac.remove(position);
                dnca.remove(position);
                dnxpl.remove(position);
                selecteddn.remove(position);
                dnAdapter.notifyDataSetChanged();
                Toast.makeText(CustomDiet.this, "Item has been removed from your list", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //for snacks
        final String[] g = new String[1];
        final String[] h = new String[1];
        final String[] snh = new String[1];
        final String[] snadd = new String[1];

        sn.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                snpick = (String) parent.getItemAtPosition(position);
                //Toast.makeText(context, "" + snpick, Toast.LENGTH_SHORT).show();
                sntvdisp.setVisibility(View.VISIBLE);
                sncal.setVisibility(View.VISIBLE);
                DBaseHelper dbasehelper = new DBaseHelper(CustomDiet.this);
                String[] res = dbasehelper.selectfood(snpick);
                sntvdisp.setText(res[0]);
                sncal.setText(res[1]);
                g[0] = res[0];
                h[0] = res[1];
                snh[0] = res[2];
            }
        });

        snADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                breakfast.get("bfname" + bfcount[0])
                if(!(sntvdisp.getText().toString().equals("TextView") && sncal.getText().toString().equals("TextView"))){
                    snac.add(sncount,e[0]);
                    snca.add(sncount,f[0]);
                    snxpl.add(sncount,dnf[0]);
                    sncount++;

                    snadd[0] = sntvdisp.getText().toString();
                    selectedsn.add(snadd[0]);
                    snAdapter.notifyDataSetChanged();
                }

            }
        });

        snListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                sncount--;
                snac.remove(position);
                snca.remove(position);
                snxpl.remove(position);
                selectedsn.remove(position);
                snAdapter.notifyDataSetChanged();
                Toast.makeText(CustomDiet.this, "Item has been removed from your list", Toast.LENGTH_SHORT).show();
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

                        Toast.makeText(CustomDiet.this, "Success", Toast.LENGTH_LONG).show();
                        int i=1000;
                    } else {
                        Toast.makeText(CustomDiet.this, "Error", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        CustomRequest customRequest = new CustomRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(CustomDiet.this);
        queue.add(customRequest);

        final Button btconfirm = (Button) findViewById(R.id.btsaveDiet);

        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cobf == 0){
                    Toast.makeText(getApplicationContext(),"please select atleast one item in breakfast",Toast.LENGTH_LONG);
                }
                else if(lncount == 0){
                    Toast.makeText(getApplicationContext(),"please select atleast one item in lunch",Toast.LENGTH_LONG);
                }
                else if(dncount == 0){
                    Toast.makeText(getApplicationContext(),"please select atleast one item in dinner",Toast.LENGTH_LONG);
                }

                else{
                    breakfast.put("bf",bfac.get(0));
                    breakfast.put("bfcal",bfca.get(0));
                    breakfast.put("bfxpl",bfxpl.get(0));
                    for(int i=1;i<cobf;i++){
                        breakfast.put("bf",";"+bfac.get(i));
                        breakfast.put("bfcal","\n"+bfca.get(i));
                        breakfast.put("bfxpl",";"+bfxpl.get(i));
                    }
                    cobf--;
                    lunch.put("ln",lnac.get(0));
                    lunch.put("lncal",lnca.get(0));
                    lunch.put("lnxpl",lnxpl.get(0));
                    for(int i=1;i<lncount;i++){
                        lunch.put("ln",lnac.get(i));
                        lunch.put("lncal",lnca.get(i));
                        lunch.put("lnxpl",lnxpl.get(i));
                    }
                    lncount--;
                    dinner.put("dn",dnac.get(0));
                    dinner.put("dncal",dnca.get(0));
                    dinner.put("dnxpl",dnxpl.get(0));
                    for(int i=1;i<dncount;i++){
                        dinner.put("dn",dnac.get(i));
                        dinner.put("dncal",dnca.get(i));
                        dinner.put("dnxpl",dnxpl.get(i));
                    }
                    dncount--;
                    if(sncount==0){
                        snack.put("sn","-");
                        snack.put("sncal","-");
                        snack.put("snxpl","-");
                    }
                    else {
                        snack.put("sn",snac.get(0));
                        snack.put("sncal",snca.get(0));
                        snack.put("snxpl",snxpl.get(0));
                        for(int i=1;i<sncount;i++){
                            snack.put("sn",snack.get("sn")+";"+snac.get(i));
                            snack.put("sncal",snack.get("sncal")+"\n"+snca.get(i));
                            snack.put("snxpl",snack.get("snxpl")+";"+snxpl.get(i));
                        }
                        sncount--;
                    }

                    Calendar cal = Calendar.getInstance();
                    String date=cal.get(Calendar.YEAR) + "-"
                            + (cal.get(Calendar.MONTH)+1)
                            + "-" + cal.get(Calendar.DAY_OF_MONTH);

                    boolean acds= myDb.insertUserDietData(de.DietTableName,date,
                            breakfast.get("bf"),breakfast.get("bfcal"),breakfast.get("bfxpl"),lunch.get("ln"),
                            lunch.get("lncal"),lunch.get("lnxpl"),dinner.get("dn"),
                            dinner.get("dncal"), dinner.get("dnxpl"),snack.get("sn"),snack.get("sncal"),
                            snack.get("snxpl"),"-","-","-",cobf+"",lncount+"",
                            dncount+"",sncount+"",0+"",2700+"");

                    if(acds){
                        Intent i = new Intent(getApplicationContext(),Homescreen.class);
                        i.putExtra("bmr",1700+"");
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"kar raha kya hai abhi",Toast.LENGTH_LONG);
                    }

                }

            }
        });


//        List<String> pickList = new ArrayList<>();
//        pickList.add(bfpick);
//        pickList.add(lnpick);
//        pickList.add(dnpick);
//        pickList.add(snpick);
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        for(int i=0; i<pickList.size();i++){
//            String food_pick =pickList.get(i);
//            DBaseHelper dbasehelper = new DBaseHelper(CustomDiet.this);
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
//        Toast.makeText(CustomDiet.this, "" + selectQuery, Toast.LENGTH_LONG).show();
//submit btn
//        //HashMap<String, String> hashMap = new HashMap<String, String>();
//        //hashMap.put("key", "value");
//        Intent intent = new Intent(this, Second.class);
//        intent.putExtra("mappy", map);
//        startActivity(intent);

    }


}
