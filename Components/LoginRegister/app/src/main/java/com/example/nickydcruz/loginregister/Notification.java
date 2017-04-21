package com.example.nickydcruz.loginregister;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Notification extends AppCompatActivity {
    TextView brText;
    TextView snText;
    TextView lnText;
    TextView snText1;
    TextView dnText;

    Calendar c = Calendar.getInstance();
    Calendar cb = Calendar.getInstance();
    Calendar cl = Calendar.getInstance();
    Calendar cd = Calendar.getInstance();
    Calendar cs1 = Calendar.getInstance();
    Calendar cs2 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        brText = (TextView) findViewById(R.id.brText);
        snText = (TextView) findViewById(R.id.snText);
        lnText = (TextView) findViewById(R.id.lnText);
        snText1 = (TextView) findViewById(R.id.snText1);
        dnText = (TextView) findViewById(R.id.dnText);
        Button brButton = (Button) findViewById(R.id.brButton);
        Button snButton = (Button) findViewById(R.id.snButton);
        Button lnButton = (Button) findViewById(R.id.lnButton);
        Button snButton1 = (Button) findViewById(R.id.snButton1);
        Button doneButton1 = (Button) findViewById(R.id.Done);
        TextView sn = (TextView) findViewById(R.id.sn);
        TextView sn1 = (TextView) findViewById(R.id.sn1);

        Button dnButton = (Button) findViewById(R.id.dnButton);
        SharedPreferences pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        if(pref.getInt("noofmeals",5)==3){
            snText.setVisibility(View.GONE);
            snText1.setVisibility(View.GONE);
            snButton.setVisibility(View.GONE);
            snButton1.setVisibility(View.GONE);
            sn.setVisibility(View.GONE);
            sn1.setVisibility(View.GONE);
        }
        else if(pref.getInt("noofmeals",5)==4){
            snText1.setVisibility(View.GONE);
            snButton1.setVisibility(View.GONE);
            sn1.setVisibility(View.GONE);
        }
        doneButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iiop = new Intent(getApplicationContext(),Homescreen.class);
                startActivity(iiop);
                finish();
            }
        });
        setCurrentDateOnbrView();
        setCurrentDateOndnView();
        setCurrentDateOnlnView();
        setCurrentDateOnsn1View();
        setCurrentDateOnsnView();
        brText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(Notification.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int min) {
                        c.set(Calendar.HOUR_OF_DAY, hour);
                        c.set(Calendar.MINUTE, min);
                        setCurrentDateOnbrView();
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
            }
        });

//Snack
        snText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(Notification.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int min) {
                        c.set(Calendar.HOUR_OF_DAY, hour);
                        c.set(Calendar.MINUTE, min);
                        setCurrentDateOnsnView();
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
            }
        });

        //Lunch
        lnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(Notification.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int min) {
                        c.set(Calendar.HOUR_OF_DAY, hour);
                        c.set(Calendar.MINUTE, min);
                        setCurrentDateOnlnView();
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
            }
        });

        //Snack1
        snText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(Notification.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int min) {
                        c.set(Calendar.HOUR_OF_DAY, hour);
                        c.set(Calendar.MINUTE, min);
                        setCurrentDateOnsn1View();
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
            }
        });

        //Dinner
        dnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(Notification.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int min) {
                        c.set(Calendar.HOUR_OF_DAY, hour);
                        c.set(Calendar.MINUTE, min);
                        setCurrentDateOndnView();
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
            }
        });

        brButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY,12);
//                calendar.set(Calendar.MINUTE,8);
//                String timeFormat = "hh:mm a";
//                SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
//                try {
//                    stf.parse(brText.getText().toString());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Calendar calendar = stf.getCalendar();
                cb.set(Calendar.SECOND,0);
                cb.set(Calendar.MILLISECOND,0);
                Intent intent = new Intent(getApplicationContext(),NotificationReciever.class);
                intent.putExtra("id",109);
                intent.putExtra("msg","Time for Breakfast");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),109,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cb.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            }
        });


        snButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY,12);
//                calendar.set(Calendar.MINUTE,8);
//                String timeFormat = "hh:mm a";
//                SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
//                try {
//                    stf.parse(snText.getText().toString());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Calendar calendar = stf.getCalendar();
                cs1.set(Calendar.SECOND,0);
                cs1.set(Calendar.MILLISECOND,0);
                Intent intent = new Intent(getApplicationContext(),NotificationReciever.class);
                intent.putExtra("id",115);
                intent.putExtra("msg","Time for Snacks");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),115,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cs1.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            }
        });


        snButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY,12);
//                calendar.set(Calendar.MINUTE,8);
//                String timeFormat = "hh:mm a";
//                SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
//                try {
//                    stf.parse(snText1.getText().toString());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Calendar calendar = stf.getCalendar();
                cs2.set(Calendar.SECOND,0);
                cs2.set(Calendar.MILLISECOND,0);
                Intent intent = new Intent(getApplicationContext(),NotificationReciever.class);
                intent.putExtra("id",114);
                intent.putExtra("msg","Time for Snacks");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),114,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cs2.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            }
        });


        lnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY,12);
//                calendar.set(Calendar.MINUTE,8);
//                String timeFormat = "hh:mm a";
//                SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
//                try {
//                    stf.parse(lnText.getText().toString());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                cl.set(Calendar.SECOND,0);
                cl.set(Calendar.MILLISECOND,0);
                Intent intent = new Intent(getApplicationContext(),NotificationReciever.class);
                intent.putExtra("id",113);
                intent.putExtra("msg","Time for Lunch");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),113,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cl.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            }
        });


        dnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY,12);
//                calendar.set(Calendar.MINUTE,8);
                String timeFormat = "hh:mm a";
                SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
                try {
                    stf.parse(dnText.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar calendar = stf.getCalendar();
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
                Intent intent = new Intent(getApplicationContext(),NotificationReciever.class);
                intent.putExtra("id",112);
                intent.putExtra("msg","Time for Dinner");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),112,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cd.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            }
        });



    }


//
//    TimePickerDialog.OnTimeSetListener time =

//    public void timeOnClick(View view){
//
//    }
    public void setCurrentDateOnbrView(){
        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
        brText.setText(stf.format(c.getTime()));
        cb=Calendar.getInstance();
    }

    public void setCurrentDateOnlnView(){
        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
        lnText.setText(stf.format(c.getTime()));
        cl=Calendar.getInstance();
    }
    public void setCurrentDateOnsnView(){
        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
        snText.setText(stf.format(c.getTime()));
        cs1=Calendar.getInstance();
    }
    public void setCurrentDateOnsn1View(){
        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
        snText1.setText(stf.format(c.getTime()));
        cs2=Calendar.getInstance();
    }
    public void setCurrentDateOndnView(){
        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
        dnText.setText(stf.format(c.getTime()));
        cd=Calendar.getInstance();
    }


}
