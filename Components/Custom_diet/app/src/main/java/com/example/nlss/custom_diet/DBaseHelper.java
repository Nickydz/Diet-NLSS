package com.example.nlss.custom_diet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Leo on 2/16/2017.
 */

public class DBaseHelper extends SQLiteOpenHelper {

    public static final String Database_Name = "foodDatabase.db";
    public static final int Database_Version = 1;
    DetailContract.Users d;
    private String username;

    public DBaseHelper(Context context, String username) {
        super(context, Database_Name,null , Database_Version);
        this.username=username;
        d = new DetailContract.Users(username);
        SQLiteDatabase db = this.getWritableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_FOOD_TABLE = "CREATE TABLE " +
                d.foodDatabase +" (" +
                DetailContract.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DetailContract.Users.COLUMN_food +" TEXT NOT NULL," +
                DetailContract.Users.COLUMN_protein +" TEXT NOT NULL,"+
                DetailContract.Users.COLUMN_fat +" TEXT NOT NULL,"+
                DetailContract.Users.COLUMN_carbohydrate+" TEXT NOT NULL,"+
                DetailContract.Users.COLUMN_calorie+ " INTEGER NOT NULL,"+
                DetailContract.Users.COLUMN_type+" TEXT NOT NULL"+
                ");";


        db.execSQL(SQL_CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ d.foodDatabase);
        onCreate(db);
    }

    public boolean insertData(String TableName,String food,String protein,String fat,String carbohydrate,String calorie,String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(DetailContract.Users.COLUMN_food,food);
        con.put(DetailContract.Users.COLUMN_protein,protein);
        con.put(DetailContract.Users.COLUMN_fat,fat);
        con.put(DetailContract.Users.COLUMN_carbohydrate,carbohydrate);
        con.put(DetailContract.Users.COLUMN_calorie,calorie);
        con.put(DetailContract.Users.COLUMN_type,type);

        long result = db.insert(TableName,null,con);
        if (result == -1)
            return false;
        else
            return true;
    }
}
