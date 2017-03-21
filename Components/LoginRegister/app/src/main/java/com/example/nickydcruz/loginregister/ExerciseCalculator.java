package com.example.nickydcruz.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ExerciseCalculator extends AppCompatActivity {
    private EditText e1,e2;
    SharedPreferences pref;
    TextView calv;
    TextView totalcal;
    private Button calculate,confirm;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayList1;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter1;
    AutoCompleteTextView autoCompleteTextView;
    private EditText txtInput;
    float num1 = 0.0f;
    float num2 = 0.0f;
    float num3 = 0.0f;
    float num4 = 0.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);

        //AutoComplete textView
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.activity);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        autoCompleteTextView.setAdapter(ad);
        init();
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String res = (String)parent.getItemAtPosition(position);
                String[] ser = res.split(":");
                float ser1= Float.parseFloat(ser[1]);
                num1 = ser1;
            }
        });


        //ListView
        ListView listView = (ListView)findViewById(R.id.listv);
        String[] items = {};
        arrayList = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,arrayList);
        listView.setAdapter(adapter);


        final List<String> list = new ArrayList<String>();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String a = (String) parent.getItemAtPosition(position);
                String[] ser = a.split("calories burned - ");
                float ser1 = Float.parseFloat(ser[1]);
                num2 = ser1;
                String b = totalcal.getText().toString();
                float c = Float.parseFloat(b);
                num3 = c;
                num4 = num3 - num2;
                arrayList.remove(position);
                list.remove(position);
                adapter.notifyDataSetChanged();

                Toast.makeText(ExerciseCalculator.this, "Activity has been removed from your list", Toast.LENGTH_SHORT).show();
                String s = new Float(num4).toString();
                totalcal.setText(s);
                return false;
            }
        });



        txtInput = (EditText)findViewById(R.id.activity);
        Button btAdd = (Button)findViewById(R.id.btadd);
        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (txtInput.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Activity cannot be Blank", Toast.LENGTH_LONG).show();
                    txtInput.setError("Activity cannot be Blank");
                    return;
                }else if (e2.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Weight cannot be Blank", Toast.LENGTH_LONG).show();
                    e2.setError("Weight cannot be Blank");
                    return;
                } else if (e1.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Duration cannot be blank", Toast.LENGTH_LONG).show();
                    e1.setError("Duration cannot be blank");
                    return;
                }  else if (calv.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "calories burned cannot be 0", Toast.LENGTH_LONG).show();
                    calv.setError("Click on the Calculate button");
                    return;
                } else {
                    String newItem = txtInput.getText().toString();
                    String cb = calv.getText().toString();


                    arrayList.add(newItem + "   " + "calories burned" + " - " + cb);
                    //  arrayList1.add(cb);
                    adapter.notifyDataSetChanged();
                    list.add(cb);
                    float sum = 0;
                    for(int i=0 ;i<list.size();i++) {
                        sum += Float.parseFloat(list.get(i));

                    }

                    String s = String.format("%.2f", sum);

                    totalcal.setText("" + s);
                    //  adapter1.notifyDataSetChanged();
                    txtInput.setText(null);
                    e2.setText(null);
                    calv.setText(null);
                    e1.setText(null);
                }
            }

        });


    }
    private void init() {
        calculate = (Button) findViewById(R.id.calc);
        // e1 = (EditText) findViewById(R.id.editText);
        totalcal = (TextView) findViewById(R.id.total);
        confirm =(Button) findViewById(R.id.btconfrm);
        e1 = (EditText) findViewById(R.id.Time);
        e2 = (EditText) findViewById(R.id.weight);
        e2.setText(pref.getString("weight","0"));
        calv = (TextView) findViewById(R.id.calorie);
        // e4 = (EditText) findViewById(R.id.editText4);
        //e5 = (EditText) findViewById(R.id.editText5);
        // OnClickListener btnListener = new OnClickListener();
        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (txtInput.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Activity cannot be Blank", Toast.LENGTH_LONG).show();
                    txtInput.setError("Activity cannot be Blank");
                    return;
                }else if (e2.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Weight cannot be Blank", Toast.LENGTH_LONG).show();
                    e2.setError("Weight cannot be Blank");
                    return;
                } else if(e1.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Duration cannot be blank", Toast.LENGTH_LONG).show();
                    e1.setError("Duration cannot be blank");
                    return;
                }
                else {
                    float num2 = Float.parseFloat(e2.getText().toString());
                    float num3 = Float.parseFloat(e1.getText().toString());
                    float mul = (float) ((num1 * 3.5 * num2) / 200);
                    float mul1 = num3 * mul;
                    String result = String.format("%.2f", mul);
                    String result1 = String.format("%.2f", mul1);
                    calv.setText("" + result1);
                }

            }

        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                Calendar cal = Calendar.getInstance();
                editor.putString("calday",cal.get(Calendar.YEAR) + "-"
                        + (cal.get(Calendar.MONTH)+1)
                        + "-" + cal.get(Calendar.DAY_OF_MONTH));
                editor.putInt("ExerciseCalc",Math.round(Float.parseFloat(totalcal.getText().toString())));

                Intent i =new Intent(getApplicationContext(),ResultPage.class);
                startActivity(i);

            }
        });
    }


}

     /*   if (e2.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Weight cannot be Blank", Toast.LENGTH_LONG).show();
            e2.setError("Weight cannot be Blank");
            return;
        } else if (e3.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Activity duration cannot be Blank", Toast.LENGTH_LONG).show();
            e3.setError("Activity duration cannot be Blank");
            return;
        } else {
            // float num1 = Float.parseFloat(e1.getText().toString());
            float num2 = Float.parseFloat(e2.getText().toString());
            float num3 = Float.parseFloat(e3.getText().toString());
            float mul = (float) ((num1 * 3.5 * num2)/200);
            mul1 = num3 * mul;
            String result = String.format("%.2f", mul);
            String result1 = String.format("%.2f", mul1);
            e4.setText("" + result);
            e5.setText("" + result1);


        }

    }
    */


