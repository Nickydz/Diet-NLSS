package com.example.nlss.custom_diet;

import android.util.SparseArray;

/**
 * Created by Leo on 16-02-2017.
 */

public class DBGen {

    private String username;
    DBaseHelper d ;
    DetailContract.Users d1 ;
    public DBGen(String username, DBaseHelper myDb) {
        this.username = username;
        d=myDb;
        d1 = new DetailContract.Users(username);
    }

    public void breakfastGen(SparseArray<String> breakfast, int i) {

        for (int j=0;j<i;j++){
            boolean sis = d.insertData(d1.foodDatabase,breakfast.get(j++),breakfast.get(j++),breakfast.get(j++),breakfast.get(j++),breakfast.get(j++),breakfast.get(j));
        }

    }
}
