package com.example.nlss.custom_diet;

import android.provider.BaseColumns;

/**
 * Created by Leo on 16/02/2017.
 */
public class DetailContract {

    public static final class Users implements BaseColumns{

        String foodDatabase ="";
        public Users(String tableName) {
            foodDatabase = tableName+"foodDatabase";
        }

        public static final String COLUMN_food = "food";
        public static final String COLUMN_protein = "protein";
        public static final String COLUMN_fat = "fat";
        public static final String COLUMN_carbohydrate = "carbohydrate";
        public static final String COLUMN_calorie = "calorie";
        public static final String COLUMN_type = "type";
    }
}
