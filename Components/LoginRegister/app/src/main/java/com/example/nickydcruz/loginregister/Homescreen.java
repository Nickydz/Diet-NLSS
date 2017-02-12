package com.example.nickydcruz.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Homescreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        Spinner spn=(Spinner) findViewById(R.id.spinb);

        spn.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Black Tea");
        categories.add("Green Tea");
        categories.add("Milk");
        categories.add("Coffee, Black, 1 Sugar");
        categories.add("Tea, Whole Milk, 1 Sugar");
        categories.add("Tea, Whole Milk");
        categories.add("Tea, Semi Skimmed Milk, 1 Sugar");



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(dataAdapter);




    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent i = getIntent();
        final DietGen d = new DietGen();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(i.getStringExtra("diet"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String s = d.dietDivider(jsonObject);
        String[] diet = s.split(";");
        ActionBar actionBar =getSupportActionBar();
        actionBar.setLogo(R.mipmap.nlss_crop);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        TextView tvbf =(TextView) findViewById(R.id.textbf);
        TextView tvbsn =(TextView) findViewById(R.id.textbsn);
        TextView tvln =(TextView) findViewById(R.id.textln);
        TextView tvlsn =(TextView) findViewById(R.id.textlsn);
        TextView tvdn =(TextView) findViewById(R.id.textdn);
        TextView tvCalbf =(TextView) findViewById(R.id.tvCalbf);
        TextView tvCalln =(TextView) findViewById(R.id.tvCalln);
        TextView tvCaldn =(TextView) findViewById(R.id.tvCaldn);
        TextView tvCalbsn =(TextView) findViewById(R.id.tvCalbsn);
        TextView tvCallsn =(TextView) findViewById(R.id.tvCallsn);

        tvbf.setText(diet[0]);
        tvCalbf.setText(diet[1]);
        tvln.setText(diet[2]);
        tvCalln.setText(diet[3]);
        tvdn.setText(diet[4]);
        tvCaldn.setText(diet[5]);
        tvbsn.setText(diet[6]);
        tvCalbsn.setText(diet[7]);
        tvlsn.setText(diet[8]);
        tvCallsn.setText(diet[9]);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_homescreen_actions,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(Homescreen.this,Homescreen.class);
        switch (item.getItemId()) {
            case R.id.foodcravings: i = new Intent(Homescreen.this, FoodCravings.class);
                break;

            case R.id.superfood: i = new Intent(Homescreen.this, superfood_main.class);
                break;

            case R.id.excal: i = new Intent(Homescreen.this, ExerciseCalculator.class);
                break;

            case R.id.diet: i = new Intent(Homescreen.this, Homescreen.class);
                break;
            case R.id.logout: {
                pref.edit().clear().commit();
                i = new Intent(getApplicationContext(), LoginActivity.class);
                break;
            }

        }
        Homescreen.this.startActivity(i);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        TextView tvCalt = (TextView) findViewById(R.id.tvCalt);
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        if (item.equals("Black Tea")) tvCalt.setText("1");
        else if(item.equals("Tea, Whole Milk, 1 Sugar")) tvCalt.setText("43");
        else if (item.equals("Tea, Whole Milk")) tvCalt.setText("19");
        else if (item.equals("Tea, Semi Skimmed Milk, 1 Sugar")) tvCalt.setText("37");
        else if (item.equals("Green Tea")) tvCalt.setText("0");
        else if (item.equals("Milk")) tvCalt.setText("103");
        else if (item.equals("Coffee, Black, 1 Sugar")) tvCalt.setText("32");


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
