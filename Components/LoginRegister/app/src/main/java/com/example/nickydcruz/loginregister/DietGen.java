package com.example.nickydcruz.loginregister;

import android.util.SparseArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Nicky D. Cruz on 2/2/2017.
 */

public class DietGen {

    public String breakfastGen(SparseArray<String> breakfast, int i) {
        Random r =new Random();
        int  r1= r.nextInt(i);
        return breakfast.get(r1)+";"+breakfast.get(r1+100);
    }

    public String lunchGen(SparseArray<String> lunch, int i) {
        Random r =new Random();
        int  r1= r.nextInt(i);
        return lunch.get(r1)+";"+lunch.get(r1+1000);
    }

    public String dinnerGen(SparseArray<String> dinner, int i) {
        Random r =new Random();
        int  r1= r.nextInt(i);
        return dinner.get(r1)+";"+dinner.get(r1+1000);
    }

    public String snacksGen(SparseArray<String> snacks, int i) {
        Random r =new Random();
        int  r1= r.nextInt(i);
        return snacks.get(r1)+";"+snacks.get(r1+1000);
    }

    public String dietDivider(JSONObject jsonObject) {
        SparseArray<String> breakfast = new SparseArray<>();
        SparseArray<String> lunch = new SparseArray<>();
        SparseArray<String> dinner = new SparseArray<>();
        SparseArray<String> snacks = new SparseArray<>();
        int i,j,u=0;
        String s;
        for (i = 0,j=0; true;i++,j++) {
            try {

                if (jsonObject.getString(i + "").equals("endbf")) {
                    break;
                } else {

                    breakfast.put(j, jsonObject.getString(i + ""));
                    u = j +100;
                    i++;
                    breakfast.put(u,jsonObject.getString(i + ""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        s = breakfastGen(breakfast, j);
        i++;
        u=0;
        for ( j = 0; true; i++, j++) {
            try {
                if (jsonObject.getString(i + "").equals("endln")) {
                    break;
                } else {
                    lunch.put(j, jsonObject.getString(i + ""));
                    u = j +1000;
                    i++;
                    lunch.put(u,jsonObject.getString(i+""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        i++;

        s = s + ";" + lunchGen(lunch,j);
        u = 0;
        for (j = 0; true; i++, j++) {
            try {
                if (jsonObject.getString(i + "").equals("enddn")) {
                    break;
                } else {
                    dinner.put(j, jsonObject.getString(i + ""));
                    u = j +1000;
                    i++;
                    dinner.put(u,jsonObject.getString(i+""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        i++;
        s = s + ";" + dinnerGen(dinner,j);
        u=0;
        for (j = 0; true; i++, j++) {
            try {
                if (jsonObject.getString(i + "").equals("endsn")) {
                    break;
                } else {
                    snacks.put(j, jsonObject.getString(i + ""));
                    u = j +1000;
                    i++;
                    snacks.put(u,jsonObject.getString(i+""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        s = s + ";"+ snacksGen(snacks,j);
        s = s + ";"+ snacksGen(snacks,j);

        return s;
    }
}
