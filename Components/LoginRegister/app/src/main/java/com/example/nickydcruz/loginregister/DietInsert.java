package com.example.nickydcruz.loginregister;

import android.content.Context;
import android.util.SparseArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Nicky D. Cruz on 2/2/2017.
 */

public class DietInsert {

    private String username;
    DBHelper d ;
    DietContract.DietEntry d1 ;
    public DietInsert(String username, DBHelper myDb) {
        this.username = username;
        d=myDb;
        d1 = new DietContract.DietEntry(username);
    }

    public void breakfastGen(SparseArray<String> breakfast, int i) {
        d.truncate(d1.BFTableName);
        for (int j=0;j<i;j++){
            boolean sis = d.insertData(d1.BFTableName,breakfast.get(j++),breakfast.get(j++),breakfast.get(j++),breakfast.get(j++),breakfast.get(j++),breakfast.get(j));
        }
        
    }

    public void lunchGen(SparseArray<String> lunch, int i) {
        d.truncate(d1.LNTableName);
        for (int j=0;j<i;j++){
            d.insertData(d1.LNTableName,lunch.get(j++),lunch.get(j++),lunch.get(j++),lunch.get(j++),lunch.get(j++),lunch.get(j));
        }
        /*Random r =new Random();
        int  r1= r.nextInt(i);
        return lunch.get(r1)+";"+lunch.get(r1+1000);*/
    }

    public void dinnerGen(SparseArray<String> dinner, int i) {
        d.truncate(d1.DNTableName);
        for (int j=0;j<i;j++){
            d.insertData(d1.DNTableName,dinner.get(j++),dinner.get(j++),dinner.get(j++),dinner.get(j++),dinner.get(j++),dinner.get(j));
        }
        /*Random r =new Random();
        int  r1= r.nextInt(i);
        return lunch.get(r1)+";"+lunch.get(r1+1000);*/
    }

    public void snacksGen(SparseArray<String> snacks, int i) {
        d.truncate(d1.SNTableName);
        for (int j=0;j<i;j++){
            d.insertData(d1.SNTableName,snacks.get(j++),snacks.get(j++),snacks.get(j++),snacks.get(j++),snacks.get(j),"");
        }
        /*Random r =new Random();
        int  r1= r.nextInt(i);
        return lunch.get(r1)+";"+lunch.get(r1+1000);*/
    }

    public void dietDivider(JSONObject jsonObject) {
        SparseArray<String> breakfast = new SparseArray<>();
        SparseArray<String> lunch = new SparseArray<>();
        SparseArray<String> dinner = new SparseArray<>();
        SparseArray<String> snacks = new SparseArray<>();
        int i,j,u=0;
        String s;
        for (i = 0,j=0; j<1000;i++,j++) {
            try {

                if (jsonObject.getString(i + "").equals("endbf")) {
                    break;
                } else {

                    breakfast.put(j++, jsonObject.getString(i++ + ""));
                    u = j +100;
                    breakfast.put(j++,jsonObject.getString(i++ + ""));
                    breakfast.put(j++,jsonObject.getString(i++ + ""));
                    breakfast.put(j++,jsonObject.getString(i++ + ""));
                    breakfast.put(j++,jsonObject.getString(i++ + ""));
                    breakfast.put(j,jsonObject.getString(i + ""));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        breakfastGen(breakfast,j);
        i++;
        u=0;
        for ( j = 0; j<1000; i++, j++) {
            try {
                if (jsonObject.getString(i + "").equals("endln")) {
                    break;
                } else {
                    lunch.put(j++, jsonObject.getString(i++ + ""));
                    lunch.put(j++, jsonObject.getString(i++ + ""));
                    lunch.put(j++, jsonObject.getString(i++ + ""));
                    lunch.put(j++, jsonObject.getString(i++ + ""));
                    lunch.put(j++, jsonObject.getString(i++ + ""));
                    u = j +1000;
                    lunch.put(j,jsonObject.getString(i+""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        i++;

        //s = s + ";" +
        lunchGen(lunch,j);
        u = 0;
        for (j = 0; j<1000; i++, j++) {
            try {
                if (jsonObject.getString(i + "").equals("enddn")) {
                    break;
                } else {
                    dinner.put(j++, jsonObject.getString(i++ + ""));
                    dinner.put(j++, jsonObject.getString(i++ + ""));
                    dinner.put(j++, jsonObject.getString(i++ + ""));
                    dinner.put(j++, jsonObject.getString(i++ + ""));
                    dinner.put(j++, jsonObject.getString(i++ + ""));
                    u = j +1000;
                    dinner.put(j,jsonObject.getString(i + ""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        i++;
        //s = s + ";" +
        dinnerGen(dinner,j);
        u=0;
        for (j = 0; j<1000; i++, j++) {
            try {
                if (jsonObject.getString(i + "").equals("endsn")) {
                    break;
                } else {
                    snacks.put(j++, jsonObject.getString(i++ + ""));
                    snacks.put(j++, jsonObject.getString(i++ + ""));
                    snacks.put(j++, jsonObject.getString(i++ + ""));
                    snacks.put(j++, jsonObject.getString(i++ + ""));
                    u = j +1000;
                    snacks.put(j,jsonObject.getString(i+""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //s = s + ";"+ snacksGen(snacks,j);
        //s = s + ";"+
        snacksGen(snacks,j);

        //return "a;b;c;d;e;f;g;h;i;j;k;l;m;n;o;p";
    }
}
