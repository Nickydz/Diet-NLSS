package com.example.nickydcruz.loginregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.support.design.widget.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

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
                DietContract.DietEntry.COLUMN_Weight +" INTEGER," +
                DietContract.DietEntry.COLUMN_Height +" INTEGER," +
                DietContract.DietEntry.COLUMN_Date+" TEXT NOT NULL"+
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
                DietContract.DietEntry.COLUMN_Date+" TEXT NOT NULL UNIQUE," +
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
                DietContract.DietEntry.COLUMN_S1Count+ " TEXT NOT NULL,"+
                DietContract.DietEntry.COLUMN_S2Count+ " TEXT NOT NULL,"+
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


    public HashMap graphdatawt(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> usersList;
        usersList = new ArrayList<HashMap<String, String>>();
        Cursor res = db.rawQuery("SELECT "+ DietContract.DietEntry.COLUMN_Date+", "+ DietContract.DietEntry.COLUMN_Weight+" FROM "+d.MeasureTable,null);
        HashMap<String,String> arr =new HashMap<>();
        int i=0;
        if (res.moveToFirst()) {
            do {
                arr.put("Date"+i,res.getString(0));
                arr.put("Weight"+i,res.getString(1));
               i++;
            } while (res.moveToNext());
        }
        arr.put("Count",i+"");
        db.close();
//        return (Cursor) usersList;
        return arr;
    }

    public void insertData(int count, String TableName, ArrayList<String> Name, ArrayList<String> Proteins, ArrayList<String> Fats, ArrayList<String> Carbs, ArrayList<String> Calories, ArrayList<String> Flags){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues con = new ContentValues();
//
//        con.put(DietContract.DietEntry.COLUMN_Name,Name);
//        con.put(DietContract.DietEntry.COLUMN_Proteins,Proteins);
//        con.put(DietContract.DietEntry.COLUMN_Fats,Fats);
//        con.put(DietContract.DietEntry.COLUMN_Carbs,Carbs);
//        con.put(DietContract.DietEntry.COLUMN_Calories,Calories);
//
//        if (!TableName.equals(d.SNTableName)) {
//            con.put(DietContract.DietEntry.COLUMN_Flags, Flags);
//        }
        String sql;
        if(!(TableName.equals(d.SNTableName))) {
             sql = "INSERT INTO " + TableName + "(" + DietContract.DietEntry.COLUMN_Name + ", " + DietContract.DietEntry.COLUMN_Proteins + ", " + DietContract.DietEntry.COLUMN_Fats + ", " + DietContract.DietEntry.COLUMN_Carbs + ", " + DietContract.DietEntry.COLUMN_Calories + ", " + DietContract.DietEntry.COLUMN_Flags + ") values (?, ?, ?, ?, ?, ?);";
        }
        else{
             sql = "INSERT INTO " + TableName + "(" + DietContract.DietEntry.COLUMN_Name + ", " + DietContract.DietEntry.COLUMN_Proteins + ", " + DietContract.DietEntry.COLUMN_Fats + ", " + DietContract.DietEntry.COLUMN_Carbs + ", " + DietContract.DietEntry.COLUMN_Calories + ") values (?, ?, ?, ?, ?);";
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sql);
//        long result = db.insert(TableName,null,con);
        for(int i =0;i<count;i++ ){
            stmt.bindString(1,Name.get(i));
            stmt.bindString(2,Proteins.get(i));
            stmt.bindString(3,Fats.get(i));
            stmt.bindString(4,Carbs.get(i));
            stmt.bindString(5,Calories.get(i));
            if(!(TableName.equals(d.SNTableName)))
            stmt.bindString(6,Flags.get(i));

            long entryID = stmt.executeInsert();
            stmt.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
//        if (result == -1)
//            return false;
//        else
//            return true;
    }

    public void insertMeasureData(String TableName,String weight, String height, String Date){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues con = new ContentValues();
//
//        con.put(DietContract.DietEntry.COLUMN_Name,Name);
//        con.put(DietContract.DietEntry.COLUMN_Proteins,Proteins);
//        con.put(DietContract.DietEntry.COLUMN_Fats,Fats);
//        con.put(DietContract.DietEntry.COLUMN_Carbs,Carbs);
//        con.put(DietContract.DietEntry.COLUMN_Calories,Calories);
//
//        if (!TableName.equals(d.SNTableName)) {
//            con.put(DietContract.DietEntry.COLUMN_Flags, Flags);
//        }
//        if (result == -1)
//            return false;
//        else
//            return true;


        String sql ="INSERT INTO " + d.MeasureTable  + "(" + DietContract.DietEntry.COLUMN_Weight + ", " + DietContract.DietEntry.COLUMN_Height + ", " + DietContract.DietEntry.COLUMN_Date + ") values (?, ?, ?);";

        SQLiteDatabase db0 = this.getWritableDatabase();
        if(CheckIsDataAlreadyInDBorNot(TableName, DietContract.DietEntry.COLUMN_Date,Date)){
            db0.beginTransaction();
            db0.delete(TableName, DietContract.DietEntry.COLUMN_Date+" =?",new String[]{Date});
            db0.setTransactionSuccessful();
            db0.endTransaction();
            db0.close();
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sql);
//        long result = db.insert(TableName,null,con);


        stmt.bindString(1,weight);
        stmt.bindString(2,height);
        stmt.bindString(3,Date);

        long entryID = stmt.executeInsert();
        stmt.clearBindings();


        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();


    }


    public boolean insertUserDietData(String TableName,String Date,String Breakfast,String Bcal,String Bxpl,String Lunch,
                                      String Lcal,String Lxpl,String Dinner,String Dcal,String Dxpl,String Snacks1,
                                      String Scal1,String Sxpl1,String Snacks2,String Scal2,String Sxpl2,String bcount,
                                      String lcount,String dcount,String s1count,String s2count,String TotalCal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();



        if(CheckIsDataAlreadyInDBorNot(TableName, DietContract.DietEntry.COLUMN_Date,Date)){
            db.beginTransaction();
            db.delete(TableName, DietContract.DietEntry.COLUMN_Date+" =?",new String[]{Date});
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
        }
//
////
////        con.put(DietContract.DietEntry.COLUMN_Date,Date);
////        con.put(DietContract.DietEntry.COLUMN_Breakfast,Breakfast);
////        con.put(DietContract.DietEntry.COLUMN_Bcal,Bcal);
////        con.put(DietContract.DietEntry.COLUMN_Bxpl,Bxpl);
////        con.put(DietContract.DietEntry.COLUMN_Lunch,Lunch);
////        con.put(DietContract.DietEntry.COLUMN_Lcal,Lcal);
////        con.put(DietContract.DietEntry.COLUMN_Lxpl,Lxpl);
////        con.put(DietContract.DietEntry.COLUMN_Dinner, Dinner);
////        con.put(DietContract.DietEntry.COLUMN_Dcal, Dcal);
////        con.put(DietContract.DietEntry.COLUMN_Dxpl,Dxpl);
////        con.put(DietContract.DietEntry.COLUMN_Snacks1, Snacks1);
////        con.put(DietContract.DietEntry.COLUMN_Scal1, Scal1);
////        con.put(DietContract.DietEntry.COLUMN_Sxpl1,Sxpl1);
////        con.put(DietContract.DietEntry.COLUMN_Snacks2, Snacks2);
////        con.put(DietContract.DietEntry.COLUMN_Scal2, Scal2);
////        con.put(DietContract.DietEntry.COLUMN_Sxpl2,Sxpl2);
////        con.put(DietContract.DietEntry.COLUMN_BCount, bcount);
////        con.put(DietContract.DietEntry.COLUMN_LCount, lcount);
////        con.put(DietContract.DietEntry.COLUMN_DCount, dcount);
////        con.put(DietContract.DietEntry.COLUMN_S1Count, s1count);
////        con.put(DietContract.DietEntry.COLUMN_S2Count, s2count);
////        con.put(DietContract.DietEntry.COLUMN_TotalCal, TotalCal);
//
//        long result = db.insert(TableName,null,con);
//        db.close();
//        if (result == -1)
//            return false;
//        else
//            return true;

        String sql ="INSERT INTO " + TableName + "(" + DietContract.DietEntry.COLUMN_Date + ", " + DietContract.DietEntry.COLUMN_Breakfast + ", "
                + DietContract.DietEntry.COLUMN_Bcal + ", " + DietContract.DietEntry.COLUMN_Bxpl + ", "
                + DietContract.DietEntry.COLUMN_Lunch + ", " + DietContract.DietEntry.COLUMN_Lcal + ", "
                + DietContract.DietEntry.COLUMN_Lxpl + ", " + DietContract.DietEntry.COLUMN_Dinner + ", "
                + DietContract.DietEntry.COLUMN_Dcal + ", " + DietContract.DietEntry.COLUMN_Dxpl + ", "
                + DietContract.DietEntry.COLUMN_Snacks1 + ", " + DietContract.DietEntry.COLUMN_Scal1 + ", "
                + DietContract.DietEntry.COLUMN_Sxpl1 + ", " + DietContract.DietEntry.COLUMN_Snacks2 + ", "
                + DietContract.DietEntry.COLUMN_Scal2 + ", " + DietContract.DietEntry.COLUMN_Sxpl2 + ", "
                + DietContract.DietEntry.COLUMN_BCount + ", " + DietContract.DietEntry.COLUMN_LCount + ", "
                + DietContract.DietEntry.COLUMN_DCount + ", " + DietContract.DietEntry.COLUMN_S1Count + ", "
                + DietContract.DietEntry.COLUMN_S2Count + ", " + DietContract.DietEntry.COLUMN_TotalCal
                + ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        SQLiteDatabase db1 = this.getWritableDatabase();
        db1.beginTransaction();
        SQLiteStatement stmt = db1.compileStatement(sql);
//        long result = db.insert(TableName,null,con);

            stmt.bindString(1,Date);
            stmt.bindString(2,Breakfast);
            stmt.bindString(3,Bcal);
            stmt.bindString(4,Bxpl);
            stmt.bindString(5,Lunch);
            stmt.bindString(6,Lcal);
            stmt.bindString(7,Lxpl);
            stmt.bindString(8,Dinner);
            stmt.bindString(9,Dcal);
            stmt.bindString(10,Dxpl);
            stmt.bindString(11,Snacks1);
            stmt.bindString(12,Scal1);
            stmt.bindString(13,Sxpl1);
            stmt.bindString(14,Snacks2);
            stmt.bindString(15,Scal2);
            stmt.bindString(16,Sxpl2);
            stmt.bindString(17,bcount);
            stmt.bindString(18,lcount);
            stmt.bindString(19,dcount);
            stmt.bindString(20,s1count);
            stmt.bindString(21,s2count);
            stmt.bindString(22,TotalCal);


            long entryID = stmt.executeInsert();
            stmt.clearBindings();


        db1.setTransactionSuccessful();
        db1.endTransaction();
        db1.close();

        return true;
    }

    public boolean truncate(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table,null,null);
        db.close();
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
                DietContract.DietEntry.COLUMN_DCount,DietContract.DietEntry.COLUMN_S1Count,DietContract.DietEntry.COLUMN_S2Count,DietContract.DietEntry.COLUMN_TotalCal};

        String[] selectionargs = {date};


        Cursor res = db.query(TableName,columns, DietContract.DietEntry.COLUMN_Date+" =?",selectionargs,null,null,null);
        return res;
    }

    public Cursor getDrinks() {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id",DietContract.DietEntry.COLUMN_Name};
        String[] selectionargs = {"d"};
        Cursor res = db.query(d.BFTableName,columns, DietContract.DietEntry.COLUMN_Flags+" =?",selectionargs,null,null,null);
        return res;
    }

    public boolean CheckIsDataAlreadyInDBorNot(String TableName,String dbfeild,String fieldValue){
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select * from " + TableName + " where " + dbfeild + " = ?";
        String[] array = {fieldValue};
        Cursor cursor = db.rawQuery(Query,array);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;

    }




}
