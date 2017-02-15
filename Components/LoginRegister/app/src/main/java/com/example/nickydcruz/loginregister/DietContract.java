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
        public DietEntry(String tableName) {
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

    }
}
