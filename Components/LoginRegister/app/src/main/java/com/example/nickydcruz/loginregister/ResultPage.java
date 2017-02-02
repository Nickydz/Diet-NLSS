package com.example.nickydcruz.loginregister;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultPage extends AppCompatActivity {

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

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final int age = intent.getIntExtra("age",0);
        float bmi = intent.getFloatExtra("BMI",0.0f);
        final int bmr = intent.getIntExtra("BMR",0);
        final FormulaClass f = new FormulaClass();
        String category = f.category(bmi);
        String expertsMessage = "";
        tvCategory.setText(category);
        tvBmr.setText("Your Basal metabolic rate (BMR) is " + bmr);

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
                int bmr1 = bmr + 500;
                int a[] = f.bmrcal(bmr1*0.25);
                int b[] = f.bmrcal(bmr1*0.125);

                Toast.makeText(ResultPage.this,a[0]+"", Toast.LENGTH_SHORT).show();

                Response.Listener<String> listener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);
                            boolean success = jsonresponse.getBoolean("success");
                            if(success){
                                Intent int1=new Intent(ResultPage.this,Homescreen.class);
                                int1.putExtra("diet",jsonresponse.toString());
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
                int a[] = f.bmrcal(bmr1*0.25);
                int b[] = f.bmrcal(bmr1*0.125);
                Toast.makeText(ResultPage.this,a[2]+"", Toast.LENGTH_SHORT).show();

                Response.Listener<String> listener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);

                            boolean success = jsonresponse.getBoolean("success");
                            if(success){
                                Intent int1=new Intent(ResultPage.this,Homescreen.class);
                                int1.putExtra("diet",jsonresponse.toString());
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

                Toast.makeText(ResultPage.this,a[0]+"", Toast.LENGTH_SHORT).show();

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Error")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(ResultPage.this);
                            builder.setMessage("Registration failed")
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


    }


}

