package com.example.nickydcruz.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatProperty;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class Water_graph extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences pr;
    float waterltr;
    static float calculated;
    private static String TAG = "MainActivity";

    private float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
    private String[] xData = {"Mitch", "Jessica" , "Mohammad" , "Kelsey", "Sam", "Robert", "Ashley"};
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_graph);
        Log.d(TAG, "onCreate: starting to create chart");
        pref = getSharedPreferences("sp", Context.MODE_PRIVATE);
        pr = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        pieChart = (PieChart) findViewById(R.id.water_chart);
        String wt = pr.getString("weight",60+"");
        Float weight = Float.parseFloat(wt);
        weight =weight*2.2046f;
        float wateroz = weight*0.5f;
        wateroz = wateroz*0.02957f;
        wateroz = wateroz*1000;


//        waterltr = wateroz/33.814f;
        waterltr = wateroz;
        waterltr = waterltr -(0.2f*waterltr);
//        SharedPreferences sharedPreferences = getSharedPreferences("sp",MODE_PRIVATE);




        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(65f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Daily water intake");
        pieChart.setCenterTextSize(15);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Intent m = new Intent(Water_graph.this,Water_graph2.class);
                startActivity(m);
                finish();
//                Log.d(TAG, "onValueSelected: Value select from chart.");
//                Log.d(TAG, "onValueSelected: " + e.toString());
//                Log.d(TAG, "onValueSelected: " + h.toString());
//
//                int pos1 = e.toString().indexOf("(sum): ");
//                String sales = e.toString().substring(pos1 + 7);
//
//                for(int i = 0; i < yData.length; i++){
//                    if(yData[i] == Float.parseFloat(sales)){
//                        pos1 = i;
//                        break;
//                    }
//                }
//                String employee = xData[pos1 + 1];
//                Toast.makeText(Water_graph.this, "Employee " + employee + "\n" + "Sales: $" + sales + "K", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        int identify = pref.getInt("identifier",0);

        if(identify == 1) {
            Intent intent = getIntent();
            Float stuffone = 0f;
            Float stufftwo = 0f;
            Float stuffthree = 0f;
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("identifier",0);
            editor.commit();
            calculated = pref.getFloat("calculated",0);
            //Get the bundle
//            Bundle bundle = getIntent().getExtras();
//            stuffone = Float.parseFloat(bundle.getString("stuffone"));
//            stuffone = intent.getFloatExtra("calculatedvalue",0.0f);
//            stufftwo = Float.parseFloat(bundle.getString("stufftwo"));
//            stuffthree = Float.parseFloat(bundle.getString("stuffthree"));

//            calculated = calculated + stuffone;
  //          editor.putFloat("calculated",calculated);
//            editor.commit();
            //calculated = bundle.getInt("stuff");

        }
        else{

        }

    }
    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        calculated = pref.getFloat("calculated",0);
        ArrayList<Integer> colors = new ArrayList<>();
        if(waterltr>calculated){
            yEntrys.add(new PieEntry(waterltr-calculated, 1));
            colors.add(Color.RED);

        }
        else {
            yEntrys.add(new PieEntry(calculated - waterltr, 1));
            colors.add(Color.BLUE);

        }

        yEntrys.add(new PieEntry(calculated,2));


        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "intake Water ");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
//        colors.add(Color.GRAY);
//        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
//        colors.add(Color.CYAN);
//        colors.add(Color.YELLOW);
//        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
