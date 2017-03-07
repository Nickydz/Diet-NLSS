package com.example.nickydcruz.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Advanced_Survey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced__survey);
        Spinner spnprommeal = (Spinner) findViewById(R.id.spnprommeal);
        Spinner spnofmeal = (Spinner) findViewById(R.id.spnofmeal);

        List<String> prommeal = new ArrayList<>();
        prommeal.add("Breakfast");
        prommeal.add("Lunch");
        prommeal.add("Dinner");

        List<String> nofmeal = new ArrayList<>();
        nofmeal.add("Breakfast");
        nofmeal.add("Lunch");
        nofmeal.add("Dinner");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, prommeal);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnprommeal.setAdapter(dataAdapter1);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, nofmeal);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnofmeal.setAdapter(dataAdapter2);

        spnofmeal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(Advanced_Survey.this,item,Toast.LENGTH_LONG);
            }
        });

        spnofmeal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(Advanced_Survey.this,item,Toast.LENGTH_LONG);
            }
        });


    }
}
