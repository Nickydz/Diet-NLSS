package com.example.nickydcruz.loginregister;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Leo on 2/16/2017.
 */

public class DBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "foodDatabase.db";
    public static final String TABLE_NAME = "food_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "food";
    public static final String COL_3 = "protein";
    public static final String COL_4 = "fat";
    public static final String COL_5 = "carbohydrate";
    public static final String COL_6 = "calorie";
    public static final String COL_7 = "type";



    public DBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    private DBaseHelper context = this;

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " ("+DietContract.DietEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,food TEXT NOT NULL,protein TEXT NOT NULL,fat TEXT NOT NULL,carbohydrate TEXT NOT NULL,calorie INTEGER NOT NULL,type TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


//public class DBaseHelper extends SQLiteOpenHelper {
//
//    public static final String Database_Name = "foodDatabase.db";
//    public static final int Database_Version = 1;
//    DetailContract.Users d;
//    private String username;
//
//    public DBaseHelper(Context context) {
//        super(context, Database_Name,null , Database_Version);
//        this.username=username;
//        d = new DetailContract.Users(username);
//        SQLiteDatabase db = this.getWritableDatabase();
//
//    }
//
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        final String SQL_CREATE_FOOD_TABLE = "CREATE TABLE " +
//                d.foodDatabase +" (" +
//                DetailContract.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
//                DetailContract.Users.COLUMN_food +" TEXT NOT NULL," +
//                DetailContract.Users.COLUMN_protein +" TEXT NOT NULL,"+
//                DetailContract.Users.COLUMN_fat +" TEXT NOT NULL,"+
//                DetailContract.Users.COLUMN_carbohydrate+" TEXT NOT NULL,"+
//                DetailContract.Users.COLUMN_calorie+ " INTEGER NOT NULL,"+
//                DetailContract.Users.COLUMN_type+" TEXT NOT NULL"+
//                ");";
//
//
//        db.execSQL(SQL_CREATE_FOOD_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+ d.foodDatabase);
//        onCreate(db);
//    }

    public Cursor getfood_name(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id","food"};
        String[] selectionargs = {"d"};
        Cursor res = db.query(TABLE_NAME,columns, null,null,null,null,null);
        return res;
    }

    public boolean insertData( String food, String protein, String fat, String carbohydrate, String calorie, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(COL_2, food);
        con.put(COL_3, protein);
        con.put(COL_4, fat);
        con.put(COL_5, carbohydrate);
        con.put(COL_6, calorie);
        con.put(COL_7, type);

        long result = db.insert(TABLE_NAME, null, con);
        if (result == -1)
            return false;
        else
            return true;
    }

    String foodname = "";
    String foodcal = "";


    public String[] selectfood(String food_pick){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> usersList;
        usersList = new ArrayList<HashMap<String, String>>();
        Cursor res = db.rawQuery("SELECT food,calorie FROM food_table WHERE food = '"+food_pick+ "'",null);
        String[] arr = new String[2];
        if (res.moveToFirst()) {
            do {
                foodname = res.getString(0);
                foodcal = res.getString(1);
//                        HashMap<String, String> map = new HashMap<String, String>();
//                        usersList.add(map);
            } while (res.moveToNext());
        }
        arr[0] = foodname;
        arr[1] = foodcal;
        db.close();
//        return (Cursor) usersList;
        return arr;
    }

//    public ArrayList<HashMap<String, String>> getAllUsers() {
//        ArrayList<HashMap<String, String>> usersList;
//        usersList = new ArrayList<HashMap<String, String>>();
//        String selectQuery = "SELECT  * FROM users";
//        SQLiteDatabase database = this.getWritableDatabase();
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                HashMap<String, String> map = new HashMap<String, String>();
//                tv.settext
//                map.put("userId", cursor.getString(0));
//                map.put("userName", cursor.getString(1));
//                usersList.add(map);
//            } while (cursor.moveToNext());
//        }
//        database.close();
//        return usersList;
//    }
}
