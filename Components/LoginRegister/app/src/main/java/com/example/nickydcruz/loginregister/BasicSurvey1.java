package com.example.nickydcruz.loginregister;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BasicSurvey1 extends AppCompatActivity implements NumberPicker.OnValueChangeListener
  {

    public TextView tv;
    static Dialog d ;
    static String s ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_survey1);


        final FormulaClass f = new FormulaClass();

        final Intent intent = getIntent();

        final String username = intent.getStringExtra("username");
        final String dob = intent.getStringExtra("dob");
        final int age = f.calculateAge(dob);



        final RadioGroup radioSexGroup = (RadioGroup)findViewById(R.id.radgrp);

        final TextView etHeight = (TextView) findViewById(R.id.etht);
        tv = (TextView) findViewById(R.id.etht);
        final EditText etWeight = (EditText) findViewById(R.id.etWeight);
        final EditText etWristCir = (EditText) findViewById(R.id.etWristCir);
        final Button submit =(Button) findViewById(R.id.btSubmit);

        Button b = (Button) findViewById(R.id.button2);// on click of button display the dialog
        b.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String height = tv.getText().toString();
                final String weight =etWeight.getText().toString();
                final String wristCir =etWristCir.getText().toString();

                int wristcri = Integer.parseInt(wristCir);
                float wieght = Float.parseFloat(weight);
                float hieght = Float.parseFloat(height);


                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                RadioButton radioSexButton = (RadioButton) findViewById(selectedId);
                String gender;
                if(radioSexButton.getText().equals("Male"))
                    gender = "M";
                else
                    gender = "F";

                Toast.makeText(BasicSurvey1.this,gender, Toast.LENGTH_SHORT).show();
        final float BMI = f.bmi( hieght,wieght);
        final int BMR = f.bmr(gender,age,wieght,hieght*100);

                Response.Listener<String> listener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse1 = new JSONObject(response);
                            boolean success = jsonResponse1.getBoolean("success");
                            if(success){
                                Intent int1 = new Intent(BasicSurvey1.this,ResultPage.class);
                                int1.putExtra("BMR",BMR);
                                int1.putExtra("BMI",BMI);
                                int1.putExtra("age",age);
                                int1.putExtra("username",username);
                                BasicSurvey1.this.startActivity(int1);
                            }
                            else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                BSurveyRequest brequest = new BSurveyRequest(username,dob,gender,height,weight,wristCir,listener);
                RequestQueue queue1 = Volley.newRequestQueue(BasicSurvey1.this);
                queue1.add(brequest);

            }
        });




    }
      @Override
      public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

          Log.i("value is",""+newVal);

      }

//      public void button2 (View v) {
//
//          View v1 = getLayoutInflater().inflate(R.layout.dialog_numpick, null);
//
//
//          final NumberPicker picker = (NumberPicker) v1.findViewById(R.id.numpick_bs);
//          final NumberPicker picker1 = (NumberPicker) v1.findViewById(R.id.numpick2_bs);
//          picker.setMinValue(1);
//          picker.setMaxValue(20);
//          picker1.setMinValue(1);
//          picker1.setMaxValue(20);
//          picker.setWrapSelectorWheel(false);
//          picker1.setWrapSelectorWheel(false);
//
//          AlertDialog.Builder builder = new AlertDialog.Builder(this);
//          builder.setView(v1);
//          builder.setTitle("Cantidad");
//          builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//              public void onClick(DialogInterface dialog, int which) {
//                  int pickedValue = picker.getValue();
//                  int pickeValue = picker1.getValue();
//                  TextView textView = (TextView) findViewById(R.id.etht);
//                  textView.setText(Integer.parseInt(pickedValue+"."+pickeValue));
//                  return;
//              }
//          });
//          builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//              public void onClick(DialogInterface dialog, int which) {
//                  return;
//              }
//          });
//
//          AlertDialog dialog = builder.create();
//          dialog.show();
//      }

      public String show()
      {



          final Dialog d = new Dialog(BasicSurvey1.this);
          d.setTitle("NumberPicker");
          d.setContentView(R.layout.dialog_numpick);
          Button b1 = (Button) d.findViewById(R.id.btset);
          Button b2 = (Button) d.findViewById(R.id.btCan);
          final NumberPicker np = (NumberPicker) d.findViewById(R.id.numpick_bs);
          final NumberPicker np1 = (NumberPicker) d.findViewById(R.id.numpick2_bs);
          np1.setMaxValue(100);
          np1.setMinValue(0);
          np.setMaxValue(100); // max value 100
          np.setMinValue(0);   // min value 0
          np.setWrapSelectorWheel(false);
          np.setOnValueChangedListener(this);
          b1.setOnClickListener(new View.OnClickListener()
          {
              @Override
              public void onClick(View v) {
                  s = np.getValue()+"."+np1.getValue();
                  //tv.setText(s);
                  //tv.setText(String.valueOf(np.getValue()+"."+np1.getValue())); //set the value to textview
                  d.dismiss();
              }
          });
          b2.setOnClickListener(new View.OnClickListener()
          {
              @Override
              public void onClick(View v) {
                  d.dismiss(); // dismiss the dialog
              }
          });
          d.show();
          return s;


      }



}
