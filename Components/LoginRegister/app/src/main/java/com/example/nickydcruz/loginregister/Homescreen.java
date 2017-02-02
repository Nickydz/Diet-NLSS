package com.example.nickydcruz.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Homescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        Intent i = getIntent();
        final DietGen d = new DietGen();
        JSONObject jsonObject = new JSONObject();
        TextView tvbf =(TextView) findViewById(R.id.textbf);
        TextView tvbsn =(TextView) findViewById(R.id.textbsn);
        TextView tvln =(TextView) findViewById(R.id.textln);
        TextView tvlsn =(TextView) findViewById(R.id.textlsn);
        TextView tvdn =(TextView) findViewById(R.id.textdn);

        try {
            jsonObject = new JSONObject(i.getStringExtra("diet"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String s = d.dietDivider(jsonObject);
        String[] diet = s.split(";");

        tvbf.setText(diet[0]);
        tvln.setText(diet[1]);
        tvdn.setText(diet[2]);
        tvbsn.setText(diet[3]);
        tvlsn.setText(diet[3]);






    }
}
