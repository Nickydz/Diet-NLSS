package com.example.nickydcruz.loginregister;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.regex.Pattern;

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
                new DatePickerDialog(Register.this, date,2000 , 0,1).show();
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
                boolean ev = isValidEmaillId(etEmail.getText().toString().trim());
                if (!(username.equals("")|| password.equals("")||dob.equals(""))) {
                    if (confirmpassword.equals(password) && ev) {
                        Response.Listener<String> listner = new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {

                                if (response.contains("Successfully Registered")) {
                                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Register.this, BasicSurvey1.class);
                                    i.putExtra("username",username);
                                    i.putExtra("dob",dob+"");
                                    int age = new FormulaClass().calculateAge(dob);

                                    //Using Shared Preferences to maintain session
                                    SharedPreferences pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
                                    SharedPreferences .Editor editor = pref.edit();
                                    editor.putString("username",username);
                                    editor.putString("password",password);
                                    editor.putInt("age",age);
                                    editor.putString("cf","0");
                                    editor.apply();

                                    Register.this.startActivity(i);
                                    Register.this.finish();


                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                    builder.setMessage("Registration failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }


                            }

                        };
                        RegisterRequest registerRequest = new RegisterRequest(username, email, password, dob, listner);
                        RequestQueue queue = Volley.newRequestQueue(Register.this);
                        queue.add(registerRequest);

                    } else if (!ev) {
                        Toast.makeText(getApplicationContext(), "Invalid Email Address.", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                        builder.setMessage("Passwords Don't Match")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();

                    }
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setMessage("Fields are Empty")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }

            }
        });
     }
    private boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}
