package com.example.nickydcruz.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BasicSurvey1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_survey1);


        final FormulaClass f = new FormulaClass();

        final Intent intent = getIntent();

        final String username = intent.getStringExtra("username");
        final String dob = intent.getStringExtra("dob");
        final int age = f.calculateAge(dob);



        final RadioGroup radioSexGroup = (RadioGroup)findViewById(R.id.radgrp);

        final EditText etHeight = (EditText) findViewById(R.id.numpick_bs);
        final EditText etWeight = (EditText) findViewById(R.id.etWeight);
        final EditText etWristCir = (EditText) findViewById(R.id.etWristCir);
        final Button submit =(Button) findViewById(R.id.btSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String height = etHeight.getText().toString();
                final String weight =etWeight.getText().toString();
                final String wristCir =etWristCir.getText().toString();

                int wristcri = Integer.parseInt(wristCir);
                float wieght = Float.parseFloat(weight);
                float hieght = Float.parseFloat(height);


                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                RadioButton radioSexButton = (RadioButton) findViewById(selectedId);
                String gender;
                if(radioSexButton.getText().equals("Male"))
                    gender = "M";
                else
                    gender = "F";

                Toast.makeText(BasicSurvey1.this,gender, Toast.LENGTH_SHORT).show();
        final float BMI = f.bmi( hieght,wieght);
        final int BMR = f.bmr(gender,age,wieght,hieght*100);

                Response.Listener<String> listener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse1 = new JSONObject(response);
                            boolean success = jsonResponse1.getBoolean("success");
                            if(success){
                                Intent int1 = new Intent(BasicSurvey1.this,ResultPage.class);
                                int1.putExtra("BMR",BMR);
                                int1.putExtra("BMI",BMI);
                                int1.putExtra("age",age);
                                int1.putExtra("username",username);
                                BasicSurvey1.this.startActivity(int1);
                            }
                            else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                BSurveyRequest brequest = new BSurveyRequest(username,dob,gender,height,weight,wristCir,listener);
                RequestQueue queue1 = Volley.newRequestQueue(BasicSurvey1.this);
                queue1.add(brequest);

            }
        });




    }


}
