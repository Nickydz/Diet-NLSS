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


        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String dob = intent.getStringExtra("dob");

        tvUsername.setText(username);
        tvDob.setText(dob);


    }
}
