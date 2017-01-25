package com.example.nickydcruz.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        TextView tvCategory =(TextView)findViewById(R.id.tvCategory);
        TextView tvBmr =(TextView)findViewById(R.id.tvBmr);
        TextView tvExperts = (TextView)findViewById(R.id.tvExpertsMessage);
        TextView tvIdealWeight = (TextView)findViewById(R.id.tvIdealweiht);
        Button btLW =(Button)findViewById(R.id.btLW);
        Button btMW =(Button)findViewById(R.id.btMW);
        Button btGW =(Button)findViewById(R.id.btGW);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final int age = Integer.parseInt(intent.getStringExtra("age"));
        int bmi = Integer.parseInt(intent.getStringExtra("BMI"));
        final int bmr = Integer.parseInt(intent.getStringExtra("BMR"));
        final FormulaClass f = new FormulaClass();
        String category = f.category(bmi);
        String expertsMessage = "";
        tvCategory.setText(category);
        tvBmr.setText("Your Basal metabolic rate (BMR) is " + bmr);

        if(bmi<18.5) {
            expertsMessage = "Since your weight is lower than ideal we recommend you increase your weight.";
            btGW.setVisibility(View.VISIBLE);
            btGW.setClickable(true);

            if (bmi < 18.5 && bmi > 17) {
                expertsMessage = expertsMessage + "Alternatively .You could also maintain the same weight";
                btMW.setVisibility(View.VISIBLE);
                btMW.setClickable(true);
            }
        }
        else if(bmi >=18.5 && bmi < 25){
            expertsMessage = "Your weight is in the ideal range we recommend you maintain your weight. Alternatively you can also choose a diet to gain or loose your weight";
            btLW.setVisibility(View.VISIBLE);
            btMW.setVisibility(View.VISIBLE);
            btGW.setVisibility(View.VISIBLE);
            btLW.setClickable(true);
            btGW.setClickable(true);
            btMW.setClickable(true);
        }

        else if(bmi >=25 ) {
            expertsMessage = "Since your weight is more than ideal we recommend you decrease your weight.";
            btLW.setVisibility(View.VISIBLE);
            btLW.setClickable(true);
            if (bmi >= 25 && bmi < 26) {
                expertsMessage = expertsMessage + "Alternatively .You could also maintain the same weight";
                btMW.setVisibility(View.VISIBLE);
                btMW.setClickable(true);
            }
        }
        btGW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bmr1 = bmr + 500;
                int a[] = f.bmrcal(bmr1*0.25);
                int b[] = f.bmrcal(bmr1*0.125);
                Intent int1=new Intent(ResultPage.this,Homescreen.class);
                int1.putExtra("username",username);
                int1.putExtra("age",age);
                startActivity(int1);
            }
        });

        btLW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bmr1=bmr;
                if(bmr > 1700)
                bmr1 =bmr -500;
                int a[] = f.bmrcal(bmr1*0.25);
                int b[] = f.bmrcal(bmr1*0.125);

                Intent int1=new Intent(ResultPage.this,Homescreen.class);
                int1.putExtra("username",username);
                int1.putExtra("age",age);
                startActivity(int1);
            }
        });

        btMW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a[] = f.bmrcal(bmr*0.25);
                int b[] = f.bmrcal(bmr*0.125);

                Intent int1=new Intent(ResultPage.this,Homescreen.class);
                int1.putExtra("username",username);
                int1.putExtra("age",age);
                startActivity(int1);

            }
        });

        tvExperts.setText(expertsMessage);


    }
}
