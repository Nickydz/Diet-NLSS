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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Homescreen extends AppCompatActivity  {


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView tvbf1,tvSnack1,tvSnack2;
    TextView tvdat,tvbf2, tvbf3, tvbsn;
    TextView tvln1;
    TextView tvln2;
    TextView tvln3;
    TextView tvlsn;
    TextView tvdn1;
    TextView tvdn2;
    TextView tvdn3;
    TextView tvCalbf, tvCalln, tvCaldn, tvCalbsn, tvCallsn;
    DietContract.DietEntry de;
    DBHelper myDb;

    int advancedone;int bmrt;
    int snackneedfortextviewgeneration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        advancedone = pref.getInt("advancedone",0);
        de = new DietContract.DietEntry(pref.getString("username",""));
        myDb = new DBHelper(this,pref.getString("username",""));
        tvSnack1=(TextView) findViewById(R.id.tvSnack1);
        tvSnack2=(TextView) findViewById(R.id.tvSnack2);
        tvbf1 =(TextView) findViewById(R.id.textbf1);
        tvdat =(TextView) findViewById(R.id.tvdate);
        tvbf2 =(TextView) findViewById(R.id.textbf2);
        tvbf3 =(TextView) findViewById(R.id.textbf3);
        tvbsn =(TextView) findViewById(R.id.textbsn);
        tvln1 =(TextView) findViewById(R.id.textln1);
        tvln2 =(TextView) findViewById(R.id.textln2);
        tvln3 =(TextView) findViewById(R.id.textln3);
        tvlsn =(TextView) findViewById(R.id.textlsn);
        tvdn1 =(TextView) findViewById(R.id.textdn1);
        tvdn2 =(TextView) findViewById(R.id.textdn2);
        tvdn3 =(TextView) findViewById(R.id.textdn3);
        tvCalbf =(TextView) findViewById(R.id.tvCalbf);
        tvCalln =(TextView) findViewById(R.id.tvCalln);
        tvCaldn =(TextView) findViewById(R.id.tvCaldn);
        tvCalbsn =(TextView) findViewById(R.id.tvCalbsn);
        tvCallsn =(TextView) findViewById(R.id.tvCallsn);

        Intent intent = getIntent();

        bmrt = Integer.parseInt(intent.getStringExtra("bmr"));

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

        DietGen d2= new DietGen(pref.getString("username",""),myDb);
        final HashMap<String,String> breakfast,lunch,snack1,snack2,dinner;
        float bcal = 0.35f,scal=0.125f,dcal=0.15f,lcal=0.25f;
        if(advancedone == 1){
            int noofmeal = pref.getInt("noofmeal",5);
            String prommeal = pref.getString("prommeal","b");
            if(prommeal.equals("b")){
                bcal = 0.50f;
                lcal = 0.30f;
                dcal = 0.20f;
            }
            else if(prommeal.equals("l")){
                bcal= 0.30f;
                lcal= 0.50f;
                dcal= 0.20f;
            }
            else if(prommeal.equals("d")){
                bcal= 0.30f;
                lcal = 0.30f;
                dcal = 0.40f;
            }

            if(noofmeal == 5){

                snack1 = d2.snackGen(bmrt, scal);
                snack2 = d2.snackGen(bmrt, scal);

                bmrt = Math.round(bmrt - (2*scal*bmrt));
                breakfast = d2.breakfastGen(bmrt,bcal);
                lunch = d2.LunchGen(bmrt,lcal);
                dinner = d2.DinGen(bmrt,dcal);
            }
            else if(noofmeal == 4){
                snack1 = d2.snackGen(bmrt, scal);
                snack2 = d2.snackGen(bmrt, 0);
                bmrt = Math.round(bmrt - (scal*bmrt));
                breakfast = d2.breakfastGen(bmrt,bcal);
                lunch = d2.LunchGen(bmrt,lcal);
                dinner = d2.DinGen(bmrt,dcal);
            }
            else {
                snack1 = d2.snackGen(bmrt, 0);
                snack2 = d2.snackGen(bmrt, 0);
                breakfast = d2.breakfastGen(bmrt,bcal);
                lunch = d2.LunchGen(bmrt,lcal);
                dinner = d2.DinGen(bmrt,dcal);
            }

        }
        else {
            breakfast = d2.breakfastGen(bmrt,bcal);
            snack1 = d2.snackGen(bmrt,scal);
            lunch = d2.LunchGen(bmrt,lcal);
            snack2 = d2.snackGen(bmrt,scal);
            dinner = d2.DinGen(bmrt,dcal);
        }


        int breakfastcount = Integer.parseInt(breakfast.get("count"));
        String s1 = breakfast.get(0+"");
        for(int j=1;j<=breakfastcount;j++){
            s1 =s1 + ";"+ breakfast.get(j+"");
        }

        int lunchcount = Integer.parseInt(lunch.get("count"));
        String s2 = lunch.get(0+"");
        for(int j=1;j<=lunchcount;j++){
            s2 =s2 + ";"+ lunch.get(j+"");
        }

        int dinnercount = Integer.parseInt(dinner.get("count"));
        String s3 = dinner.get(0+"");
        for(int j=1;j<=dinnercount;j++){
            s3 =s3 + ";"+ dinner.get(j+"");
        }

        int s1count = Integer.parseInt(snack1.get("count"));
        int s2count = Integer.parseInt(snack2.get("count"));

        boolean hgghghg = myDb.insertUserDietData(de.DietTableName,date,breakfast.get("bf"),breakfast.get("bfcal"),s1,lunch.get("ln"),
                lunch.get("lncal"),s2,dinner.get("dn"),dinner.get("dncal"), s3,snack1.get("sn"),snack1.get("sncal"),
                snack1.get("snac"),snack2.get("sn"),snack2.get("sncal"),snack2.get("snac"),breakfastcount+"",lunchcount+"",
                dinnercount+"",s1count+"",s2count+"",1700+"");

        if(hgghghg) {
            Cursor res1 = myDb.getDietData(de.DietTableName, date);
            return res1;
        }
        else{
            Cursor res1 = myDb.getDietData(de.DietTableName, date);
            return res1;
        }

    }

    public void data(Cursor res){

        final HashMap<String,String> breakfast = new HashMap<String,String>(),lunch = new HashMap<String,String>(),snack1= new HashMap<String,String>(),snack2= new HashMap<String,String>(),dinner= new HashMap<String,String>();
        breakfast.put("bf",res.getString(2));
        breakfast.put("count",res.getString(17));
        breakfast.put("bfcal",res.getString(3));


        int breakfastcount = Integer.parseInt(breakfast.get("count"));
        String[] bfre = breakfast.get("bf").split(";");
        String[] bfres = res.getString(4).split(";");
        for(int j=0;j<=breakfastcount;j++){
            breakfast.put(j+"",bfres[j]);
        }

        lunch.put("ln",res.getString(5));
        lunch.put("lncal",res.getString(6));
        lunch.put("count",res.getString(18));

        int lunchcount = Integer.parseInt(lunch.get("count"));
        String[] lnre = lunch.get("ln").split(";");
        String[] lnres = res.getString(7).split(";");
        for(int j=0;j<=lunchcount;j++){
            lunch.put(j+"",lnres[j]);
        }

        dinner.put("dn",res.getString(8));
        dinner.put("dncal",res.getString(9));
        dinner.put("count",res.getString(19));

        int dinnercount = Integer.parseInt(dinner.get("count"));
        String[] dnre = dinner.get("dn").split(";");
        String[] dnres = res.getString(10).split(";");
        for (int j=0;j<=dinnercount;j++){
            dinner.put(j+"",dnres[j]);
        }

        snack1.put("sn",res.getString(11));
        snack1.put("sncal",res.getString(12));
        snack1.put("snac",res.getString(13));
        int snack1count = Integer.parseInt(res.getString(20));
        snack2.put("sn",res.getString(14));
        snack2.put("sncal",res.getString(15));
        snack2.put("snac",res.getString(16));

        int snack2count = Integer.parseInt(res.getString(21));

        if(breakfastcount == 2){

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

        }else if(breakfastcount==1){
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

        if(!(snack1.get("sn").equals("-") || snack1.get("sncal").equals("-"))) {

          if(snack1count == 0) {
              tvbsn.setText(snack1.get("sn"));
              tvbsn.setVisibility(View.VISIBLE);
              tvbsn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                      builder.setMessage(snack1.get("snac"))
                              .setNeutralButton("OK", null)
                              .create()
                              .show();
                  }
              });
              tvCalbsn.setText(snack1.get("sncal"));
              tvCalbsn.setVisibility(View.VISIBLE);
          }
          else{
              String[] snre = snack1.get("sn").split(";");
              final String[] snres = snack1.get("snac").split(";");
              for (int j=0;j<=snack1count;j++){
                  snack1.put(j+"",snres[j]);
              }
              RelativeLayout layout = (RelativeLayout)findViewById(R.id.activity_homescreen);
              tvbsn.setText(snre[0]);
              tvbsn.setVisibility(View.VISIBLE);
              tvbsn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                      builder.setMessage(snres[0])
                              .setNeutralButton("OK", null)
                              .create()
                              .show();
                  }
              });
              int prevTextViewId = tvbsn.getId();
              for(int j=1;j<=snack1count;j++){
                  final TextView textView = new TextView(this);
                  textView.setText(snre[j]);


                  int curTextViewId = prevTextViewId + 1;
                  textView.setId(curTextViewId);
                  final RelativeLayout.LayoutParams params =
                          new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
                                  RelativeLayout.LayoutParams.WRAP_CONTENT);

                  params.addRule(RelativeLayout.BELOW, prevTextViewId);
                  textView.setLayoutParams(params);
                  snackneedfortextviewgeneration = j;
                  textView.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                          builder.setMessage(snres[snackneedfortextviewgeneration])
                                  .setNeutralButton("OK", null)
                                  .create()
                                  .show();
                      }
                  });

                  prevTextViewId = curTextViewId;
                  layout.addView(textView, params);
              }

              tvCalbsn.setText(snack1.get("sncal"));
              tvCalbsn.setVisibility(View.VISIBLE);

          }
        }
        else {

            tvbsn.setVisibility(View.GONE);
            tvCalbsn.setVisibility(View.GONE);
            tvSnack1.setVisibility(View.GONE);
        }

        if(!(snack2.get("sn").equals("-") || snack2.get("sncal").equals("-"))){

            if(snack2count == 0) {
                tvlsn.setText(snack2.get("sn"));
                tvlsn.setVisibility(View.VISIBLE);
                tvlsn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                        builder.setMessage(snack2.get("snac"))
                                .setNeutralButton("OK", null)
                                .create()
                                .show();
                    }
                });
                tvCallsn.setText(snack2.get("sncal"));
                tvCallsn.setVisibility(View.VISIBLE);
            }
            else{
                String[] snre = snack2.get("sn").split(";");
                final String[] snres = snack2.get("snac").split(";");
                for (int j=0;j<=snack2count;j++){
                    snack2.put(j+"",snres[j]);
                }
                RelativeLayout layout = (RelativeLayout)findViewById(R.id.activity_homescreen);
                tvlsn.setText(snre[0]);
                tvlsn.setVisibility(View.VISIBLE);
                tvlsn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                        builder.setMessage(snres[0])
                                .setNeutralButton("OK", null)
                                .create()
                                .show();
                    }
                });
                int prevTextViewId = tvlsn.getId();
                for(int j=1;j<=snack1count;j++){
                    final TextView textView = new TextView(this);
                    textView.setText(snre[j]);


                    int curTextViewId = prevTextViewId + 1;
                    textView.setId(curTextViewId);
                    final RelativeLayout.LayoutParams params =
                            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
                                    RelativeLayout.LayoutParams.WRAP_CONTENT);

                    params.addRule(RelativeLayout.BELOW, prevTextViewId);
                    textView.setLayoutParams(params);
                    snackneedfortextviewgeneration = j;
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                            builder.setMessage(snres[snackneedfortextviewgeneration])
                                    .setNeutralButton("OK", null)
                                    .create()
                                    .show();
                        }
                    });

                    prevTextViewId = curTextViewId;
                    layout.addView(textView, params);
                }

                tvCallsn.setText(snack2.get("sncal"));
                tvCallsn.setVisibility(View.VISIBLE);
            }

        }
        else{
            tvlsn.setVisibility(View.GONE);
            tvCallsn.setVisibility(View.GONE);
            tvSnack2.setVisibility(View.GONE);
        }




        if(lunchcount == 2){

            tvln1.setText(lnre[0]);
            tvln2.setText(lnre[1]);
            tvln3.setText(lnre[2]);
            tvln1.setVisibility(View.VISIBLE);
            tvln2.setVisibility(View.VISIBLE);
            tvln3.setVisibility(View.VISIBLE);



            tvln1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(lunch.get(0+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
            tvln2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(lunch.get(1+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
            tvln3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(lunch.get(2+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });

        }else if(lunchcount==1){
            tvln1.setText(lnre[0]);
            tvln2.setText(lnre[1]);
            tvln1.setVisibility(View.VISIBLE);
            tvln2.setVisibility(View.VISIBLE);

            tvln1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(lunch.get(0+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
            tvln2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(lunch.get(1+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
        }else{
            tvln1.setText(lnre[0]);
            tvln1.setVisibility(View.VISIBLE);

            tvln1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(lunch.get(0+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
        }
        tvCalln.setText(lunch.get("lncal"));
        tvCalln.setVisibility(View.VISIBLE);


        if(dinnercount == 2){

            tvdn1.setText(dnre[0]);
            tvdn2.setText(dnre[1]);
            tvdn3.setText(dnre[2]);
            tvdn1.setVisibility(View.VISIBLE);
            tvdn2.setVisibility(View.VISIBLE);
            tvdn3.setVisibility(View.VISIBLE);



            tvdn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(dinner.get(0+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
            tvdn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(dinner.get(1+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
            tvdn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(dinner.get(2+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });

        }else if(dinnercount==1){
            tvdn1.setText(dnre[0]);
            tvdn2.setText(dnre[1]);
            tvdn1.setVisibility(View.VISIBLE);
            tvdn2.setVisibility(View.VISIBLE);

            tvdn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(dinner.get(0+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
            tvdn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(dinner.get(1+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
        }else{
            tvdn1.setText(dnre[0]);
            tvdn1.setVisibility(View.VISIBLE);

            tvdn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                    builder.setMessage(dinner.get(0+""))
                            .setNeutralButton("OK",null)
                            .create()
                            .show();
                }
            });
        }
        tvCaldn.setText(dinner.get("dncal"));
        tvCaldn.setVisibility(View.VISIBLE);




//
//        tvln.setText(lunch.get("name"));
//        tvln.setVisibility(View.VISIBLE);
//        tvln.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
//                builder.setMessage(lunch.get("snac"))
//                        .setNeutralButton("OK",null)
//                        .create()
//                        .show();
//            }
//        });
//        tvCalln.setText(lunch.get("cal"));
//
//        tvdn.setText(dinner.get("name"));
//        tvdn.setVisibility(View.VISIBLE);
//        tvdn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
//                builder.setMessage(dinner.get("snac"))
//                        .setNeutralButton("OK",null)
//                        .create()
//                        .show();
//            }
//        });
//        tvCaldn.setText(dinner.get("cal"));
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

            case R.id.advanceSurvey: i = new Intent(Homescreen.this, Advanced_Survey.class);
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
