package com.example.nickydcruz.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ResultPage extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        TextView tvCategory =(TextView)findViewById(R.id.tvCategory);
        TextView tvBmr =(TextView)findViewById(R.id.tvBmr);
        TextView tvExperts = (TextView)findViewById(R.id.tvExpertsMessage);
        TextView tvIdealWeight = (TextView)findViewById(R.id.tvIdealweiht);
        Button btLW =(Button)findViewById(R.id.btLW);
        Button btMW =(Button)findViewById(R.id.btMW);
        Button btGW =(Button)findViewById(R.id.btGW);
        Button btcusD = (Button)findViewById(R.id.btcustD);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        final String username = pref.getString("username","");
        int age = pref.getInt("age",0);
        final DBHelper myDb= new DBHelper(this,username);

        float height = 0.3048f * Float.parseFloat(pref.getString("height","0"));
        float weight = Float.parseFloat(pref.getString("weight","0"));
        float bmi = new FormulaClass().bmi(height,weight);
        final int bmr = new FormulaClass().bmr(pref.getString("gender",""),pref.getInt("age",0),weight, height*100);
        final FormulaClass f = new FormulaClass();
        String category = f.category(bmi);
        String expertsMessage = "";
        tvCategory.setText(category);
        tvBmr.setText("Your Basal metabolic rate (BMR) is " + bmr);

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
            if (bmi >= 25 && bmi < 26) {
                expertsMessage = expertsMessage + "Alternatively .You could also maintain the same weight";
                btMW.setVisibility(View.VISIBLE);
                btMW.setClickable(true);
            }
        }
        btGW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int bmr1 = bmr + 500;
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
                                int1.putExtra("bmr",bmr1+"");
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

                RpRequest rp = new RpRequest(a[0]+"",a[1]+"",a[2]+"",b[0]+ "",b[1]+"",b[2]+"",listener);
                RequestQueue queue2 = Volley.newRequestQueue(ResultPage.this);
                queue2.add(rp);
            }
        });

        btLW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bmr1=bmr;
                if(bmr > 1700)
                bmr1 =bmr -500;
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
                                int1.putExtra("bmr",bmr2+"");
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

                RpRequest rp = new RpRequest(a[0]+"",a[1]+"",a[2]+"",b[0]+"",+b[1]+"",b[2]+"",listener);
                RequestQueue queue2 = Volley.newRequestQueue(ResultPage.this);
                queue2.add(rp);
            }
        });

        btMW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a[] = f.bmrcal(bmr*0.25);
                int b[] = f.bmrcal(bmr*0.125);

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
                                    int1.putExtra("bmr",bmr+"");
                                    DietInsert d =new DietInsert(username,myDb);
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

                RpRequest rp = new RpRequest(a[0]+"",a[1]+"",a[2]+"",b[0]+"",b[1]+"",b[2]+"",listener);
                RequestQueue queue2 = Volley.newRequestQueue(ResultPage.this);
                queue2.add(rp);

            }
        });

        tvExperts.setText(expertsMessage);


        Button bt = (Button) findViewById(R.id.graph);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Graph.class);
                startActivity(i);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_homescreen_actions,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(ResultPage.this,Homescreen.class);
        switch (item.getItemId()) {
            case R.id.foodcravings: i = new Intent(ResultPage.this, FoodCravings.class);
                break;

            case R.id.superfood: i = new Intent(ResultPage.this, superfood_main.class);
                break;

            case R.id.excal: i = new Intent(ResultPage.this, ExerciseCalculator.class);
                break;

            case R.id.diet: i = new Intent(ResultPage.this, Homescreen.class);
                break;

            case R.id.advanceSurvey: i = new Intent(ResultPage.this, Advanced_Survey.class);
                break;

            case R.id.activity_update_wtht: i = new Intent(ResultPage.this, Update_wtht.class);
                break;

            case R.id.logout: {
                pref.edit().clear().commit();
                i = new Intent(getApplicationContext(), LoginActivity.class);
                break;
            }

        }
        ResultPage.this.startActivity(i);
        return super.onOptionsItemSelected(item);
    }

}

