package com.example.venussteve.notify;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import java.text.SimpleDateFormat;
import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity {

    private EditText edtTime;
    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String res = edtTime.getText().toString();
                String[] ser = res.split(":");
                int ser1= Integer.parseInt(ser[0]);
                int ser2= Integer.parseInt(ser[1]);
                c.set(Calendar.HOUR_OF_DAY, ser1);
                c.set(Calendar.MINUTE, ser2);
                */


                c.set(Calendar.HOUR_OF_DAY, 1);
                c.set(Calendar.MINUTE, 48);

                Intent intent = new Intent(getApplicationContext(),Notification_reciever.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            }
        });

    }

private void initializeView(){
   edtTime = (EditText) findViewById(R.id.edtTime);
    setCurrentDateOnView();
}

TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(TimePicker view, int hour, int min) {
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, min);
        setCurrentDateOnView();
    }
};
public void timeOnClick(View view){
    new TimePickerDialog(MainActivity.this,time, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false).show();
}

public void setCurrentDateOnView(){
    String timeFormat = "hh:mm a";
    SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
    edtTime.setText(stf.format(c.getTime()));
}

}

