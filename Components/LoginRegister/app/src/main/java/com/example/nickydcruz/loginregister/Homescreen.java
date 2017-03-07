package com.example.nickydcruz.loginregister;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;

public class Homescreen extends AppCompatActivity  {


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView tvbf1;
    TextView tvdat;
    TextView tvbf2;
    TextView tvbf3;
    TextView tvbsn;
    TextView tvln;
    TextView tvlsn;
    TextView tvdn;
    TextView tvCalbf;
    TextView tvCalln;
    TextView tvCaldn;
    TextView tvCalbsn;
    TextView tvCallsn;
    DietContract.DietEntry de;
    DBHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);

        de = new DietContract.DietEntry(pref.getString("username",""));
        myDb = new DBHelper(this,pref.getString("username",""));
        /*
        float height = Float.parseFloat(pref.getString("height","0"));
        float weight = Float.parseFloat(pref.getString("weight","0"));
        int age = pref.getInt("age",0);
        String gender = pref.getString("gender","");
        */
        tvbf1 =(TextView) findViewById(R.id.textbf1);
        tvdat =(TextView) findViewById(R.id.tvdate);
        tvbf2 =(TextView) findViewById(R.id.textbf2);
        tvbf3 =(TextView) findViewById(R.id.textbf3);
        tvbsn =(TextView) findViewById(R.id.textbsn);
        tvln =(TextView) findViewById(R.id.textln);
        tvlsn =(TextView) findViewById(R.id.textlsn);
        tvdn =(TextView) findViewById(R.id.textdn);
        tvCalbf =(TextView) findViewById(R.id.tvCalbf);
        tvCalln =(TextView) findViewById(R.id.tvCalln);
        tvCaldn =(TextView) findViewById(R.id.tvCaldn);
        tvCalbsn =(TextView) findViewById(R.id.tvCalbsn);
        tvCallsn =(TextView) findViewById(R.id.tvCallsn);


        ActionBar actionBar =getSupportActionBar();
        actionBar.setLogo(R.mipmap.nlss_crop);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        final Calendar c = Calendar.getInstance();
        final String sDate = c.get(Calendar.YEAR) + "-"
                + (c.get(Calendar.MONTH)+1)
                + "-" + c.get(Calendar.DAY_OF_MONTH);
        tvdat.setText(c.get(Calendar.DAY_OF_MONTH) + "-"
                + (c.get(Calendar.MONTH)+1)
                + "-" + c.get(Calendar.YEAR));


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                monthOfYear++;
                String sDat = year+"-"+monthOfYear+"-"+dayOfMonth;
                tvdat.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
                Cursor resd = myDb.getDietData(de.DietTableName,sDat);

                if(resd.moveToFirst()){
                    data(resd);
                }
                else{
                    data(insertDiet(sDat));
                }
            }

        };



        tvdat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datepic = new DatePickerDialog(Homescreen.this, date,c.get(Calendar.YEAR) ,c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datepic.getDatePicker().setMaxDate(c.getTimeInMillis()+(1000*60*60*24*7));//for 7 days ahead
                datepic.show();
            }
        });

        Cursor resd = myDb.getDietData(de.DietTableName,sDate);

        if(resd.moveToFirst()){
            data(resd);
        }
        else{
            data(insertDiet(sDate));
        }


    }

    private Cursor insertDiet(String date) {
        int bmr =new FormulaClass().bmr(pref.getString("gender",""),pref.getInt("age",0),Float.parseFloat(pref.getString("weight","0")),Float.parseFloat(pref.getString("height","0")));
        DietGen d2= new DietGen(pref.getString("username",""),myDb);
        final HashMap<String,String> breakfast,lunch,snack1,snack2,dinner;
        breakfast = d2.breakfastGen(bmr);
        snack1 = d2.snackGen(bmr);
        lunch = d2.LunchGen(bmr);
        snack2 = d2.snackGen(bmr);
        dinner = d2.DinGen(bmr);
        int i = Integer.parseInt(breakfast.get("count"));
        String s1 = breakfast.get(0+"");
        for(int j=1;j<=i;j++){
            s1 =s1 + ";"+ breakfast.get(j+"");
        }

        myDb.insertUserDietData(de.DietTableName,date,breakfast.get("bf"),breakfast.get("bfcal"),s1,lunch.get("name"),lunch.get("cal"),lunch.get("snac"),dinner.get("name"),dinner.get("cal"), dinner.get("snac"),snack1.get("name"),snack1.get("cal"),snack1.get("snac"),snack2.get("name"),snack2.get("cal"),snack2.get("snac"),i+"",1700+"");
        Cursor res1 = myDb.getDietData(de.DietTableName,date);
        return res1;

    }

    public void data(Cursor res){

        final HashMap<String,String> breakfast = new HashMap<String,String>(),lunch = new HashMap<String,String>(),snack1= new HashMap<String,String>(),snack2= new HashMap<String,String>(),dinner= new HashMap<String,String>();
        breakfast.put("bf",res.getString(2));
        breakfast.put("count",res.getString(17));
        breakfast.put("bfcal",res.getString(3));
        int i = Integer.parseInt(breakfast.get("count"));
        String[] bfre = breakfast.get("bf").split(";");
        String[] bfres = res.getString(4).split(";");
        for(int j=0;j<=i;j++){
            breakfast.put(j+"",bfres[j]);
        }
        lunch.put("name",res.getString(5));
        lunch.put("cal",res.getString(6));
        lunch.put("snac",res.getString(7));
        dinner.put("name",res.getString(8));
        dinner.put("cal",res.getString(9));
        dinner.put("snac",res.getString(10));
        snack1.put("name",res.getString(11));
        snack1.put("cal",res.getString(12));
        snack1.put("snac",res.getString(13));
        snack2.put("name",res.getString(14));
        snack2.put("cal",res.getString(15));
        snack2.put("snac",res.getString(16));

        if(i == 2){

            tvbf1.setText(bfre[0]);
            tvbf2.setText(bfre[1]);
            tvbf3.setText(bfre[2]);
            tvbf1.setVisibility(View.VISIBLE);
            tvbf2.setVisibility(View.VISIBLE);
            tvbf3.setVisibility(View.VISIBLE);





            tvbf1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(breakfast.get(0+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
            tvbf2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(breakfast.get(1+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
            tvbf3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(breakfast.get(2+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });

        }else if(i==1){
            tvbf1.setText(bfre[0]);
            tvbf2.setText(bfre[1]);
            tvbf1.setVisibility(View.VISIBLE);
            tvbf2.setVisibility(View.VISIBLE);

            tvbf1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(breakfast.get(0+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
            tvbf2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(breakfast.get(1+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
        }else{
            tvbf1.setText(bfre[0]);
            tvbf1.setVisibility(View.VISIBLE);

            tvbf1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(breakfast.get(0+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
        }
        tvCalbf.setText(breakfast.get("bfcal"));
        tvCalbf.setVisibility(View.VISIBLE);

        tvbsn.setText(snack1.get("name"));
        tvbsn.setVisibility(View.VISIBLE);
        tvbsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                builder.setMessage(snack1.get("snac"))
                        .setNeutralButton("OK",null)
                        .create()
                        .show();
            }
        });
        tvCalbsn.setText(snack1.get("cal"));


        tvlsn.setText(snack2.get("name"));
        tvlsn.setVisibility(View.VISIBLE);
        tvlsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                builder.setMessage(snack2.get("snac"))
                        .setNeutralButton("OK",null)
                        .create()
                        .show();
            }
        });
        tvCallsn.setText(snack2.get("cal"));



        tvln.setText(lunch.get("name"));
        tvln.setVisibility(View.VISIBLE);
        tvln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                builder.setMessage(lunch.get("snac"))
                        .setNeutralButton("OK",null)
                        .create()
                        .show();
            }
        });
        tvCalln.setText(lunch.get("cal"));

        tvdn.setText(dinner.get("name"));
        tvdn.setVisibility(View.VISIBLE);
        tvdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                builder.setMessage(dinner.get("snac"))
                        .setNeutralButton("OK",null)
                        .create()
                        .show();
            }
        });
        tvCaldn.setText(dinner.get("cal"));
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
/*
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
    */
}
