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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class ExerciseCalculator extends AppCompatActivity implements OnClickListener {

    private EditText e1, e2, e3, e4, e5;
    private Button calculate;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_calculator);

        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);

            // Spinner element
            Spinner mySpinner = (Spinner) findViewById(R.id.spinner);


            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ExerciseCalculator.this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mySpinner.setAdapter(myAdapter);
            init();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_homescreen_actions,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(ExerciseCalculator.this,Homescreen.class);
        switch (item.getItemId()) {
            case R.id.foodcravings: i = new Intent(ExerciseCalculator.this, FoodCravings.class);
                break;

            case R.id.superfood: i = new Intent(ExerciseCalculator.this, superfood_main.class);
                break;

            case R.id.excal: i = new Intent(ExerciseCalculator.this, ExerciseCalculator.class);
                break;

            case R.id.diet: i = new Intent(ExerciseCalculator.this, Homescreen.class);
                break;
            case R.id.logout: {
                pref.edit().clear().commit();
                i = new Intent(ExerciseCalculator.this, LoginActivity.class);
                break;
            }

        }
        ExerciseCalculator.this.startActivity(i);
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        calculate = (Button) findViewById(R.id.button);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        // OnClickListener btnListener = new OnClickListener();
        calculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (e1.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "MET value cannot be Blank", Toast.LENGTH_LONG).show();
            e1.setError("MET value cannot be Blank");
            return;
        } else if (e2.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Weight cannot be Blank", Toast.LENGTH_LONG).show();
            e2.setError("Weight cannot be Blank");
            return;
        } else if (e3.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Activity duration cannot be Blank", Toast.LENGTH_LONG).show();
            e3.setError("Activity duration cannot be Blank");
            return;
        } else {
            float num1 = Float.parseFloat(e1.getText().toString());
            float num2 = Float.parseFloat(e2.getText().toString());
            float num3 = Float.parseFloat(e3.getText().toString());
            float mul = (float) (0.175 * num1 * num2);
            float mul1 = num3 * mul;
            e4.setText("" + mul);
            e5.setText("" + mul1);


        }


    }

}



