package com.example.nickydcruz.loginregister;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class ResultPage extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int amr;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        t = (TextView) findViewById(R.id.tvCategory);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/Zapf Humanist 601 Bold BT.ttf");
        t.setTypeface(myCustomFont);

        t = (TextView) findViewById(R.id.tvBmr);
        Typeface myCustomFont1=Typeface.createFromAsset(getAssets(),"fonts/Zapf Humanist 601 Bold BT.ttf");
        t.setTypeface(myCustomFont1);

        t = (TextView) findViewById(R.id.tvIdealweiht);
        Typeface myCustomFont2=Typeface.createFromAsset(getAssets(),"fonts/Zapf Humanist 601 Bold BT.ttf");
        t.setTypeface(myCustomFont2);

        t = (TextView) findViewById(R.id.tvExpertsMessage);
        Typeface myCustomFont3=Typeface.createFromAsset(getAssets(),"fonts/Zapf Humanist 601 Bold BT.ttf");
        t.setTypeface(myCustomFont3);

        TextView tvCategory =(TextView)findViewById(R.id.tvCategory);
        TextView tvBmr =(TextView)findViewById(R.id.tvBmr);
        TextView tvExperts = (TextView)findViewById(R.id.tvExpertsMessage);
        TextView tvIdealWeight = (TextView)findViewById(R.id.tvIdealweiht);
        Button btLW =(Button)findViewById(R.id.btLW);
        Button btMW =(Button)findViewById(R.id.btMW);
        Button btGW =(Button)findViewById(R.id.btGW);
        Button btcusD = (Button)findViewById(R.id.btcustD);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        editor=pref.edit();
        final String username = pref.getString("username","");
        int age = pref.getInt("age",0);
        final DBHelper myDb= new DBHelper(this,username);

        float height =Float.parseFloat(pref.getString("height","0"));
        final float weight = Float.parseFloat(pref.getString("weight","0"));
        float bmi = new FormulaClass().bmi(height,weight);
        int calburned =0;
        final int bmr = new FormulaClass().bmr(pref.getString("gender",""),pref.getInt("age",0),weight, height*100);




        Calendar cal = Calendar.getInstance();
        int dayoftheyear = cal.get(Calendar.DAY_OF_YEAR);

        final SharedPreferences prefere = getSharedPreferences(username+"update.conf",Context.MODE_PRIVATE);
        if(prefere.getInt("dayoftheyear",366)+6 < dayoftheyear){
            new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Resultpage))
                    .setTitle("Update Weight")
                    .setMessage("It's been a while since you last updated your weight . Would you like to update your weight?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // redirect to update page
                            prefere.edit().putInt("fromResultPage",1).apply();
                            if(prefere.getString("need","m").equals("l")) {
                                prefere.edit().putString("expweight", weight-0.5 + "").apply();
                            }
                            else if(prefere.getString("need","m").equals("g")){
                                prefere.edit().putString("expweight", weight+0.5 + "").apply();
                            }
                            else{
                                prefere.edit().putString("expweight", weight + "").apply();
                            }
                            Intent inupdate = new Intent(getApplicationContext(),Update_wtht.class);
                            startActivity(inupdate);

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        if(!(pref.getString("SystemDate",0+"").equals(cal.get(Calendar.YEAR) + "-"
                + (cal.get(Calendar.MONTH)+1)
                + "-" + cal.get(Calendar.DAY_OF_MONTH)))){
            editor.putInt("Updateamr",1);
            editor.remove("amr");
            editor.putString("SystemDate",cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DAY_OF_MONTH));
            editor.commit();
        }


        if(pref.getInt("advancedone",0)==1 && (pref.getInt("Updateamr",0)==1 || pref.getInt("Updateamr",0)==3)){
            editor.putInt("amr",Math.round(bmr*Float.parseFloat(pref.getString("exerciselevel",1+""))));
            if(pref.getInt("Updateamr",0)==3)
            editor.putInt("Updateamr",1);
            editor.commit();
        }


        if(pref.getString("calday",0+"").equals(cal.get(Calendar.YEAR) + "-"
                + (cal.get(Calendar.MONTH)+1)
                + "-" + (cal.get(Calendar.DAY_OF_MONTH)-1)) && (pref.getInt("Updateamr",0)==1 || pref.getInt("Updateamr",0)==3)){
            calburned= Math.round(pref.getInt("ExerciseCalc",0));
            amr =pref.getInt("amr", Math.round(bmr*Float.parseFloat(pref.getString("exerciselevel",1+""))));
            editor.putInt("amr",amr+calburned);
            editor.putInt("aupdate",5);
            editor.putInt("Updateamr",0);
            editor.putString("calday",0+"");
            editor.commit();
        }





        final FormulaClass f = new FormulaClass();
        String category = f.category(bmi);
        String expertsMessage = "";
        tvCategory.setText(category);
        amr =pref.getInt("amr", Math.round(bmr*Float.parseFloat(pref.getString("exerciselevel",1+""))));
        amr = amr + prefere.getInt("addbmr",0);
        String dailyintake = "Your Calorie requirement for the day is " + amr;
        editor.putInt("Updateamr",1);
        editor.apply();
        tvBmr.setText(dailyintake);

        btcusD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cus = new Intent(ResultPage.this,CustomDiet.class);
                ResultPage.this.startActivity(cus);
            }
        });

        ActionBar actionBar =getSupportActionBar();
        actionBar.setLogo(R.mipmap.nlss_crop);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        if(bmi<18.5) {
            expertsMessage = "Since your weight is lower than ideal we recommend you increase your weight.";
            btGW.setVisibility(View.VISIBLE);
            btGW.setClickable(true);

            if (bmi < 18.5 && bmi > 17) {
                expertsMessage = expertsMessage + "Alternatively .You could also maintain the same weight";
                btMW.setVisibility(View.VISIBLE);
                btMW.setClickable(true);
            }
        }
        else if(bmi >=18.5 && bmi < 25){
            expertsMessage = "Your weight is in the ideal range we recommend you maintain your weight. Alternatively you can also choose a diet to gain or loose your weight";
            btLW.setVisibility(View.VISIBLE);
            btMW.setVisibility(View.VISIBLE);
            btGW.setVisibility(View.VISIBLE);
            btLW.setClickable(true);
            btGW.setClickable(true);
            btMW.setClickable(true);
        }


        else if(bmi >=25 ) {
            expertsMessage = "Since your weight is more than ideal we recommend you decrease your weight.";
            btLW.setVisibility(View.VISIBLE);
            btLW.setClickable(true);
            if (bmi >= 25 && bmi < 27) {
                expertsMessage = expertsMessage + "Alternatively .You could also maintain the same weight";
                btMW.setVisibility(View.VISIBLE);
                btMW.setClickable(true);
            }
        }
        btGW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int bmr1 = amr + 500;
                int a[] = f.bmrcal(bmr1*0.25);
                int b[] = f.bmrcal(bmr1*0.125);

                Response.Listener<String> listener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);
                            boolean success = jsonresponse.getBoolean("success");
                            if(success){
                                Intent int1=new Intent(ResultPage.this,Homescreen.class);
                                int1.putExtra("username",username);
                                int1.putExtra("diet",jsonresponse.toString());
//                                int1.putExtra("bmr",bmr1+"");
                                if(pref.getInt("amr",1700)!=bmr1) {
                                    editor.putInt("aupdate",5);
                                    editor.putInt("amr", bmr1);
                                    editor.commit();
                                }
                                DietInsert d =new DietInsert(username,myDb);
                                d.dietDivider(jsonresponse);
                                prefere.edit().putString("need","g").apply();
                                startActivity(int1);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ResultPage.this);
                                builder.setMessage("Registration failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                if(pref.getString("type","n").equals("v")) {
                    RpRequestveg rp = new RpRequestveg(a[0] + "", a[1] + "", a[2] + "", b[0] + "", b[1] + "", b[2] + "", listener);
                    RequestQueue queue2 = Volley.newRequestQueue(ResultPage.this);
                    queue2.add(rp);
                }
                else {
                    RpRequest rp = new RpRequest(a[0] + "", a[1] + "", a[2] + "", b[0] + "", b[1] + "", b[2] + "", listener);
                    RequestQueue queue2 = Volley.newRequestQueue(ResultPage.this);
                    queue2.add(rp);
                }
            }
        });

        btLW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bmr1=amr;
                if(amr > 1700)
                bmr1 =amr -500;
                //to deal with final stipulation in intent
                final int bmr2=bmr1;
                int a[] = f.bmrcal(bmr1*0.25);
                int b[] = f.bmrcal(bmr1*0.125);

                Response.Listener<String> listener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);

                            boolean success = jsonresponse.getBoolean("success");
                            if(success){
                                Intent int1=new Intent(ResultPage.this,Homescreen.class);
                                int1.putExtra("diet",jsonresponse.toString());
                                int1.putExtra("username",username);
//                                int1.putExtra("bmr",bmr2+"");
                                if(pref.getInt("amr",1700)!=bmr2) {
                                    editor.putInt("aupdate",5);
                                    editor.putInt("amr", bmr2);
                                    editor.commit();
                                }
                                prefere.edit().putString("need","l").apply();
                                DietInsert d =new DietInsert(username,myDb);
                                d.dietDivider(jsonresponse);
                                startActivity(int1);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ResultPage.this);
                                builder.setMessage("Registration failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                };

                if(pref.getString("type","n").equals("v")) {
                    RpRequestveg rp = new RpRequestveg(a[0] + "", a[1] + "", a[2] + "", b[0] + "", b[1] + "", b[2] + "", listener);
                    RequestQueue queue2 = Volley.newRequestQueue(ResultPage.this);
                    queue2.add(rp);
                }
                else {
                    RpRequest rp = new RpRequest(a[0] + "", a[1] + "", a[2] + "", b[0] + "", b[1] + "", b[2] + "", listener);
                    RequestQueue queue2 = Volley.newRequestQueue(ResultPage.this);
                    queue2.add(rp);
                }
            }
        });

        btMW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a[] = f.bmrcal(amr*0.25);
                int b[] = f.bmrcal(amr*0.125);

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Error")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(ResultPage.this);
                            builder.setMessage("Download failed")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                        else {
                            try {
                                JSONObject jsonresponse = new JSONObject(response);
                                boolean success = jsonresponse.getBoolean("success");
                                if (success) {
                                    Intent int1 = new Intent(ResultPage.this, Homescreen.class);
                                    int1.putExtra("diet",jsonresponse.toString());
                                    int1.putExtra("username",username);
//                                    int1.putExtra("bmr",amr+"");
                                    if(pref.getInt("amr",1700)!=amr) {
                                    editor.putInt("aupdate",5);
                                    editor.putInt("amr", amr);
                                    editor.commit();
                                }
                                    DietInsert d =new DietInsert(username,myDb);
                                    prefere.edit().putString("need","m").apply();
                                    d.dietDivider(jsonresponse);
                                    startActivity(int1);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ResultPage.this);
                                    builder.setMessage("Registration failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                };

                if(pref.getString("type","n").equals("v")) {
                    RpRequestveg rp = new RpRequestveg(a[0] + "", a[1] + "", a[2] + "", b[0] + "", b[1] + "", b[2] + "", listener);
                    RequestQueue queue2 = Volley.newRequestQueue(ResultPage.this);
                    queue2.add(rp);
                }
                else {
                    RpRequest rp = new RpRequest(a[0] + "", a[1] + "", a[2] + "", b[0] + "", b[1] + "", b[2] + "", listener);
                    RequestQueue queue2 = Volley.newRequestQueue(ResultPage.this);
                    queue2.add(rp);
                }

            }
        });

        tvExperts.setText(expertsMessage);


        Button bt = (Button) findViewById(R.id.graph);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Graphs.class);
                startActivity(i);
           }
        }
        );



    }


}

