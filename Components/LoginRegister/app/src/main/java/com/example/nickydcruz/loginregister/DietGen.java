package com.example.nickydcruz.loginregister;

import android.database.Cursor;
import android.util.SparseArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by Nicky D. Cruz on 2/25/2017.
 */

public class DietGen {

    String username;
    DBHelper dbHelper ;
    DietContract.DietEntry d1 ;

    public DietGen(String username,DBHelper myDb) {
        this.username = username;
        dbHelper=myDb;
        d1 = new DietContract.DietEntry(username);
    }

    public JSONObject breakfastGen(int bmr1){
        float bmr = bmr1 * 0.35f;
        int a[] = new FormulaClass().bmrcal(bmr);
        Cursor res = dbHelper.getData(d1.BFTableName,a[0],a[1],a[2]) ;

        if(res.getCount() == 0){
            //error in data code
        }


        SparseArray<String> drinks = new SparseArray<>();
        SparseArray<String> sides = new SparseArray<>();
        SparseArray<String> base = new SparseArray<>();
        SparseArray<String> eggs = new SparseArray<>();

        int d=1,s=1,b=1,e=1;

        while(res.moveToNext()) {
            if (res.getString(6).equals("d")) {
                drinks.put(d++, res.getString(1));
                drinks.put(d++, res.getString(2));
                drinks.put(d++, res.getString(3));
                drinks.put(d++, res.getString(4));
                drinks.put(d++, res.getString(5));
            } else if (res.getString(6).equals("b")) {
                base.put(b++, res.getString(1));
                base.put(b++, res.getString(2));
                base.put(b++, res.getString(3));
                base.put(b++, res.getString(4));
                base.put(b++, res.getString(5));
            } else if (res.getString(6).equals("e")) {
                eggs.put(e++, res.getString(1));
                eggs.put(e++, res.getString(2));
                eggs.put(e++, res.getString(3));
                eggs.put(e++, res.getString(4));
                eggs.put(e++, res.getString(5));
            } else if (res.getString(6).equals("s")) {
                sides.put(s++, res.getString(1));
                sides.put(s++, res.getString(2));
                sides.put(s++, res.getString(3));
                sides.put(s++, res.getString(4));
                sides.put(s++, res.getString(5));
            }
        }
        --d;--e;--s;--b;
        int cal =(int)Math.round(bmr * 0.5);
        int i=5,u=cal,p=0;
        JSONObject jsonObject =new JSONObject();

            if (d >= 0){
                while(d > i){
                    if ((cal - Integer.parseInt(drinks.get(i))) < u){
                        u=cal - Integer.parseInt(drinks.get(i));
                        p=i-4;
                    }
                    i= i+5;
                }


                try {
                    jsonObject.put("drinkn",drinks.get(p++));
                    jsonObject.put("drinkp",drinks.get(p++));
                    jsonObject.put("drinkf",drinks.get(p++));
                    jsonObject.put("drinkc",drinks.get(p++));
                    jsonObject.put("drinkcal",drinks.get(p));

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                cal = Math.round(bmr) - cal +u;
                Random r= new Random();
                i=5;
                u=cal;
                p=0;
                if(b>0){
                    if(e>0){
                        if(s>0){
                            int r1 = r.nextInt(2);
                            if(r1 == 0){
                                i=5;
                                while(b >= i){
                                    if ((cal - Integer.parseInt(base.get(i))) < u){
                                        u=cal - Integer.parseInt(base.get(i));
                                        p=i-4;
                                    }
                                    i= i+5;
                                }
                                i=5;
                                cal =u;
                                if(p != 0) {
                                    try {
                                        jsonObject.put("basen", base.get(p++));
                                        jsonObject.put("basep", base.get(p++));
                                        jsonObject.put("basef", base.get(p++));
                                        jsonObject.put("basec", base.get(p++));
                                        jsonObject.put("basecal", base.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                                p=0;
                                while(s >= i){
                                    if ((cal - Integer.parseInt(sides.get(i))) < u){
                                        u=cal - Integer.parseInt(sides.get(i));
                                        p=i-4;
                                    }
                                    i= i+5;
                                }

                                if(p != 0) {
                                    try {
                                        jsonObject.put("sidesn", sides.get(p++));
                                        jsonObject.put("sidesp", sides.get(p++));
                                        jsonObject.put("sidesf", sides.get(p++));
                                        jsonObject.put("sidesc", sides.get(p++));
                                        jsonObject.put("sidescal", sides.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                            if(r1 == 1){
                                i=5;
                                while(b >= i){
                                    if ((cal - Integer.parseInt(base.get(i))) < u){
                                        u=cal - Integer.parseInt(base.get(i));
                                        p=i-4;
                                    }
                                    i= i+5;
                                }
                                i=5;
                                cal=u;

                                if(p != 0) {
                                    try {
                                        jsonObject.put("basen", base.get(p++));
                                        jsonObject.put("basep", base.get(p++));
                                        jsonObject.put("basef", base.get(p++));
                                        jsonObject.put("basec", base.get(p++));
                                        jsonObject.put("basecal", base.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                                p=0;

                                while(e >= i){
                                    if ((cal - Integer.parseInt(eggs.get(i))) < u){
                                        u=cal - Integer.parseInt(eggs.get(i));
                                        p=i-4;
                                    }
                                    i= i+5;
                                }
                                if(p != 0) {
                                    try {
                                        jsonObject.put("eggsn", eggs.get(p++));
                                        jsonObject.put("eggsp", eggs.get(p++));
                                        jsonObject.put("eggsf", eggs.get(p++));
                                        jsonObject.put("eggsc", eggs.get(p++));
                                        jsonObject.put("eggscal", eggs.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                            }
                            if(r1 == 2){
                                i=5;
                                while(e >= i){
                                    if ((cal - Integer.parseInt(eggs.get(i))) < u){
                                        u=cal - Integer.parseInt(eggs.get(i));
                                        p=i-4;
                                    }
                                    i= i+5;
                                }

                                if(p != 0) {
                                    try {
                                        jsonObject.put("eggsn", eggs.get(p++));
                                        jsonObject.put("eggsp", eggs.get(p++));
                                        jsonObject.put("eggsf", eggs.get(p++));
                                        jsonObject.put("eggsc", eggs.get(p++));
                                        jsonObject.put("eggscal", eggs.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                            }
                        }
                        else{
                            int r1 = r.nextInt(1);

                            if(r1 == 0){
                                i=5;
                                while(b >= i){
                                    if ((cal - Integer.parseInt(base.get(i))) < u){
                                        u=cal - Integer.parseInt(base.get(i));
                                        p=i-4;
                                    }
                                    i= i+5;
                                }
                                i=5;
                                cal=u;

                                if(p != 0) {
                                    try {
                                        jsonObject.put("basen", base.get(p++));
                                        jsonObject.put("basep", base.get(p++));
                                        jsonObject.put("basef", base.get(p++));
                                        jsonObject.put("basec", base.get(p++));
                                        jsonObject.put("basecal", base.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                                p=0;

                                while(e >= i){
                                    if ((cal - Integer.parseInt(eggs.get(i))) < u){
                                        u=cal - Integer.parseInt(eggs.get(i));
                                        p=i-4;
                                    }
                                    i= i+5;
                                }

                                if(p != 0) {
                                    try {
                                        jsonObject.put("eggsn", eggs.get(p++));
                                        jsonObject.put("eggsp", eggs.get(p++));
                                        jsonObject.put("eggsf", eggs.get(p++));
                                        jsonObject.put("eggsc", eggs.get(p++));
                                        jsonObject.put("eggscal", eggs.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                            if(r1 == 1){
                                i=5;
                                while(e >= i){
                                    if ((cal - Integer.parseInt(eggs.get(i))) < u){
                                        u=cal - Integer.parseInt(eggs.get(i));
                                        p=i-4;
                                    }
                                    i= i+5;
                                }

                                if(p != 0) {
                                    try {
                                        jsonObject.put("eggsn", eggs.get(p++));
                                        jsonObject.put("eggsp", eggs.get(p++));
                                        jsonObject.put("eggsf", eggs.get(p++));
                                        jsonObject.put("eggsc", eggs.get(p++));
                                        jsonObject.put("eggscal", eggs.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                            }
                        }

                    }
                    else {
                        while(b >= i){
                            if ((cal - Integer.parseInt(base.get(i))) < u){
                                u=cal - Integer.parseInt(base.get(i));
                                p=i-4;
                            }
                            i= i+5;
                        }

                        if(p != 0) {
                            try {
                                jsonObject.put("basen", base.get(p++));
                                jsonObject.put("basep", base.get(p++));
                                jsonObject.put("basef", base.get(p++));
                                jsonObject.put("basec", base.get(p++));
                                jsonObject.put("basecal", base.get(p));

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                        p=0;
                    }

                }
                else if(e>0){
                    i=5;
                    while(e >= i){
                        if ((cal - Integer.parseInt(eggs.get(i))) < u){
                            u=cal - Integer.parseInt(eggs.get(i));
                            p=i-4;
                        }
                        i= i+5;
                    }

                    if(p != 0) {
                        try {
                            jsonObject.put("eggsn", eggs.get(p++));
                            jsonObject.put("eggsp", eggs.get(p++));
                            jsonObject.put("eggsf", eggs.get(p++));
                            jsonObject.put("eggsc", eggs.get(p++));
                            jsonObject.put("eggscal", eggs.get(p));

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }

                }
                else if(s>0){
                    i=5;
                    while(e >= i){
                        if ((cal - Integer.parseInt(sides.get(i))) < u){
                            u=cal - Integer.parseInt(sides.get(i));
                            p=i-4;
                        }
                        i= i+5;
                    }

                    if(p != 0) {
                        try {
                            jsonObject.put("sidesn", sides.get(p++));
                            jsonObject.put("sidesp", sides.get(p++));
                            jsonObject.put("sidesf", sides.get(p++));
                            jsonObject.put("sidesc", sides.get(p++));
                            jsonObject.put("sidescal", sides.get(p));

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }

                }

            }
            else{
             //no data available at the moment
            }
           return jsonObject;

    }

    public JSONObject snackGen(int bmr1){
        float bmr = bmr1 * 0.125f;
        int a[] = new FormulaClass().bmrcal(bmr);
        Cursor res = dbHelper.getData(d1.SNTableName,a[0],a[1],a[2]) ;

        if(res.getCount() == 0){
            //error in data code
        }
        int d=1,i=0;

        SparseArray<String> snacks = new SparseArray<>();

        while(res.moveToNext()) {

            snacks.put(d++, res.getString(1));
            snacks.put(d++, res.getString(2));
            snacks.put(d++, res.getString(3));
            snacks.put(d++, res.getString(4));
            snacks.put(d++, res.getString(5));
            i++;

        }
        --d;
        Random r =new Random();
        int p = r.nextInt(i);
        if (p==0)
            p++;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("snackn",snacks.get(p++));
            jsonObject.put("snackp",snacks.get(p++));
            jsonObject.put("snackf",snacks.get(p++));
            jsonObject.put("snackc",snacks.get(p++));
            jsonObject.put("snakcal",snacks.get(p));

        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return jsonObject;
    }
//


}
