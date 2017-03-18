//package com.example.nickydcruz.loginregister;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.github.mikephil.charting.charts.PieChart;
//import com.github.mikephil.charting.components.Legend;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.PieData;
//import com.github.mikephil.charting.data.PieDataSet;
//import com.github.mikephil.charting.data.PieEntry;
//import com.github.mikephil.charting.highlight.Highlight;
//import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
//
//import java.util.ArrayList;
//
///**
// * Created by Venus Steve on 17-03-2017.
// */
//
//public class Piechart_Calories extends AppCompatActivity{
//
//    private static String TAG = "Piechart_Calories";
//
//    private float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
//    private String[] xData = {"Mitch", "Jessica" , "Mohammad" , "Kelsey", "Sam", "Robert", "Ashley"};
//    PieChart pieChart;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.piechart_calorie);
//        Log.d(TAG, "onCreate: starting to create chart");
//
//        pieChart = (PieChart) findViewById(R.id.idPieChart);
//
//        pieChart.setDescription("Sales by employee (In Thousands $)");
//        pieChart.setRotationEnabled(true);
//        pieChart.setHoleRadius(25f);
//        pieChart.setTransparentCircleAlpha(0);
//        pieChart.setCenterText("Daily Calories");
//        pieChart.setCenterTextSize(10);
//        //  pieChart.setDrawEntryLabels(true);
//
//        addDataSet();
//
//        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
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
//                Toast.makeText(Piechart_Calories.this, "Employee " + employee + "\n" + "Sales: $" + sales + "K", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });
//    }
//       private  void addDataSet(){
//           Log.d(TAG, "addDataSet started");
//           ArrayList<PieEntry> yEntry = new ArrayList<>();
//           ArrayList<String> xEntry = new ArrayList<>();
//
//           for(int i = 0; i < yData.length; i++){
//               yEntry.add(new PieEntry(yData[i] , i));
//           }
//
//           for(int i = 1; i < xData.length; i++){
//               xEntry.add(xData[i]);
//           }
//
//           //create the data set
//           PieDataSet pieDataSet = new PieDataSet(yEntry, "Employee Sales");
//           pieDataSet.setSliceSpace(2);
//           pieDataSet.setValueTextSize(12);
//
//           //add colors to dataset
//           ArrayList<Integer> colors = new ArrayList<>();
//           colors.add(Color.GRAY);
//           colors.add(Color.BLUE);
//           colors.add(Color.RED);
//           colors.add(Color.GREEN);
//           colors.add(Color.CYAN);
//           colors.add(Color.YELLOW);
//           colors.add(Color.MAGENTA);
//
//           pieDataSet.setColors(colors);
//
//           //add legend to chart
//           Legend legend = pieChart.getLegend();
//           legend.setForm(Legend.LegendForm.CIRCLE);
//           legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
//
//           //create pie data object
//           PieData pieData = new PieData(pieDataSet);
//           pieChart.setData(pieData);
//           pieChart.invalidate();
//
//
//
//    }
//
//}
