package com.example.nickydcruz.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Leo on 27-08-2017.
 */

public class Help extends AppCompatActivity {
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_homescreen_actions,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(getApplicationContext(),Homescreen.class);
        switch (item.getItemId()) {
            case R.id.foodcravings: i = new Intent(getApplicationContext(), FoodCravings.class);
                break;

            case R.id.superfood: i = new Intent(getApplicationContext(), superfood_main.class);
                break;

            case R.id.excal: i = new Intent(getApplicationContext(), ExerciseCalculator.class);
                break;

            case R.id.diet: i = new Intent(getApplicationContext(), Homescreen.class);
                break;

            case R.id.advanceSurvey: i = new Intent(getApplicationContext(), Advanced_Survey.class);
                break;

            case R.id.activity_update_wtht: i = new Intent(getApplicationContext(), Update_wtht.class);
                break;

            case R.id.activity_help: i = new Intent(getApplicationContext(), Help.class);
                break;

            case R.id.Graphs: i = new Intent(getApplicationContext(), Graphs.class);
                break;

            case R.id.logout: {
                pref.edit().clear().commit();
                i = new Intent(getApplicationContext(), LoginActivity.class);
                break;
            }

        }
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }
}
