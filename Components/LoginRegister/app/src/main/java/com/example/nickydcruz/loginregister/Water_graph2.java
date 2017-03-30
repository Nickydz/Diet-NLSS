package com.example.nickydcruz.loginregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class Water_graph2 extends AppCompatActivity {
    float calculated;
    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_graph2);
        gifImageView = (GifImageView)findViewById(R.id.gifwater);
        Button otf = (Button) findViewById(R.id.bt_otf);
        Button osf = (Button) findViewById(R.id.bt_osf);
        Button ttf = (Button) findViewById(R.id.bt_ttf);
        final SharedPreferences sharedPreferences = getSharedPreferences("sp",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("identifier",1);
        editor.commit();

        otf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculated = sharedPreferences.getFloat("calculated",0)+125f;
                editor.putFloat("calculated",calculated);
                editor.commit();

                //Set GIFImageView resource
                try{
                    InputStream inputStream = getAssets().open("waterglasstwo.gif");
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    gifImageView.setBytes(bytes);
                    gifImageView.startAnimation();
                }
                catch (IOException ex)
                {

                }


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent s = new Intent(Water_graph2.this,Water_graph.class);
//                        Bundle bundleone = new Bundle();
//                        bundleone.putString("stuffone", Float.toString(calculated));
                        s.putExtra("calculatedvalue",calculated);

//                        s.putExtra("stuffone", calculated);
                        startActivity(s);
                    }
                },1500); // 1500 = 1.5seconds
            }
        });

        osf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculated = sharedPreferences.getFloat("calculated",0)+175f;
                editor.putFloat("calculated",calculated);
                editor.commit();
                //Set GIFImageView resource
                try{
                    InputStream inputStream = getAssets().open("waterglasstwo.gif");
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    gifImageView.setBytes(bytes);
                    gifImageView.startAnimation();
                }
                catch (IOException ex)
                {

                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent t = new Intent(Water_graph2.this,Water_graph.class);
//                        Bundle bundletwo = new Bundle();
//                        bundletwo.putString("stuffone", Float.toString(calculated));
                        t.putExtra("calculatedvalue",calculated);
                        startActivity(t);
                    }
                },1500); // 1500 = 1.5seconds
            }
        });

        ttf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                calculated = 325f;
                calculated = sharedPreferences.getFloat("calculated",0)+325f;
                editor.putFloat("calculated",calculated);
                editor.commit();

                //Set GIFImageView resource
                try{
                    InputStream inputStream = getAssets().open("waterglasstwo.gif");
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    gifImageView.setBytes(bytes);
                    gifImageView.startAnimation();
                }
                catch (IOException ex)
                {

                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent q = new Intent(Water_graph2.this,Water_graph.class);
//                        Bundle bundlethree = new Bundle();
//                        bundlethree.putString("stuffone", Float.toString(calculated));
                        q.putExtra("calculatedvalue",calculated);
                        startActivity(q);
                    }
                },1500); // 1500 = 1.5seconds
            }
        });
    }
}
