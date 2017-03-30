package com.example.nickydcruz.loginregister;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Graphbyleo extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphbyleo);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        GraphView graph = (GraphView)findViewById(R.id.weightgraph);
        DBHelper myDb = new DBHelper(this,pref.getString("username",""));

        HashMap arr = myDb.graphdatawt();
        int count = Integer.parseInt(arr.get("Count").toString());
        ArrayList<Date> date =new ArrayList<>();
        int i=0;
        for(i=0;i<count;i++){
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = null;
            try {
                d1 = (Date)formatter.parse(arr.get("Date"+i).toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            date.add(d1);
        }

//        DataPoint[] ggsgsgsgsgss= new DataPoint[];
//        for(int i=0;i<count;i++){
//            ggsgsgsgsgss[i]=new DataPoint(date.get(i),arr.get("weight"+i));
//        }

        // you can directly pass Date objects to DataPoint-Constructor
        // this will convert the Date to double via Date#getTime()
        series = new LineGraphSeries<DataPoint>();
        for(int j=0;j<count;j++){
            series.appendData(new DataPoint(date.get(j),Double.parseDouble(arr.get("Weight"+j).toString())),true,count);

        }
        graph.addSeries(series);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(graph.getContext()));
        graph.getViewport().setMinX(date.get(0).getTime());
        if(i>5) {
            graph.getViewport().setMaxX(date.get(i-1).getTime());
        }
        else{
            Calendar c = Calendar.getInstance();
            c.setTime(date.get(0));
            c.add(Calendar.DAY_OF_YEAR,5);
            Date d121345= new Date(); d121345.setTime(c.getTimeInMillis());
            graph.getViewport().setMaxX(d121345.getTime());
        }
    }
}
