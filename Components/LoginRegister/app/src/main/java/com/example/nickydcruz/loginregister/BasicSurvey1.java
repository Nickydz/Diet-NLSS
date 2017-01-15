package com.example.nickydcruz.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BasicSurvey1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_survey1);
<<<<<<< HEAD

        final FormulaClass f = new FormulaClass();

        final Intent intent = getIntent();

        final String username = intent.getStringExtra("username");
        final String dob = intent.getStringExtra("dob");
        final int age = f.calculateAge(dob);


        Toast.makeText(BasicSurvey1.this,
                username + "" + age, Toast.LENGTH_SHORT).show();

        radioSexGroup = (RadioGroup)findViewById(R.id.rGender);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etWristCir = (EditText) findViewById(R.id.etWristCir);
        submit =(Button) findViewById(R.id.btSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String height =etHeight.getText().toString();
                final String weight =etWeight.getText().toString();
                final String wristCir =etWristCir.getText().toString();

                int wristcri = Integer.parseInt(wristCir);
                float wieght = Float.parseFloat(weight);
                float hieght = Float.parseFloat(height);


                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(selectedId);
                String gender;
                if(radioSexButton.getText().equals("Male"))
                    gender = "M";
                else
                    gender = "F";

        final float BMI = f.bmi( hieght,wieght);
        final double BMR = f.bmr(gender,age,wieght,hieght);

                Response.Listener<String> listener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse1 = new JSONObject(response);
                            boolean success = jsonResponse1.getBoolean("success");
                            if(success){
                                Intent int1 = new Intent(BasicSurvey1.this,Homescreen.class);
                                int1.putExtra("BMR",BMR);
                                int1.putExtra("BMI",BMI);
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




=======
>>>>>>> 36ed686132fcd11a349e18f0ce59867558f6d8e9
    }
}
