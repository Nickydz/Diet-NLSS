package com.example.nickydcruz.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Advanced_Survey extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    DBHelper myDb;
    String prommeal,prefdrink;
    int noofmeals;
    float exerciselimit =1.1f;
    String activitylevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced__survey);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        editor = pref.edit();
        Spinner spnprommeal = (Spinner) findViewById(R.id.spnprommeal);
        Spinner spnofmeal = (Spinner) findViewById(R.id.spnofmeal);
        Spinner spdrink =(Spinner) findViewById(R.id.spndrink);
        Spinner spnexercise = (Spinner) findViewById(R.id.spnexercise);
        Button btasubmit = (Button) findViewById(R.id.btasubmit);


        myDb = new DBHelper(this,pref.getString("username",""));

        ArrayAdapter<CharSequence> dataAdapter1 = ArrayAdapter.createFromResource(this,R.array.prommeal,android.R.layout.simple_spinner_item);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnprommeal.setAdapter(dataAdapter1);

        ArrayAdapter<CharSequence> dataAdapter2 = ArrayAdapter.createFromResource(this,R.array.noofmeal,android.R.layout.simple_spinner_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnofmeal.setAdapter(dataAdapter2);

        ArrayAdapter<CharSequence> dataAdapter3 = ArrayAdapter.createFromResource(this,R.array.exercise,android.R.layout.simple_spinner_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnexercise.setAdapter(dataAdapter3);

        String[] cols=new String[]{"Name"};
        int[] lbls= new int[]{android.R.id.text1};
        Cursor mycursor = myDb.getDrinks();
        SimpleCursorAdapter sca=new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, mycursor, cols,lbls,0);
        sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdrink.setAdapter(sca);


        spnprommeal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = parent.getItemAtPosition(position).toString().trim();
                    switch (item)
                    {
                        case "Breakfast":
                            prommeal = "b";
                            break;
                        case "Lunch":
                            prommeal = "l";
                            break;
                        case "Dinner":
                            prommeal = "d";
                            break;
                    }
                Toast.makeText(getApplicationContext(),prommeal,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnofmeal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString().trim();
                switch (item)
                {
                    case "3 Main Meals":
                        noofmeals = 3;
                        break;
                    case "3 Main Meals, 1 Snack":
                        noofmeals = 4;
                        break;
                    case "3 Main Meals, 2 Snacks":
                        noofmeals =5;
                        break;
                }

                Toast.makeText(getApplicationContext(),noofmeals+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spdrink.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cursor ada = (Cursor)parent.getItemAtPosition(position);
                prefdrink = ada.getString(1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spnexercise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                activitylevel = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btasubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> listener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse1 = new JSONObject(response);
                            boolean success = jsonResponse1.getBoolean("success");
                            if (success) {

                                editor.putString("prommeal",prommeal);
                                editor.putInt("noofmeal",noofmeals);
                                editor.putString("prefdrink",prefdrink);
                                editor.putString("exerciselevel",exerciselimit+"");
                                editor.putInt("advancedone",1);
                                editor.commit();

                                Intent int1 = new Intent(getApplicationContext(), ResultPage.class);
                                Advanced_Survey.this.startActivity(int1);
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                ASurveyRequest brequest = new ASurveyRequest(pref.getString("username",""), pref.getString("height",""),prommeal,noofmeals+"",prefdrink,activitylevel,listener);
                RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());
                queue1.add(brequest);

            }
        });

    }
}
