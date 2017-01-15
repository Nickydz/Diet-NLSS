package com.example.nickydcruz.loginregister;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword =(EditText) findViewById(R.id.etPassword);
        final EditText etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        final EditText etDOB = (EditText) findViewById(R.id.etDOB);
        final Button btRegister = (Button) findViewById(R.id.btRegister);



        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                monthOfYear++;
                etDOB.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
            }

        };

        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Register.this, date,2000 , 0,
                        1).show();
            }
        });


        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username =etUsername.getText().toString();
                final String email =etEmail.getText().toString();
                final String password =etPassword.getText().toString();
                final String confirmpassword =etConfirmPassword.getText().toString();
                final String dob = etDOB.getText().toString();
                if(confirmpassword.equals(password)){
                Response.Listener<String> listner = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                            if(response.equals("?Successfully Registered")){
                                Intent i = new Intent(Register.this,LoginActivity.class);
                                Register.this.startActivity(i);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage("Registration failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }


                    }

                };
                    RegisterRequest registerRequest = new RegisterRequest(username,email,password,dob, listner);
                    RequestQueue queue = Volley.newRequestQueue(Register.this);
                    queue.add(registerRequest);

                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setMessage("Passwords Don't Match")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();

                }


            }
        });
     }
}
