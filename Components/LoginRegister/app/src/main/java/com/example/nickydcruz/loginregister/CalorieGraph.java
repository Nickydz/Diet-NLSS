package com.example.nickydcruz.loginregister;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class CalorieGraph extends AppCompatActivity {

    SharedPreferences pref;
    PieChart pieChart;
    private static String TAG = "MainActivity";
    private String[] xData = {"Mitch", "Jessica" , "Mohammad" , "Kelsey", "Sam", "Robert", "Ashley"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_graph);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        pieChart = (PieChart) findViewById(R.id.calorieGraph);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(65f);
        pieChart.setHoleRadius(65f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Calorie Chart for the day");
        pieChart.setCenterTextSize(15);

        addDataSet();



    }

    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        int noofmeal = pref.getInt("noofmeal",5);
        String prommeal = pref.getString("prommeal","b");
        float bcal,lcal,dcal;
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
        else{
            bcal = 0.50f;
            lcal = 0.30f;
            dcal = 0.20f;
        }
        float scal =0.125f;
        int bmrt = pref.getInt("amr",1700);
        ArrayList<Integer> colors = new ArrayList<>();
        if(noofmeal == 5){

            float snack1 = bmrt*scal;
            float snack2 = bmrt*scal;


            bmrt = Math.round(bmrt - (2*scal*bmrt));
            float breakfast = bmrt * bcal;
            float lunch = bmrt*lcal;
            float dinner = bmrt*dcal;
            xEntrys.add("Breakfast");
            yEntrys.add(new PieEntry(breakfast, 1));
            xEntrys.add("Brunch");
            yEntrys.add(new PieEntry(snack1, 2));
            xEntrys.add("Lunch");
            yEntrys.add(new PieEntry(lunch, 3));
            xEntrys.add("Evening Snack");
            yEntrys.add(new PieEntry(snack2, 4));
            xEntrys.add("Dinner");
            yEntrys.add(new PieEntry(dinner, 5));


            colors.add(Color.GRAY);
            colors.add(Color.BLUE);
            colors.add(Color.RED);
            colors.add(Color.GREEN);
            colors.add(Color.YELLOW);
//        colors.add(Color.CYAn);
//        colors.add(Color.MAGENTA);


        }
        else if(noofmeal == 4){
            float snack1 = bmrt*scal;
            bmrt = Math.round(bmrt - (scal*bmrt));
            float breakfast = bmrt * bcal;
            float lunch = bmrt*lcal;
            float dinner = bmrt*dcal;
            xEntrys.add("Breakfast");
            yEntrys.add(new PieEntry(breakfast, 1));
            xEntrys.add("Lunch");
            yEntrys.add(new PieEntry(lunch, 2));
            xEntrys.add("Evening Snack");
            yEntrys.add(new PieEntry(snack1, 3));
            xEntrys.add("Dinner");
            yEntrys.add(new PieEntry(dinner, 4));

            colors.add(Color.GRAY);
            colors.add(Color.BLUE);
            colors.add(Color.RED);
            colors.add(Color.GREEN);
//            colors.add(Color.YELLOW);
//        colors.add(Color.CYAn);
//        colors.add(Color.MAGENTA);

        }
        else {

            float breakfast = bmrt * bcal;
            float lunch = bmrt*lcal;
            float dinner = bmrt*dcal;
            xEntrys.add("Breakfast");
            yEntrys.add(new PieEntry(breakfast, 1));
            xEntrys.add("Lunch");
            yEntrys.add(new PieEntry(lunch, 2));
            xEntrys.add("Dinner");
            yEntrys.add(new PieEntry(dinner, 3));
            colors.add(Color.GRAY);
            colors.add(Color.BLUE);
            colors.add(Color.RED);
//            colors.add(Color.GREEN);
//            colors.add(Color.YELLOW);
//        colors.add(Color.CYAn);
//        colors.add(Color.MAGENTA);

        }





        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Calorie Distribution");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);



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
