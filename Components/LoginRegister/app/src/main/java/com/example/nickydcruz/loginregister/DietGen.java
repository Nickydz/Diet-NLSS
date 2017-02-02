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
        return breakfast.get(r.nextInt(i));
    }

    public String lunchGen(SparseArray<String> lunch, int i) {
        Random r =new Random();
        return lunch.get(r.nextInt(i));
    }

    public String dinnerGen(SparseArray<String> dinner, int i) {
        Random r =new Random();
        return dinner.get(r.nextInt(i));
    }

    public String snacksGen(SparseArray<String> snacks, int i) {
        Random r =new Random();
        return snacks.get(r.nextInt(i));
    }

    public String dietDivider(JSONObject jsonObject) {
        SparseArray<String> breakfast = new SparseArray<>();
        SparseArray<String> lunch = new SparseArray<>();
        SparseArray<String> dinner = new SparseArray<>();
        SparseArray<String> snacks = new SparseArray<>();
        int i;
        String s;
        for (i = 0; true; i++) {
            try {
                if (jsonObject.getString(i + "").equals("endbf")) {
                    break;
                } else {
                    breakfast.put(i, jsonObject.getString(i + ""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        s = breakfastGen(breakfast, i);
        i++;
        int j;
        for ( j = 0; true; i++, j++) {
            try {
                if (jsonObject.getString(i + "").equals("endln")) {
                    break;
                } else {
                    lunch.put(j, jsonObject.getString(i + ""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        i++;

        s = s + ";" + lunchGen(lunch,j);

        for (j = 0; true; i++, j++) {
            try {
                if (jsonObject.getString(i + "").equals("enddn")) {
                    break;
                } else {
                    dinner.put(j, jsonObject.getString(i + ""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        i++;
        s = s + ";" + dinnerGen(dinner,j);

        for (j = 0; true; i++, j++) {
            try {
                if (jsonObject.getString(i + "").equals("endsn")) {
                    break;
                } else {
                    snacks.put(j, jsonObject.getString(i + ""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        s = s + ";"+ snacksGen(snacks,j);

        return s;
    }
}
