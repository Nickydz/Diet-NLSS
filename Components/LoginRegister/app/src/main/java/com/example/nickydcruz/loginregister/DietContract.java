package com.example.nickydcruz.loginregister;

import android.provider.BaseColumns;

/**
 * Created by Nicky D. Cruz on 2/15/2017.
 */

public class DietContract {


    public static final class DietEntry implements BaseColumns{

        String BFTableName ="";
        String LNTableName ="";
        String DNTableName ="";
        String SNTableName ="";
        String DietTableName ="";
        public DietEntry(String tableName) {
            DietTableName = tableName+"_Diet_table";
            BFTableName = tableName+"_Breakfast";
            LNTableName = tableName+"_Lunch";
            DNTableName = tableName+"_Dinner";
            SNTableName = tableName+"_Snacks";
        }


        public static final String COLUMN_Name = "Name";
        public static final String COLUMN_Proteins = "Proteins";
        public static final String COLUMN_Fats = "Fats";
        public static final String COLUMN_Carbs = "Carbs";
        public static final String COLUMN_Calories = "Calories";
        public static final String COLUMN_Flags = "Flags";
        public static final String COLUMN_Date = "Date";
        public static final String COLUMN_Breakfast = "Breakfast";
        public static final String COLUMN_Lunch = "Lunch";
        public static final String COLUMN_Dinner = "Dinner";
        public static final String COLUMN_Snacks1 = "Snacks1";
        public static final String COLUMN_Snacks2 = "Snacks2";
        public static final String COLUMN_Bcal = "Bcal";
        public static final String COLUMN_Lcal = "Lcal";
        public static final String COLUMN_Dcal = "Dcal";
        public static final String COLUMN_Scal1 = "Scal1";
        public static final String COLUMN_Scal2 = "Scal2";
        public static final String COLUMN_Bxpl = "Bxpl";
        public static final String COLUMN_Lxpl = "Lxpl";
        public static final String COLUMN_Dxpl = "Dxpl";
        public static final String COLUMN_Sxpl1 = "Sxpl1";
        public static final String COLUMN_Sxpl2 = "Sxpl2";
        public static final String COLUMN_Count = "Count";
        public static final String COLUMN_TotalCal = "TotalCal";

    }
}
