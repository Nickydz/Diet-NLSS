package com.example.nickydcruz.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Graphs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        Button btWtgrsph = (Button) findViewById(R.id.WeightGraph);
        Button btWrgrsph = (Button) findViewById(R.id.WaterGraph);
        Button btCalgrsph = (Button) findViewById(R.id.CalorieDistribution);

        btCalgrsph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CalorieGraph.class);
                startActivity(i);
            }
        });

        btWtgrsph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Graphbyleo.class);
                startActivity(i);
            }
        });

        btWrgrsph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Water_graph.class);
                startActivity(i);
            }
        });

    }
}
