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
        SQLiteDatabase db = this.getWritableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

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
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ d.BFTableName);
        db.execSQL("DROP TABLE IF EXISTS "+ d.LNTableName);
        db.execSQL("DROP TABLE IF EXISTS "+ d.DNTableName);
        db.execSQL("DROP TABLE IF EXISTS "+ d.LNTableName);
        onCreate(db);
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
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean truncate(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table,null,null);
        db.close();
        if(result == -1)
        return false;

        return true;
    }

    public Cursor getData(String TableName,int carbs,int prots,int fats){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TableName+" where Carbs <= " + carbs +" and Proteins <= "+ prots +" and Fats <="+fats+" order by Calories",null);
        return res;
    }
}
