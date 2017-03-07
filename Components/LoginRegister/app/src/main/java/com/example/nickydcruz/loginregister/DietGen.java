package com.example.nickydcruz.loginregister;

import android.database.Cursor;
import android.util.SparseArray;

import org.json.JSONObject;

import java.util.HashMap;
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

    public HashMap<String, String> breakfastGen(int bmr1){
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
        int i=5,u=cal,p=1;
        JSONObject jsonObject =new JSONObject();
        HashMap<String,String> breakfast = new HashMap<>();
        String bf ="";
        String bfcal="";
        int counter =0;
            if (d > 0){
                while(d+1 > i){
                    if (((cal - Integer.parseInt(drinks.get(i))) < u)&& cal>Integer.parseInt(drinks.get(i))){
                        u=cal - Integer.parseInt(drinks.get(i));
                        p=i-4;
                    }
                    i= i+5;
                }
                String drink = drinks.get(p)+"\n"+"Proteins: "+drinks.get(p+1)+"\n"+"Fats: "+drinks.get(p+2)+"\n"+"Carbohydrates: "+drinks.get(p+3)+"\n"+"Total Calories: "+drinks.get(p+4);
                bf = drinks.get(p);
                bfcal = drinks.get(p+4);
                breakfast.put(counter+"",drink);
                counter++;
/*
                try {
                    jsonObject.put("drinkn",drinks.get(p++));
                    jsonObject.put("drinkp",drinks.get(p++));
                    jsonObject.put("drinkf",drinks.get(p++));
                    jsonObject.put("drinkc",drinks.get(p++));
                    jsonObject.put("drinkcal",drinks.get(p));

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
  */
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
                                    String base1 = base.get(p)+"\n"+"Proteins: "+base.get(p+1)+"\n"+"Fats: "+base.get(p+2)+"\n"+"Carbohydrates: "+base.get(p+3)+"\n"+"Total Calories: "+base.get(p+4);
                                    bf = bf+";"+ base.get(p);
                                    bfcal = bfcal+"\n\n"+base.get(p+4);
                                    breakfast.put(counter+"",base1);
                                    counter++;
                                    /*
                                    try {
                                        jsonObject.put("basen", base.get(p++));
                                        jsonObject.put("basep", base.get(p++));
                                        jsonObject.put("basef", base.get(p++));
                                        jsonObject.put("basec", base.get(p++));
                                        jsonObject.put("basecal", base.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                    */
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
                                    String sides1 = sides.get(p)+"\n"+"Proteins: "+sides.get(p+1)+"\n"+"Fats: "+sides.get(p+2)+"\n"+"Carbohydrates: "+sides.get(p+3)+"\n"+"Total Calories: "+sides.get(p+4);
                                    bf = bf+";"+ sides.get(p);
                                    bfcal = bfcal+"\n\n"+sides.get(p+4);
                                    breakfast.put(counter+"",sides1);
                                    counter++;
                                    /*
                                    try {
                                        jsonObject.put("sidesn", sides.get(p++));
                                        jsonObject.put("sidesp", sides.get(p++));
                                        jsonObject.put("sidesf", sides.get(p++));
                                        jsonObject.put("sidesc", sides.get(p++));
                                        jsonObject.put("sidescal", sides.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                    */

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
                                    String base1 = base.get(p)+"\n"+"Proteins: "+base.get(p+1)+"\n"+"Fats: "+base.get(p+2)+"\n"+"Carbohydrates: "+base.get(p+3)+"\n"+"Total Calories: "+base.get(p+4);
                                    bf = bf+";"+ base.get(p);
                                    bfcal = bfcal+"\n\n"+base.get(p+4);
                                    breakfast.put(counter+"",base1);
                                    counter++;
                                 /*
                                    try {
                                        jsonObject.put("basen", base.get(p++));
                                        jsonObject.put("basep", base.get(p++));
                                        jsonObject.put("basef", base.get(p++));
                                        jsonObject.put("basec", base.get(p++));
                                        jsonObject.put("basecal", base.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                   */

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
                                    String eggs1 = eggs.get(p)+"\n"+"Proteins: "+eggs.get(p+1)+"\n"+"Fats: "+eggs.get(p+2)+"\n"+"Carbohydrates: "+eggs.get(p+3)+"\n"+"Total Calories: "+eggs.get(p+4);
                                    bf = bf+";"+ eggs.get(p);
                                    bfcal = bfcal+"\n\n"+eggs.get(p+4);
                                    breakfast.put(counter+"",eggs1);
                                    counter++;
                                    /*
                                    try {
                                        jsonObject.put("eggsn", eggs.get(p++));
                                        jsonObject.put("eggsp", eggs.get(p++));
                                        jsonObject.put("eggsf", eggs.get(p++));
                                        jsonObject.put("eggsc", eggs.get(p++));
                                        jsonObject.put("eggscal", eggs.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                    */
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
                                    String eggs1 = eggs.get(p)+"\n"+"Proteins: "+eggs.get(p+1)+"\n"+"Fats: "+eggs.get(p+2)+"\n"+"Carbohydrates: "+eggs.get(p+3)+"\n"+"Total Calories: "+eggs.get(p+4);
                                    bf = bf+";"+ eggs.get(p);
                                    bfcal = bfcal+"\n\n"+eggs.get(p+4);
                                    breakfast.put(counter+"",eggs1);
                                    counter++;
                                    /*
                                    try {
                                        jsonObject.put("eggsn", eggs.get(p++));
                                        jsonObject.put("eggsp", eggs.get(p++));
                                        jsonObject.put("eggsf", eggs.get(p++));
                                        jsonObject.put("eggsc", eggs.get(p++));
                                        jsonObject.put("eggscal", eggs.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                    */
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
                                    String base1 = base.get(p)+"\n"+"Proteins: "+base.get(p+1)+"\n"+"Fats: "+base.get(p+2)+"\n"+"Carbohydrates: "+base.get(p+3)+"\n"+"Total Calories: "+base.get(p+4);
                                    bf = bf+";"+ base.get(p);
                                    bfcal = bfcal+"\n\n"+base.get(p+4);
                                    breakfast.put(counter+"",base1);
                                    counter++;
                                 /*
                                    try {
                                        jsonObject.put("basen", base.get(p++));
                                        jsonObject.put("basep", base.get(p++));
                                        jsonObject.put("basef", base.get(p++));
                                        jsonObject.put("basec", base.get(p++));
                                        jsonObject.put("basecal", base.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                    */
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
                                    String eggs1 = eggs.get(p)+"\n"+"Proteins: "+eggs.get(p+1)+"\n"+"Fats: "+eggs.get(p+2)+"\n"+"Carbohydrates: "+eggs.get(p+3)+"\n"+"Total Calories: "+eggs.get(p+4);
                                    bf = bf+";"+ eggs.get(p);
                                    bfcal = bfcal+"\n\n"+eggs.get(p+4);
                                    breakfast.put(counter+"",eggs1);
                                    counter++;
/*
                                    try {
                                        jsonObject.put("eggsn", eggs.get(p++));
                                        jsonObject.put("eggsp", eggs.get(p++));
                                        jsonObject.put("eggsf", eggs.get(p++));
                                        jsonObject.put("eggsc", eggs.get(p++));
                                        jsonObject.put("eggscal", eggs.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
*/
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
                                    String eggs1 = eggs.get(p)+"\n"+"Proteins: "+eggs.get(p+1)+"\n"+"Fats: "+eggs.get(p+2)+"\n"+"Carbohydrates: "+eggs.get(p+3)+"\n"+"Total Calories: "+eggs.get(p+4);
                                    bf = bf+";"+ eggs.get(p);
                                    bfcal = bfcal+"\n\n"+eggs.get(p+4);

                                    breakfast.put(counter+"",eggs1);
                                    counter++;
                                    /*
                                    try {
                                        jsonObject.put("eggsn", eggs.get(p++));
                                        jsonObject.put("eggsp", eggs.get(p++));
                                        jsonObject.put("eggsf", eggs.get(p++));
                                        jsonObject.put("eggsc", eggs.get(p++));
                                        jsonObject.put("eggscal", eggs.get(p));

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                    */
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
                            String base1 = base.get(p)+"\n"+"Proteins: "+base.get(p+1)+"\n"+"Fats: "+base.get(p+2)+"\n"+"Carbohydrates: "+base.get(p+3)+"\n"+"Total Calories: "+base.get(p+4);
                            bf = bf+";"+ base.get(p);
                            bfcal = bfcal+"\n\n"+base.get(p+4);
                            breakfast.put(counter+"",base1);
                            counter++;
                                 /*
                            try {
                                jsonObject.put("basen", base.get(p++));
                                jsonObject.put("basep", base.get(p++));
                                jsonObject.put("basef", base.get(p++));
                                jsonObject.put("basec", base.get(p++));
                                jsonObject.put("basecal", base.get(p));

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                            */
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
                        String eggs1 = eggs.get(p)+"\n"+"Proteins: "+eggs.get(p+1)+"\n"+"Fats: "+eggs.get(p+2)+"\n"+"Carbohydrates: "+eggs.get(p+3)+"\n"+"Total Calories: "+eggs.get(p+4);
                        bf = bf+";"+ eggs.get(p);
                        bfcal = bfcal+"\n\n"+eggs.get(p+4);
                        breakfast.put(counter+"",eggs1);
                        counter++;
                        /*
                        try {
                            jsonObject.put("eggsn", eggs.get(p++));
                            jsonObject.put("eggsp", eggs.get(p++));
                            jsonObject.put("eggsf", eggs.get(p++));
                            jsonObject.put("eggsc", eggs.get(p++));
                            jsonObject.put("eggscal", eggs.get(p));

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        */
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
                        String sides1 = sides.get(p)+"\n"+"Proteins: "+sides.get(p+1)+"\n"+"Fats: "+sides.get(p+2)+"\n"+"Carbohydrates: "+sides.get(p+3)+"\n"+"Total Calories: "+sides.get(p+4);
                        bf = bf+";"+ sides.get(p);
                        bfcal = bfcal+"\n\n"+sides.get(p+4);
                        breakfast.put(counter+"",sides1);
                        counter++;
                        /*
                        try {
                            jsonObject.put("sidesn", sides.get(p++));
                            jsonObject.put("sidesp", sides.get(p++));
                            jsonObject.put("sidesf", sides.get(p++));
                            jsonObject.put("sidesc", sides.get(p++));
                            jsonObject.put("sidescal", sides.get(p));

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        */
                    }

                }

            }
            else{
             //no data available at the moment
            }

        breakfast.put("bf",bf);
        breakfast.put("bfcal",bfcal);
        breakfast.put("count",counter-1 +"");
           return breakfast;

    }

    public HashMap<String, String> snackGen(int bmr1){
        float bmr = bmr1 * 0.125f;
        int a[] = new FormulaClass().bmrcal(bmr);
        Cursor res = dbHelper.getData(d1.SNTableName,a[0],a[1],a[2]) ;

        if(res.getCount() == 0){
            //error in data code
        }
        int d=1,i=0;

        SparseArray<String> snacks = new SparseArray<>();
        HashMap<String,String> snacking = new HashMap<>();

        while(res.moveToNext()) {

            snacks.put(d++, res.getString(1));
            snacks.put(d++, res.getString(2));
            snacks.put(d++, res.getString(3));
            snacks.put(d++, res.getString(4));
            snacks.put(d++, res.getString(5));


        }
        --d;

        int cal =(int)Math.round(bmr * 0.5);
        int u=cal,p=0;
        i=5;
        while(d >= i){
            if ((cal - Integer.parseInt(snacks.get(i))) < u){
                u=cal - Integer.parseInt(snacks.get(i));
                p=i-4;
            }
            i= i+5;
        }

        String sna = snacks.get(p);
        String snacal = snacks.get(p+4);
        String snac = snacks.get(p)+"\n Proteins: "+snacks.get(p+1)+"\n Fats: "+snacks.get(p+2)+"\n Carbohydrates: "+snacks.get(p+3)+"\n Calories: "+snacks.get(p+4);
        snacking.put("name",sna);
        snacking.put("cal",snacal);
        snacking.put("snac",snac);
        /*
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
        */

        return snacking;
    }
    public HashMap<String, String> LunchGen(int bmr1){
        float bmr = bmr1 * 0.25f;
        int a[] = new FormulaClass().bmrcal(bmr);
        Cursor res = dbHelper.getData(d1.LNTableName,a[0],a[1],a[2]) ;

        if(res.getCount() == 0){
            //error in data code
        }
        int d=1,i=0;

        SparseArray<String> snacks = new SparseArray<>();
        HashMap<String,String> snacking = new HashMap<>();

        while(res.moveToNext()) {

            snacks.put(d++, res.getString(1));
            snacks.put(d++, res.getString(2));
            snacks.put(d++, res.getString(3));
            snacks.put(d++, res.getString(4));
            snacks.put(d++, res.getString(5));


        }
        --d;

        int cal =(int)Math.round(bmr * 0.5);
        int u=cal,p=0;
        i=5;
        while(d >= i){
            if ((cal - Integer.parseInt(snacks.get(i))) < u){
                u=cal - Integer.parseInt(snacks.get(i));
                p=i-4;
            }
            i= i+5;
        }

        String sna = snacks.get(p);
        String snacal = snacks.get(p+4);
        String snac = snacks.get(p)+"\n Proteins: "+snacks.get(p+1)+"\n Fats: "+snacks.get(p+2)+"\n Carbohydrates: "+snacks.get(p+3)+"\n Calories: "+snacks.get(p+4);
        snacking.put("name",sna);
        snacking.put("cal",snacal);
        snacking.put("snac",snac);
        /*
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
        */

        return snacking;
    }



    public HashMap<String, String> DinGen(int bmr1){
        float bmr = bmr1 * 0.15f;
        int a[] = new FormulaClass().bmrcal(bmr);
        Cursor res = dbHelper.getData(d1.DNTableName,a[0],a[1],a[2]) ;

        if(res.getCount() == 0){
            //error in data code
        }
        int r=1,s=1,c=1,f=1,n=1,w=1,i=0;

        SparseArray<String> snacks = new SparseArray<>();
        HashMap<String,String> snacking = new HashMap<>();
        SparseArray<String> rice = new SparseArray<String>();
        SparseArray<String> sabji = new SparseArray<String>();
        SparseArray<String> currys = new SparseArray<String>();
        SparseArray<String> noodle = new SparseArray<String>();
        SparseArray<String> friednonveg = new SparseArray<String>();
        SparseArray<String> soup = new SparseArray<String>();

        while(res.moveToNext()) {
/*
            snacks.put(d++, res.getString(1));
            snacks.put(d++, res.getString(2));
            snacks.put(d++, res.getString(3));
            snacks.put(d++, res.getString(4));
            snacks.put(d++, res.getString(5));

*/
            if (res.getString(6).equals("a")) {
                rice.put(r++, res.getString(1));
                rice.put(r++, res.getString(2));
                rice.put(r++, res.getString(3));
                rice.put(r++, res.getString(4));
                rice.put(r++, res.getString(5));
            } else if (res.getString(6).equals("s")) {
                sabji.put(s++, res.getString(1));
                sabji.put(s++, res.getString(2));
                sabji.put(s++, res.getString(3));
                sabji.put(s++, res.getString(4));
                sabji.put(s++, res.getString(5));
            } else if (res.getString(6).equals("c")) {
                currys.put(c++, res.getString(1));
                currys.put(c++, res.getString(2));
                currys.put(c++, res.getString(3));
                currys.put(c++, res.getString(4));
                currys.put(c++, res.getString(5));
            } else if (res.getString(6).equals("f")) {
                noodle.put(f++, res.getString(1));
                noodle.put(f++, res.getString(2));
                noodle.put(f++, res.getString(3));
                noodle.put(f++, res.getString(4));
                noodle.put(f++, res.getString(5));
            } else if (res.getString(6).equals("n")) {
                friednonveg.put(n++, res.getString(1));
                friednonveg.put(n++, res.getString(2));
                friednonveg.put(n++, res.getString(3));
                friednonveg.put(n++, res.getString(4));
                friednonveg.put(n++, res.getString(5));
            } else if (res.getString(6).equals("w")) {
                soup.put(w++, res.getString(1));
                soup.put(w++, res.getString(2));
                soup.put(w++, res.getString(3));
                soup.put(w++, res.getString(4));
                soup.put(w++, res.getString(5));
            }
        }
        --r;--w;--s;--c;--n;--f;

        int cal =(int)Math.round(bmr * 0.5);

        if(r>0 && f>0 ){
            if (w>0 && s>0 && c>0 && n>0){

            }

        }
        else if (r>0) {

        }
        else if(f>0){

        }
        else if(w>0){

        }
/*
        int u=cal,p=0;
        i=5;
        while(d >= i){
            if ((cal - Integer.parseInt(snacks.get(i))) < u){
                u=cal - Integer.parseInt(snacks.get(i));
                p=i-4;
            }
            i= i+5;
        }

        String sna = snacks.get(p);
        String snacal = snacks.get(p+4);
        String snac = snacks.get(p)+"\n Proteins: "+snacks.get(p+1)+"\n Fats: "+snacks.get(p+2)+"\n Carbohydrates: "+snacks.get(p+3)+"\n Calories: "+snacks.get(p+4);
        snacking.put("name",sna);
        snacking.put("cal",snacal);
        snacking.put("snac",snac);
*/

        /*
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
        */

        return snacking;
    }
//


}
