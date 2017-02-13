package com.example.nlss.custom_diet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Centre extends Activity {

    private AutoCompleteTextView bf;
    private AutoCompleteTextView ln;
    private AutoCompleteTextView dn;
    private AutoCompleteTextView sn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre);

        bf = (AutoCompleteTextView) findViewById(R.id.bfac);
        ln = (AutoCompleteTextView) findViewById(R.id.lnac);
        dn = (AutoCompleteTextView) findViewById(R.id.dnac);
        sn = (AutoCompleteTextView) findViewById(R.id.snac);

        String[] foo1 = getResources().getStringArray(R.array.foods);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,foo1);
        bf.setAdapter(adapter1);

        String[] foo2 = getResources().getStringArray(R.array.foods);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,foo2);
        ln.setAdapter(adapter2);

        String[] foo3 = getResources().getStringArray(R.array.foods);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,foo3);
        dn.setAdapter(adapter3);

        String[] foo4 = getResources().getStringArray(R.array.foods);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,foo4);
        sn.setAdapter(adapter4);
    }
}
