package com.example.nickydcruz.loginregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nicky D. Cruz on 2/15/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String Database_Name = "diet.db";
    public static final int Database_Version = 1;
    DietContract.DietEntry d;
    private String username;

    public DBHelper(Context context,String username) {
        super(context, Database_Name,null , Database_Version);
        this.username=username;
        d = new DietContract.DietEntry(username);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_MEASURE_TABLE = "CREATE TABLE " +
                d.MeasureTable +" (" +
                DietContract.DietEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DietContract.DietEntry.COLUMN_Weight +" INTEGER NOT NULL," +
                DietContract.DietEntry.COLUMN_Height +" INTEGER NOT NULL," +
                DietContract.DietEntry.COLUMN_Flags+" TEXT NOT NULL"+
                ");";

        final String SQL_CREATE_BF_DIET_TABLE = "CREATE TABLE " +
                d.BFTableName +" (" +
                DietContract.DietEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DietContract.DietEntry.COLUMN_Name +" TEXT NOT NULL," +
                DietContract.DietEntry.COLUMN_Proteins +" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Fats +" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Carbs+" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Calories+ " INTEGER NOT NULL,"+
                DietContract.DietEntry.COLUMN_Flags+" TEXT NOT NULL"+
                ");";

        final String SQL_CREATE_USER_DIET_TABLE = "CREATE TABLE " +
                d.DietTableName +" (" +
                DietContract.DietEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DietContract.DietEntry.COLUMN_Date+" TEXT NOT NULL," +
                DietContract.DietEntry.COLUMN_Breakfast +" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Bcal +" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Bxpl +" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Lunch+" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Lcal+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Lxpl+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Dinner+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Dcal+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Dxpl+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Snacks1+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Scal1+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Sxpl1+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Snacks2+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Scal2+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Sxpl2+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_BCount+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_LCount+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_DCount+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_TotalCal+" TEXT NOT NULL"+
                ");";

        final String SQL_CREATE_LN_DIET_TABLE = "CREATE TABLE " +
                d.LNTableName +" (" +
                DietContract.DietEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DietContract.DietEntry.COLUMN_Name +" TEXT NOT NULL," +
                DietContract.DietEntry.COLUMN_Proteins +" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Fats +" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Carbs+" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Calories+ " INTEGER NOT NULL,"+
                DietContract.DietEntry.COLUMN_Flags+" TEXT NOT NULL"+
                ");";

        final String SQL_CREATE_DN_DIET_TABLE = "CREATE TABLE " +
                d.DNTableName +" (" +
                DietContract.DietEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DietContract.DietEntry.COLUMN_Name +" TEXT NOT NULL," +
                DietContract.DietEntry.COLUMN_Proteins +" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Fats +" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Carbs+" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Calories+ " INTEGER NOT NULL,"+
                DietContract.DietEntry.COLUMN_Flags+" TEXT NOT NULL"+
                ");";

        final String SQL_CREATE_SN_DIET_TABLE = "CREATE TABLE " +
                d.SNTableName +" (" +
                DietContract.DietEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DietContract.DietEntry.COLUMN_Name +" TEXT NOT NULL," +
                DietContract.DietEntry.COLUMN_Proteins +" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Fats +" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Carbs+" TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_Calories+ " INTEGER NOT NULL"+
                ");";

        db.execSQL(SQL_CREATE_BF_DIET_TABLE);
        db.execSQL(SQL_CREATE_LN_DIET_TABLE);
        db.execSQL(SQL_CREATE_DN_DIET_TABLE);
        db.execSQL(SQL_CREATE_SN_DIET_TABLE);
        db.execSQL(SQL_CREATE_USER_DIET_TABLE);
        db.execSQL(SQL_CREATE_MEASURE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + d.BFTableName);
            db.execSQL("DROP TABLE IF EXISTS " + d.LNTableName);
            db.execSQL("DROP TABLE IF EXISTS " + d.DNTableName);
            db.execSQL("DROP TABLE IF EXISTS " + d.LNTableName);
            db.execSQL("DROP TABLE IF EXISTS " + d.DietTableName);
            db.execSQL("DROP TABLE IF EXISTS " + d.MeasureTable);
            onCreate(db);
        }
        else if(oldVersion>newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + d.BFTableName);
            db.execSQL("DROP TABLE IF EXISTS " + d.LNTableName);
            db.execSQL("DROP TABLE IF EXISTS " + d.DNTableName);
            db.execSQL("DROP TABLE IF EXISTS " + d.LNTableName);
            db.execSQL("DROP TABLE IF EXISTS " + d.DietTableName);
            db.execSQL("DROP TABLE IF EXISTS " + d.MeasureTable);
            onCreate(db);
        }

    }

    public boolean insertData(String TableName,String Name,String Proteins,String Fats,String Carbs,String Calories,String Flags){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(DietContract.DietEntry.COLUMN_Name,Name);
        con.put(DietContract.DietEntry.COLUMN_Proteins,Proteins);
        con.put(DietContract.DietEntry.COLUMN_Fats,Fats);
        con.put(DietContract.DietEntry.COLUMN_Carbs,Carbs);
        con.put(DietContract.DietEntry.COLUMN_Calories,Calories);
        if (!TableName.equals(d.SNTableName)) {
            con.put(DietContract.DietEntry.COLUMN_Flags, Flags);
        }
        long result = db.insert(TableName,null,con);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean insertUserDietData(String TableName,String Date,String Breakfast,String Bcal,String Bxpl,String Lunch,String Lcal,String Lxpl,String Dinner,String Dcal,String Dxpl,String Snacks1,String Scal1,String Sxpl1,String Snacks2,String Scal2,String Sxpl2,String bcount,String lcount,String dcount,String TotalCal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(DietContract.DietEntry.COLUMN_Date,Date);
        con.put(DietContract.DietEntry.COLUMN_Breakfast,Breakfast);
        con.put(DietContract.DietEntry.COLUMN_Bcal,Bcal);
        con.put(DietContract.DietEntry.COLUMN_Bxpl,Bxpl);
        con.put(DietContract.DietEntry.COLUMN_Lunch,Lunch);
        con.put(DietContract.DietEntry.COLUMN_Lcal,Lcal);
        con.put(DietContract.DietEntry.COLUMN_Lxpl,Lxpl);
        con.put(DietContract.DietEntry.COLUMN_Dinner, Dinner);
        con.put(DietContract.DietEntry.COLUMN_Dcal, Dcal);
        con.put(DietContract.DietEntry.COLUMN_Dxpl,Dxpl);
        con.put(DietContract.DietEntry.COLUMN_Snacks1, Snacks1);
        con.put(DietContract.DietEntry.COLUMN_Scal1, Scal1);
        con.put(DietContract.DietEntry.COLUMN_Sxpl1,Sxpl1);
        con.put(DietContract.DietEntry.COLUMN_Snacks2, Snacks2);
        con.put(DietContract.DietEntry.COLUMN_Scal2, Scal2);
        con.put(DietContract.DietEntry.COLUMN_Sxpl2,Sxpl2);
        con.put(DietContract.DietEntry.COLUMN_BCount, bcount);
        con.put(DietContract.DietEntry.COLUMN_LCount, lcount);
        con.put(DietContract.DietEntry.COLUMN_DCount, dcount);
        con.put(DietContract.DietEntry.COLUMN_TotalCal, TotalCal);

        long result = db.insert(TableName,null,con);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean truncate(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table,null,null);
        return true;
    }

    public Cursor getData(String TableName,int carbs,int prots,int fats){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TableName,null);
        return res;
    }

    public Cursor getDietData(String TableName,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns ={DietContract.DietEntry._ID,DietContract.DietEntry.COLUMN_Date,DietContract.DietEntry.COLUMN_Breakfast,
                DietContract.DietEntry.COLUMN_Bcal, DietContract.DietEntry.COLUMN_Bxpl, DietContract.DietEntry.COLUMN_Lunch, DietContract.DietEntry.COLUMN_Lcal,
                DietContract.DietEntry.COLUMN_Lxpl, DietContract.DietEntry.COLUMN_Dinner, DietContract.DietEntry.COLUMN_Dcal, DietContract.DietEntry.COLUMN_Dxpl, DietContract.DietEntry.COLUMN_Snacks1, DietContract.DietEntry.COLUMN_Scal1,
                DietContract.DietEntry.COLUMN_Sxpl1, DietContract.DietEntry.COLUMN_Snacks2, DietContract.DietEntry.COLUMN_Scal2, DietContract.DietEntry.COLUMN_Sxpl2, DietContract.DietEntry.COLUMN_BCount, DietContract.DietEntry.COLUMN_LCount,
                DietContract.DietEntry.COLUMN_DCount,DietContract.DietEntry.COLUMN_TotalCal};

        String[] selectionargs = {date};

        Cursor res = db.query(TableName,columns, DietContract.DietEntry.COLUMN_Date+" =?",selectionargs,null,null,null);
        return res;
    }

    public Cursor getDrinks() {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id",DietContract.DietEntry.COLUMN_Name};
        String[] selectionargs = {"d"};
        Cursor res = db.query(d.BFTableName,columns, DietContract.DietEntry.COLUMN_Flags+" =?",selectionargs,null,null,null);
        return res;
    }
}
