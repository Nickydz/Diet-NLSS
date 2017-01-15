package com.example.nickydcruz.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Homescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);


        final TextView tvUsername = (TextView) findViewById(R.id.tvUsername);

        final TextView tvDob = (TextView) findViewById(R.id.tvDob);
        final TextView tvbmi = (TextView) findViewById(R.id.tvbmi);
        final TextView tvbmr = (TextView) findViewById(R.id.tvbmr);


        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String age = intent.getStringExtra("age");
        String bmi = intent.getStringExtra("BMI");
        String bmr = intent.getStringExtra("BMR");
        tvUsername.setText(username);
        tvDob.setText(age);
        tvbmi.setText(bmi);
        tvbmr.setText(bmr);


    }
}
