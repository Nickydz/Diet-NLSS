package com.example.nickydcruz.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Update_wtht extends AppCompatActivity {
    SharedPreferences pref;
    String updatedwt;
    String updatedht;
    String updatedage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_wtht);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        final EditText upwt = (EditText) findViewById(R.id.etupwt);
        final EditText upht = (EditText) findViewById(R.id.etupht);
        final EditText upage = (EditText) findViewById(R.id.etupage);
        Button updatewt = (Button) findViewById(R.id.btupwt);
        Button updateht = (Button) findViewById(R.id.btupht);
        Button updateage = (Button) findViewById(R.id.btupage);


        String weight = pref.getString("weight","0");
        final String username = pref.getString("username","");

        //FOR UPDATING WEIGHT//

        updatewt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedwt = upwt.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
//                                for (int i = 0; i < key; i = i + 6)
//                                    Mydb.insertData(jsonResponse.getString(i + ""), jsonResponse.getString((i + 1) + ""), jsonResponse.getString((i + 2) + ""), jsonResponse.getString((i + 3) + ""), jsonResponse.getString((i + 4) + ""), jsonResponse.getString((i + 5) + ""));

                                Toast.makeText(Update_wtht.this, "Success", Toast.LENGTH_LONG).show();
//                                int i=1000;
                            } else {
                                Toast.makeText(Update_wtht.this, "Error", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                Update_wtRequest update_wtRequest = new Update_wtRequest(username,updatedwt,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Update_wtht.this);
                queue.add(update_wtRequest);
            }
        });

        //FOR UPDATING HEIGHT//

        updateht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedht = upht.getText().toString();
                //Float b = 0.3048f * Float.parseFloat(a);
                //updatedht = Float.toString(b);

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
//                                for (int i = 0; i < key; i = i + 6)
//                                    Mydb.insertData(jsonResponse.getString(i + ""), jsonResponse.getString((i + 1) + ""), jsonResponse.getString((i + 2) + ""), jsonResponse.getString((i + 3) + ""), jsonResponse.getString((i + 4) + ""), jsonResponse.getString((i + 5) + ""));

                                Toast.makeText(Update_wtht.this, "Success", Toast.LENGTH_LONG).show();
//                                int i=1000;
                            } else {
                                Toast.makeText(Update_wtht.this, "Error", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                Update_htRequest update_htRequest = new Update_htRequest(username,updatedht,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Update_wtht.this);
                queue.add(update_htRequest);

            }
        });

        updateage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedage = upage.getText().toString();
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
        Intent i = new Intent(Update_wtht.this,Homescreen.class);
        switch (item.getItemId()) {
            case R.id.foodcravings: i = new Intent(Update_wtht.this, FoodCravings.class);
                break;

            case R.id.superfood: i = new Intent(Update_wtht.this, superfood_main.class);
                break;

            case R.id.excal: i = new Intent(Update_wtht.this, ExerciseCalculator.class);
                break;

            case R.id.diet: i = new Intent(Update_wtht.this, Homescreen.class);
                break;

            case R.id.advanceSurvey: i = new Intent(Update_wtht.this, Advanced_Survey.class);
                break;

            case R.id.logout: {
                pref.edit().clear().commit();
                i = new Intent(getApplicationContext(), LoginActivity.class);
                break;
            }

        }
        Update_wtht.this.startActivity(i);
        return super.onOptionsItemSelected(item);
    }
}
